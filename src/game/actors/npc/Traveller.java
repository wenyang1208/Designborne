package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.PurchaseItem;
import game.utils.Status;
import game.weapons.Broadsword;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represent Traveller in the Forest
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chai Jun Lun
 *
 */
public class Traveller extends Actor {

    public static List<PurchaseItem> menu = new ArrayList<PurchaseItem>();

    /**
     * Constructor for the Traveller class
     *
     */
    // constructor
    public Traveller() {
        super("IsolatedTraveller", 'ඞ', 200);
        menu.add(new PurchaseItem(new HealingVial(),100,0.25,1.5));
        menu.add(new PurchaseItem(new RefreshingFlask(),75,0.1,0.8));
        menu.add(new PurchaseItem(new Broadsword(),250,0.05,0));
    }

    public void printMenu() {
        for (int i =0; i <menu.size(); i++){
             new Display().println("Item("+ (i+1) +")");
             new Display().println(menu.get(i).toString());
        }
    }


    public List<PurchaseItem> getMenu() {
        return menu;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to Traveller
     * @param otherActor the Actor that might buy /sell inventory
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new PurchaseAction(this));
        }
        return actions;
    }
}
