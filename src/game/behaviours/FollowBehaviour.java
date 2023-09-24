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
     * @param target the target actor to be followed
     */
    public FollowBehaviour(Actor target) {

        // initialise follow behaviour
        this.targetActor = target;

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

        // check the current game map contains actor that has this behaviour or target actor
        if (!map.contains(targetActor) || !map.contains(actor)){
            return null;
        }

        // get location of actor that has this behaviour and the target actor
        Location here = map.locationOf(actor);
        Location there = map.locationOf(targetActor);

        // compute distance between 2 locations of two actors
        int currentDistance = distance(here, there);

        // follow player is nearby (within the surrounding of the enemy)
        for (Exit exit: here.getExits()) {

            // get location of exit
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

}

