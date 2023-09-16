// declare package
package game.grounds;


// import engine
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.actions.TravelAction;
import game.actions.UnlockAction;


/**
 * A class that represents the gate on the map
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class Gate extends Ground {

    //  name of destination to be traveled
    /**
     * name of destination to be travelled
     */
    private String name;

    // location of gate to travel to
    /**
     * location of gate to travel to
     */
    private Location locationToMove;

    // gate status
    /**
     * status of gate (true: open, false: close)
     */
    private boolean gateStatus;


    /**
     * Constructor of the Gate class
     *
     * @param name the name of destination to be traveled
     * @param location the location of gate to teleport
     *
     */
    public Gate (String name, Location location){

        super('=');
        this.name = name;
        this.locationToMove = location;
        setGateStatus(false);
    }

    // getter
    /**
     * A getter to get the status of the gate
     */
    public boolean getGateStatus() {return gateStatus;}

    // setter
    /**
     * A setter to set the status of the gate
     *
     * @param newStatus a boolean to indicate the status of the gate
     */
    public void setGateStatus(boolean newStatus){

        gateStatus = newStatus;

    }

    // create action to move the actor is actor has old key
    // create action to unlock the gate
    /**
     * Returns Action list to allow actors act on it.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     *
     * @return a new collection of Actions
     */
    public ActionList allowableActions(Actor actor, Location location, String direction){

        // declare output
        ActionList actions = new ActionList();

        // add actions to list

        // check gate is opened or not
        if (getGateStatus()) {

            // other actor except player cannot travel
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

                actions.add(new TravelAction(locationToMove, location.toString(), name));

            }

        }

        // unlock the gate
        else{

            actions.add(new UnlockAction(this, "Gate"));

        }




        // return output
        return actions;

    }





}
