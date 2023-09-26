package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Traveller;
import game.items.HealingVial;
import game.items.RunesItem;
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
    public List<RunesItem> menu = new ArrayList<RunesItem>();

    /**
     * Constructor to initialize a SellAction.
     *
     * @param traveller The Traveller actor to whom items can be sold.
     */
    public SellAction(Traveller traveller) {
        // Add items that can be sold to the menu
        this.traveller = traveller;
        menu.add(new RunesItem(new HealingVial(),35,0.1,2));
        menu.add(new RunesItem(new RefreshingFlask(),25,0.5,0));
        menu.add(new RunesItem(new Broadsword(), 100,0,1));
//        menu.add(new PricingItem(new Bloodberries(),10,0,1));
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
    public List<RunesItem> getActorSellMenu(Actor actor){
        List <Item> actorInventory = actor.getItemInventory();
        List<RunesItem> actorSellMenu = new ArrayList<RunesItem>();

        for (RunesItem runesItem : menu){
            boolean itemFound = false; //the item status is not defined yet.
            for (Item actorItem : actorInventory) {
                if (actorItem.toString().equals(runesItem.getItem().toString())) {
                    itemFound = true; //iterate the to String to compare, if there is in the list, break out the loop
                    break;
                }
            }
            if (itemFound) {
                actorSellMenu.add(runesItem); // add to the selling menu of actor.
            }
        }
        return actorSellMenu;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        RunesItem runesItem;
        runesItem = showMenu(getActorSellMenu(actor));
        List <Item> actorInventory = actor.getItemInventory();

        //quit menu
        if (runesItem == null){
            return "You have quit the menu.";
        }

        for (Item actorItem : actorInventory) {
            if (actorItem.toString().equals(runesItem.getItem().toString())) {
                // sell item successfully
                actor.addBalance((int) runesItem.getCurrentPrice());
                actor.removeItemFromInventory(actorItem);
                return ("The selling of " + runesItem.getItem() + " is successful. Your balance now is: " + actor.getBalance());
            }
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sell item to " + traveller;
    }
}
