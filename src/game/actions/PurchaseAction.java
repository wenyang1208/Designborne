package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.function.Function;

/**
 * A class represents a PurchaseAction that allows an actor to purchase items from a trader.
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yang Dan
 */
public class PurchaseAction extends Action {

    /**
     * Item to be purchased from the trader
     */
    private Item item;

    /**
     * Function containing the algorithm of purchasing this item from the trader
     */
    private Function<Actor, String> function;

    /**
     * Constructor of the PurchaseAction class
     *
     * @param item Item to be purchased from the trader
     * @param function Function containing the algorithm of purchasing this item from the trader
     */
    public PurchaseAction(Item item, Function<Actor, String> function){
        this.item = item;
        this.function = function;
    }


    /**
     * Perform the Purchase Action
     *
     * When the player purchases this item from the trader, apply the corresponding algorithm
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return function.apply(actor);
    }


    /**
     * Describe what action will be performed in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.item + ".";
    }
}
