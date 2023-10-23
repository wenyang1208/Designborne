// declare package
package game.grounds;

// import engine and game packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.Ability;
import game.utils.Status;
import game.actions.UnlockAction;

import java.util.HashMap;
import java.util.Map;

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
public class Gate extends Ground implements Resettable {

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

    // map that stores multiple destinations to travel to
    /**
     * a map of collection of destinations to travel to
     */
    private Map<Location, String> mapLocation = new HashMap<>();

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

        // default destination to travel
        addLocation(locationToMove, destinationName);

        // register to reset manager
        ResetManager.getInstanceReset().registerResettable(this);

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

            if (actor.hasCapability(Ability.UNLOCK_GATE) && ! this.locationToMove.containsAnActor()) { // Only actor with the key can travel to the new map

                // loop through map of locations to travel
                for (Map.Entry<Location, String> gameLocation : mapLocation.entrySet()) {
                    actions.add(new TravelAction(gameLocation.getKey(), location.toString(), gameLocation.getValue()));
                }
            }

        }

        // return output
        return actions;

    }

    // add new location to travel to into map of destinations' collection
    /**
     * Add destinations to the map that stores multiple destinations to be travelled
     *
     * @param location location of gate to travel to
     * @param destinationName name of destination to be travelled
     */
    public void addLocation(Location location, String destinationName) {

        this.mapLocation.put(location, destinationName);

    }

    // player dies, unlocked gates will be locked again.
    /**
     * Provides a way for the unlocked gates to be locked again after player dies due to any causes
     * and triggers the game reset
     */
    @Override
    public void reset() {

        // reset status of gate, locked back the gate
        this.addCapability(Status.LOCKED);

    }
}
