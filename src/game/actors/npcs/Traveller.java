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
 * A class that represent Traveller in the Forest
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yang dan
 *
 */
public class Traveller extends Actor{


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


    private void configure(){
        this.purchasables.add( new HealingVial() );
        this.purchasables.add( new RefreshingFlask() );
        this.purchasables.add( new Broadsword() );
        this.purchasables.add( new GreatKnife() );
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions  = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Purchasable purchasable : this.purchasables){
                actions.add( new PurchaseAction(purchasable, purchasable.getPurchasableName()));
            }
        }
        return actions;
    }
}
