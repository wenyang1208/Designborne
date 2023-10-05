package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.HealingVial;
import game.items.Purchasable;
import game.items.RefreshingFlask;
import game.utils.Status;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;

import java.util.ArrayList;

/**
 * A class that represent Isolated Traveller in the Forest
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class Traveller extends Actor{


    /**
     * A list of Purchasable items
     */
    private ArrayList<Purchasable> purchasables = new ArrayList<>();


    /**
     * Constructor of the Traveller class
     *
     */
    public Traveller() {
        super("Isolated Traveller", 'ඞ', 100);
        this.addCapability(Status.TRADER);
        configure();
    }


    /**
     * A method to add items to be carried and sold to other actor into a list of Purchasable items
     */
    private void configure(){
        this.purchasables.add( new HealingVial() );
        this.purchasables.add( new RefreshingFlask() );
        this.purchasables.add( new Broadsword() );
        this.purchasables.add( new GreatKnife() );
    }


    /**
     * Select the valid action with the highest priority
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
     * Returns a new collection of the Actions that the otherActor can do to Traveller
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     *
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions  = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Purchasable purchasable : this.purchasables){
                actions.add( new PurchaseAction(purchasable));
            }
        }
        return actions;
    }
}
