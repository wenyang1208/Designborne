// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchasingAction;
import game.items.Purchasable;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;


/**
 * An abstract class that representing traders on the map
 *
 * Created by:
 * @author Koe Rui En
 */
public abstract class Trader extends Actor {

    // map of items and prices to be sold or purchased
    private Map<Purchasable, Integer> traderItem = new HashMap<>();

    /**
     * The constructor of the Trader class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {

        super(name, displayChar, hitPoints);

    }

    // to add item into trader's inventory
    /**
     * A method to add items to be sold or into the trader's inventory
     *
     */
    public void addTraderItem(Purchasable item, int itemPrice) {

        traderItem.put(item, itemPrice);

    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     *
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return new DoNothingAction();

    }

    /**
     * Returns a new collection of the Actions that the otherActor(player) can do to the current Actor (trader)
     *
     * Player purchase items that offered by the trader
     *
     * @param otherActor the Actor(player) who performing purchasing action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     *
     * @return A collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // player purchase items from trader
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (Map.Entry<Purchasable, Integer> entry : traderItem.entrySet()) {
                actions.add(new PurchasingAction(entry.getKey(), entry.getValue()));
            }
        }

        return actions;

    }

}
