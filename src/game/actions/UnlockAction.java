package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Ability;
import game.grounds.Gate;
import game.utils.Status;

/**
 * class representing an action to unlock the gate
 */
public class UnlockAction extends Action {


    /* Attributes */
    private Gate gate;


    /**
     * Constructor of this UnlockGateAction
     * @param gate Gate to be unlocked
     */
    public UnlockAction(Gate gate){
        this.gate = gate;
    }


    /**
     * Perform unlock action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Ability.UNLOCK_GATE)){ // the player can only unlock the gate when the player has key to the gate
            this.gate.removeCapability(Status.LOCKED); // unlock the gate
            return "Gate is now unlocked.";
        }
        return "Gate is locked shut";
    }


    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }

}
