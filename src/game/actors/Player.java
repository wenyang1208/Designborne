package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.FancyMessage;
import game.utils.Status;
import game.actions.AttackAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor {


    /**
     * damage to health
     */
    private final int damage;

    /**
     * the chance to hit the target
     */
    private final int hitRate;


    /**
     * Constructor of the Player object
     * @param name name of the player
     * @param displayChar the character that will represent the Player in the display
     * @param hitPoints the Player's starting hit points
     * @param stamina the Player's starting stamina
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        // Add stamina attribute to the player
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.damage = 15;
        this.hitRate = 80;
    }



    private void displayStatus(Display display){
        display.endLine();
        display.println(this.name);
        display.println("HP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        display.println("Stamina: " + this.getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA));
        display.println("Wallet Balance: " + this.getBalance());
    }


    /**
     * Select and return an action to perform at each turn
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Player
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // At each turn, the player's stamina will recover by 1% of their maximum stamina
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int)(0.01 * this.getAttributeMaximum(BaseActorAttributes.STAMINA)));

        // Display player's health point and stamina at the beginning of the each game loop
        displayStatus(display);

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }


    /**
     * Create an intrinsic weapon for the Player
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "bonks", hitRate);
    }


    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Player.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)){
            actions.add(new AttackAction(this, direction)); // Player can be attacked by Enemy
        }
        return actions;
    }


    @Override
    public String unconscious(Actor actor, GameMap map) {
        String string = super.unconscious(actor, map);
        string += "\n\n" + FancyMessage.YOU_DIED;
        return string;
    }


    @Override
    public String unconscious(GameMap map) {
        String string = super.unconscious(map);
        string += "\n\n" + FancyMessage.YOU_DIED;
        return string;
    }
}
