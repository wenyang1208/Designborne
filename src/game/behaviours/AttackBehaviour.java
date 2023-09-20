package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing the behaviour of attacking
 */
public class AttackBehaviour implements Behaviour {


    private final Random random = new Random();


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
        ArrayList<Action> actions = new ArrayList<>();
        for (Exit exit : map.locationOf(actor).getExits()){
            Location destination = exit.getDestination();
            // If there is an actor around, and this actor is player, then add the Attack Action to this player to 'actions'
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                actions.add( new AttackAction(destination.getActor(), exit.getName()) );
            }
        }
        // randomly choose one action
        if (!actions.isEmpty()){
            return actions.get(random.nextInt(actions.size()));
        }
        return null;
    }
}
