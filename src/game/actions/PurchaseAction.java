package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.Traveller;
import game.items.RunesItem;

/**
 * A class represents a PurchaseAction that allows an actor to purchase items from a Traveller.
 *
 * Created by:
 * @author Chai Jun Lun
 */
public class PurchaseAction extends TradingAction{

    /**
     * The Actor that is traveller
     */
    private Traveller traveller;

    /**
     * Constructor of PurchaseAction class.
     *
     * @param traveller the traveller
     */
    public PurchaseAction(Traveller traveller) {
        this.traveller = traveller;
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
        RunesItem runesItem;
        runesItem = showMenu(this.traveller.getMenu());

        //quit menu
        if (runesItem == null){
            return "You have quit the menu.";
        }
        // purchase successfully
        if (runesItem.getCurrentPrice() <= actor.getBalance()) {
            actor.deductBalance((int) runesItem.getCurrentPrice());
            actor.addItemToInventory(runesItem.getItem());
            return ("The purchase of " + runesItem.getItem() + " is successful. Your balance now is: " + actor.getBalance());

            // Purchase failed due to insufficient balance
        } else {
            return (actor + "'s balance is not enough to buy " + runesItem.getItem() + ". Your balance now is: " + actor.getBalance()) ;
        }
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
        return actor + " purchase item from " + traveller;
    }
}
