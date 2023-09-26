package game.actors.npc;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.RunesItem;
import game.utils.Status;
import game.weapons.Broadsword;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represent Traveller in the Forest
 * Created by:
 * @author Chai Jun Lun
 *
 */
public class Traveller extends Actor {

    public static List<RunesItem> menu = new ArrayList<RunesItem>();

    /**
     * Constructor for the Traveller class
     *
     */

    public Traveller() {
        super("IsolatedTraveller", 'ඞ', 200);
        // add the items to the traveller to be purchased by actor
        menu.add(new RunesItem(new HealingVial(),100,0.25,1.5));
        menu.add(new RunesItem(new RefreshingFlask(),75,0.1,0.8));
        menu.add(new RunesItem(new Broadsword(),250,0.05,0));
    }

    // Getter for the purchase menu
    public List<RunesItem> getMenu() {
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
        // Add a PurchaseAction if the otherActor is hostile to Enemy(which is player)
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new PurchaseAction(this));
            actions.add(new SellAction(this));
        }
        return actions;
    }
}
