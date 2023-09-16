// declare package
package game.actions;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Gate;
import game.utils.Status;

/**
 * A UnlockAction class that represents an action for an actor to unlock the gate
 *
 * Created by:
 * @author Koe Rui En
 */
public class UnlockAction extends Action {

    // unlock the gate
    /**
     * Instance of gate
     */
    private final Gate gate;

    // name of gate
    /**
     * Name of the gate
     */
    private final String name;

    // constructor
    /**
     * Constructor of the UnlockAction class
     *
     * @param gate the instance of gate
     * @param gateName the name of the gate
     *
     */
    public UnlockAction(Gate gate, String gateName){

        this.gate = gate;
        this.name = gateName;

    }

    /**
     * Perform the Action to unlock the gate.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // declare output
        String ret = "";

        // unlock the gate action
        for (Item item: actor.getItemInventory()) {

            // check item status
            if (item.hasCapability(Status.UNLOCK_GATE)) {

                gate.setGateStatus(true);
                ret = "The " + name + " is opened";
                break;

            }

        }

        // if no item in inventory or key is not found
        if (!gate.getGateStatus()){

            ret = "The " + name + " is locked shut!!";

        }

        // return output
        return ret;


    }

    /**
     * Describe action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        // declare output
        String ret = "";

        ret = actor + " unlocks " + name;

        return ret;

    }
}
