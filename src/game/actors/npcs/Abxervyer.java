// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
import game.weather.WeatherManager;
import game.grounds.Gate;
import game.items.Rune;
import game.utils.Ability;
import game.utils.FancyMessage;

/**
 * A class that represent Abxervyer enemy in the Abxervyer Battle Room.
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 */
public class Abxervyer extends Enemy {

    // Abxervyer has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;

    // hit rate/accuracy
    /**
     * hit rate to other actor
     */
    private final int hitRate;

    /**
     * the dropped gate once Abxervyer is defeated
     */
    private Gate droppedGate;


    /**
     * Constructor for the Abxervyer class
     */
    public Abxervyer() {

        // displayed "Y", 2000 hp
        super("Abxervyer, The Forest Watcher", 'Y', 1, new Rune(5000));

        // attack the player with its limbs, dealing 80 damage with 25% accuracy
        this.damage = 80;
        this.hitRate = 25;

        // can follow the player
        this.addBehaviour(1, new FollowBehaviour());

        // boss will not get hurt if they walk around in the Void
        this.addCapability(Ability.STEP_ON_VOID);

    }

    /**
     * Select the valid action with the highest priority
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // get weather manager instance
        WeatherManager weatherManager = WeatherManager.getWeatherInstance();

        // run weather manager
        weatherManager.run(display);

        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Initialise the gate to be dropped by Abxervyer once defeated.
     *
     * @param droppedGate the dropped gate once Abxervyer is defeated
     */
    public void setDroppedGate(Gate droppedGate) {

        this.droppedGate = droppedGate;

    }

    /**
     * Creates and returns an intrinsic weapon for Abxervyer with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Abxervyer
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(damage, "hits", hitRate);

    }

    /**
     * Drop items on the ground once Abxervyer is defeated
     *
     * @param map the map containing the Abxervyer
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% chance to drop runes once defeated
//        this.getRunes().getDropAction(this).execute(this, map);
        super.dropItem(map);

        // boss last stood turns into a Gate once defeated
        map.locationOf(this).setGround(this.droppedGate);

    }

    /**
     * Method that can be executed when the Abxervyer is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the player fell unconscious
     *
     * @return a string describing what happened when the Abxervyer is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {

        // print a message when the boss is defeated
        for (String line : FancyMessage.BOSS_DEFEATED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        }

        return super.unconscious(actor, map);

    }

}
