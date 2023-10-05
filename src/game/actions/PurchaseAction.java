package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

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
     * An item to be purchased
     */
    private final Purchasable item;

    /**
     * Constructor of PurchaseAction class.
     *
     * @param item an item to be purchased
     */
    public PurchaseAction(Purchasable item){
        this.item = item;
    }


    /**
     * Perform the purchase action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return string description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return item.purchasedBy(actor);

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

        return actor + " buys " + item + ".";

    }


}
