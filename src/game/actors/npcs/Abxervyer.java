// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
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
public class Abxervyer extends Enemy{

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
     *
     */
    public Abxervyer() {

        // displayed "Y", 2000 hp
        super("Abxervyer", 'Y', 2000, new Rune(5000));

        // attack the player with its limbs, dealing 80 damage with 25% accuracy
        this.damage = 80;
        this.hitRate = 25;

        // can follow the player
        this.addBehaviour(1, new FollowBehaviour());

        // boss will not get hurt if they walk around in the Void
        this.addCapability(Ability.STEP_ON_VOID);

        // has ability to control weather
        this.addCapability(Ability.SUNNY);
        this.addCapability(Ability.RAINY);

    }


    /**
     * Initialise the gate to be dropped by Abxervyer once defeated.
     *
     * @param droppedGate the dropped gate once Abxervyer is defeated
     *
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
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {
        // 100% chance to drop runes once defeated
        this.getRunes().getDropAction(this).execute(this,map);

        //  boss last stood turns into a Gate once defeated
        map.locationOf(this).setGround( this.droppedGate);

        // print a message when the boss is defeated
        for (String line : FancyMessage.BOSS_DEFEATED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
