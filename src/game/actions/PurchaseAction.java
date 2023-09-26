package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Traveller;
import game.items.PricingItem;
import java.util.Map;

/**
 * Represents a PurchaseAction that allows an actor to purchase items from a Traveller.
 *
 * Constructor:
 * @param traveller The Traveller actor from whom items can be purchased.
 */
public class PurchaseAction extends TradingAction{

    /**
     * The Actor that is traveller
     */
    private Traveller traveller;

    /**
     * Constructor.
     *
     * @param traveller the traveller
     */
    public PurchaseAction(Traveller traveller) {
        this.traveller = traveller;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        PricingItem pricingItem;
        pricingItem = showMenu(this.traveller.getMenu());

        //quit menu
        if (pricingItem == null){
            return "You have quit the menu.";
        }
        // purchase successfully
        if (pricingItem.getCurrentPrice() <= actor.getBalance()) {
            actor.deductBalance((int) pricingItem.getCurrentPrice());
            actor.addItemToInventory(pricingItem.getItem());
            return ("The purchase of " + pricingItem.getItem() + " is successful. Your balance now is: " + actor.getBalance());

            // Purchase failed due to insufficient balance
        } else {
            return (actor + "'s balance is not enough to buy " + pricingItem.getItem() + ". Your balance now is: " + actor.getBalance()) ;
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchase item from " + traveller;
    }
}
