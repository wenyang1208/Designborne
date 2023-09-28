// declare package
package game.grounds;

// import engine and game packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Ability;
import game.utils.Status;
import game.actions.UnlockAction;

/**
 * A class that represents the gate on the map
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class Gate extends Ground {

    /**
     * name of destination to be travelled
     */
    //  name of destination to be traveled
    private String destinationName;

    // location of gate to travel to
    /**
     * location of gate to travel to
     */
    private Location locationToMove;


    /**
     * Constructor of the Gate class
     *
     * It is locked initially
     *
     * @param name the name of destination to be traveled
     * @param locationToMove the location of gate to teleport
     *
     */
    public Gate(String name, Location locationToMove) {

        super('=');
        this.addCapability(Status.LOCKED);
        this.destinationName = name;
        this.locationToMove = locationToMove;

    }

    /**
     * All entities can step on the 'Gate' ground when it is unlocked.
     *
     * @param actor the Actor to check
     *
     * @return true if the gate is unlocked
     */
    @Override
    public boolean canActorEnter(Actor actor) {

        return !this.hasCapability(Status.LOCKED);

    }

    /**
     * Returns a collection of Actions that an actor can perform when the actor get closer to the gate
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     *
     * @return a collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {

        // declare output
        ActionList actions = new ActionList();

        if (this.hasCapability(Status.LOCKED)){ // actor can perform Unlock Gate Action when the gate is locked
            actions.add(new UnlockAction(this));

        }

        else {

            if (actor.hasCapability(Ability.UNLOCK_GATE) && ! this.locationToMove.containsAnActor()){ // Only actor with the key can travel to the new map
                actions.add(new MoveActorAction(locationToMove, destinationName));
            }

        }

        // return output
        return actions;

    }

}
