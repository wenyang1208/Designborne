package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

public class PurchasingAction extends Action {

    /**
     * An item to be sold
     */
    private final Purchasable item;
    /**
     * The cost of an item to be purchased
     */
    private final int itemCost;

    public PurchasingAction(Purchasable item, int cost) {
        this.item = item;
        this.itemCost = cost;

    }

    /**
     * Perform the Purchasing Action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // declare output
        String ret = "";

        // check actor balance is sufficient to buy the particular item

        if (actor.getBalance() >= itemCost){

            ret += item.purchase(actor, itemCost);

        }

        // transaction fails
        else{

            ret += "Transaction failed; insufficient balance to buy " + item;

        }

        // return output
        return ret;

    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        // declare output
        String ret;

        ret = String.format("%s buys %s.",actor, item);

        // return output
        return ret;

    }
}
