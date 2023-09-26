// declare package
package game.behaviours;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

/**
 * A FollowBehaviour class that represents the follow behaviour of the enemies in the Ancient Woods.
 * It figures out a MoveAction that will move the actor one step closer to a target Actor
 * @see edu.monash.fit2099.demo.mars.Application
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class FollowBehaviour implements Behaviour {

    // target actor
    /**
     * The target actor to be followed
     */
    private Actor targetActor;

    // constructor
    /**
     * Constructor of the FollowBehaviour class
     *
     */
    public FollowBehaviour() {

        // initialise follow behaviour
        this.targetActor = null;

    }

    /**
     * Returns a MoveActorAction to follow the target actor (player) within the surrounding of the enemies
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     *
     * @return an MoveActorAction, or null if no MoveActorAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // get location of actor(enemies) that has this behaviour
        Location here = map.locationOf(actor);

        // get target actor when it is null
        if (targetActor == null) {

            // get all exit of actor
            for (Exit exit: here.getExits()) {

                // get location of exit
                Location destination = exit.getDestination();

                // check location contains target actor
                if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){

                    targetActor = destination.getActor();

                    break;

                }

            }

        } else { // got target actor

            // check the current game map contains actor that has this behaviour or target actor
            if (!map.contains(targetActor) || !map.contains(actor)) {

                return null;

            }

            Location there = map.locationOf(targetActor);

            // compute distance between 2 locations of two actors
            int currentDistance = distance(here, there);

            if ( isTargetAround(here, there, currentDistance) )
                return null;

            // follow player is nearby (within the surrounding of the enemy)
            for (Exit exit : here.getExits()) {

                // get location of exit containing player
                Location destination = exit.getDestination();

                // check this location that actor(enemies) can enter or not
                if (destination.canActorEnter(actor)) {

                    // compute new distance between 2 locations of two actors
                    int newDistance = distance(destination, there);

                    // new distance between enemies and target actor < current distance
                    if (newDistance < currentDistance) {

                        return new MoveActorAction(destination, exit.getName());

                    }
                }
            }
        }

            return null;

    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     *
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {

        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());

    }


    private boolean isTargetAround(Location a, Location b, int distance){
        boolean b1 = (a.x() == b.x() || a.y() == b.y())  &&  distance == 1;
        boolean b2 = (a.x() != b.x() || a.y() != b.y())  &&  distance == 2;
        return b1 || b2;
    }

}

