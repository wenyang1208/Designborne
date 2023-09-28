// declare package
package game.behaviours;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.utils.Status;

/**
 * A AttackBehaviour class that represents the attack behaviour of the NPCs
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns an AttackAction to attack an actor that is one block away from it, if possible.
     * If no attack is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            // If there is an actor around, and this actor is player, then add the Attack Action to this player to 'actions'
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                return new AttackAction(destination.getActor(), exit.getName());
            }
        }
        return null;
    }

}

