package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.utils.Ability;
import game.utils.Status;
import game.actions.UnlockAction;

/**
 * class representing gate to a new map
 */
public class Gate extends Ground {


    private String name; //  name of destination to be traveled
    private Location locationToMove; // location of gate to travel to


    /**
     * Constructor of gate
     * It is locked initially
     */
    public Gate(String name, Location locationToMove) {
        super('=');
        this.addCapability(Status.LOCKED);
        this.name = name;
        this.locationToMove = locationToMove;
    }


    /**
     * All entities can step on the 'Gate' ground when it is unlocked.
     *
     * @param actor the Actor to check
     * @return true if the gate is unlocked
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return ! this.hasCapability(Status.LOCKED);
    }



    /**
     * Returns a collection of Actions that an actor can perform when the actor get closer to the gate
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (this.hasCapability(Status.LOCKED)){ // actor can perform Unlock Gate Action when the gate is locked
            return new ActionList( new UnlockAction(this) );
        } else {
            if (actor.hasCapability(Ability.UNLOCK_GATE)){ // Only actor with the key can travel to the new map
                return new ActionList( new TravelAction(locationToMove, location.toString(), name) );
            } else {
                return new ActionList();
            }
        }
    }
}
