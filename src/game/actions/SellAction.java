package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Traveller;
import game.items.HealingVial;
import game.items.PricingItem;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;

import java.util.*;

/**
 * Represents the SellAction class that allows an actor to sell items to a Traveller.
 *
 * Created by: Chai Jun Lun
 */
public class SellAction extends TradingAction {

    private Traveller traveller;
    public List<PricingItem> menu = new ArrayList<PricingItem>();

    /**
     * Constructor to initialize a SellAction.
     *
     * @param traveller The Traveller actor to whom items can be sold.
     */
    public SellAction(Traveller traveller) {
        // Add items that can be sold to the menu
        this.traveller = traveller;
        menu.add(new PricingItem(new HealingVial(),35,0.1,2));
        menu.add(new PricingItem(new RefreshingFlask(),25,0.5,0));
        menu.add(new PricingItem(new Broadsword(), 100,0,1));
        menu.add(new PricingItem(new Bloodberries(),10,0,1));
    }

    /**
     * Get the menu of items that the actor can sell.
     *
     * @param actor The actor selling the items.
     * @return A list of PricingItem objects representing the items the actor can sell.
     */
    /**
     * Get the menu of items that the actor can sell.
     *
     * @param actor The actor selling the items.
     * @return A list of PricingItem objects representing the items the actor can sell.
     */
    public List<PricingItem> getActorSellMenu(Actor actor){
        List <Item> actorInventory = actor.getItemInventory();
        List<PricingItem> actorSellMenu = new ArrayList<PricingItem>();
        for (PricingItem pricingItem : menu){
            boolean itemFound = false; //the item status is not defined yet.
            for (Item actorItem : actorInventory) {
                if (actorItem.toString().equals(pricingItem.getItem().toString())) {
                    itemFound = true; //iterate the to String to compare, if there is in the list, break out the loop
                    break;
                }
            }
            if (itemFound) {
                actorSellMenu.add(pricingItem); // add to the selling menu of actor.
            }
        }
        return actorSellMenu;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        PricingItem pricingItem;
        pricingItem = showMenu(getActorSellMenu(actor));

        //quit menu
        if (pricingItem == null){
            return "You have quit the menu.";
        }
        // sell item successfully
        actor.addBalance((int) pricingItem.getCurrentPrice());
        actor.removeItemFromInventory(pricingItem.getItem());
        return ("The selling of " + pricingItem.getItem() + " is successful. Your balance now is: " + actor.getBalance());
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sell item to " + traveller;
    }
}
