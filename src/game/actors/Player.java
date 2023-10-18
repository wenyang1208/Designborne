// declare package
package game.actors;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.items.Rune;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.Ability;
import game.utils.FancyMessage;
import game.utils.Status;

/**
 * Class representing the Player.
 *
 * Created by:
 * @author Adrian Kristanto
 *
 * Modified by:
 * @author Koe Rui En
 * @author Yang Dan
 *
 */
public class Player extends Actor implements Resettable {

    /**
     * damage to health
     */
    private final int damage;

    /**
     * the chance to hit the target
     */
    private final int hitRate;

    /**
     * instance of ResetManager class that handles resetting the game entities after player dies
     */
    // instance of reset manager
    private final ResetManager resetManager;

    // last location where player stands
    private Location lastLocation;

    // location that the player will be respawned back
    private Location respawnLocation;

    // store map
    private GameMap currMap;

    /**
     * Constructor for the Player class
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints (health attribute)
     * @param stamina     Player's starting number of stamina
     *
     */
    public Player(String name, char displayChar, int hitPoints, int stamina, Location respawnLocation) {

        // initialise actor constructor
        super(name, displayChar, hitPoints);

        // capability of the player
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.ENTER_FLOOR);

        // stamina attributes
        // BaseActorAttribute is basic attribute of player, ie stamina
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));

        // hit rate and damage
        this.damage = 15;
        this.hitRate = 80;

        // initialise the wallet balance
        this.addBalance(5000);

        // get instance of reset manager
        this.resetManager = ResetManager.getInstanceReset();

        // register to reset manager
        resetManager.registerResettable(this, true);

        // initialise respawned location
        this.respawnLocation = respawnLocation;

    }


    // Display method - to dump all states of Player instance
    /**
     * Displays all states of instance of Player class before console menu is printed
     *
     * @return a String that representing all states of instance of Player class
     */
    public String displayStatus(){

        // declare output
        String ret = "";

        // from Actor cls
        ret += name + "\n";
        ret += String.format("HP: %d/%d\n", this.getAttribute(BaseActorAttributes.HEALTH), this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        ret += String.format("Stamina: %d/%d\n", this.getAttribute(BaseActorAttributes.STAMINA), this.getAttributeMaximum(BaseActorAttributes.STAMINA));
        ret += String.format("Runes: %d\n",this.getBalance());

        // return output
        return ret;

    }

    /**
     * Select and return an action to perform at each turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Player
     * @param display    the I/O object to which messages may be written
     *
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // At each turn, the player's stamina will recover by 1% of their maximum stamina
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,(int)(getAttributeMaximum(BaseActorAttributes.STAMINA)* 0.01));

        // Display player's health point and stamina at the beginning of each game loop
        display.println("\n" + displayStatus());

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Create an intrinsic weapon for the Player
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        return new IntrinsicWeapon(damage, "bonks", hitRate);

    }


    /**
     * Method that can be executed when the Player is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the player fell unconscious
     *
     * @return a string describing what happened when the Player is unconscious
     */
    public String unconscious(Actor actor, GameMap map) {

        // print fancy message
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // get player last stood location
        lastLocation = map.locationOf(this);
//        currMap = map;

        // player dies, run reset manager to reset game entities
        resetManager.run();

        // return output
        return "Game is reset";
//        return super.unconscious(actor, map);

    }

    /**
     * Method that can be executed when the Player is unconscious due to natural causes or accident
     *
     * @param map where the Player fell unconscious
     *
     * @return a string describing what happened when the Player is unconscious
     */
    public String unconscious(GameMap map) {

        // print fancy message
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // get player last stood location
        lastLocation = map.locationOf(this);

        // player dies, run reset manager to reset game entities
        resetManager.run();

        // return output
        return "Game is reset";

//        return super.unconscious(map);

    }

    /**
     * Provides a way for any entities be it actors or items or grounds on the GameMap that have to be reset
     * after player dies due to any causes
     */
    // player dies, respawned to location where they started their journey
    @Override
    public void reset() {

        respawnLocation.map().moveActor(this, respawnLocation);

        // respawned location, move actor
//        currMap.moveActor(this, respawnLocation);

        // reset player's attributes
        // maintain the maximum values of player's attributes as before

        // stamina
        int stamina = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, stamina);

        // HP
        int health = this.getAttributeMaximum(BaseActorAttributes.HEALTH);
        modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, health);

        // get current balance
        int currentBalance = this.getBalance();

        // runes in the player’s wallet (not in their inventory) will be reset back to 0.
        this.deductBalance(currentBalance);

        // drop(create) runes at last stood location
        lastLocation.addItem(new Rune(currentBalance));

    }

}
