// declare package
package game.behaviours;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * A AttackBehaviour class that represents the attack behaviour of the NPCs
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class AttackBehaviour implements Behaviour {

    // targeted actor
    /**
     * The target actor
     */
    private Actor targetActor;

    // direction of the targeted actor
    /**
     * The direction of incoming attack.
     */
    private String direction;


    // constructor
    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the target actor to be attacked
     * @param direction the direction where the attack should be performed (only used for display purposes)
     *
     */
    public AttackBehaviour(Actor target, String direction) {

        // initialise attack behaviour
        this.targetActor = target;
        this.direction = direction;

    }

    /**
     * Returns a AttackAction to attack the target actor when surrounding them or one block way
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map   the map that actor is currently on
     *
     * @return an AttackAction, or null if no AttackAction is possible
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        // check the current game map contains actor that has this behaviour or target actor
       if (!map.contains(targetActor) || !map.contains(actor)){
           return  null;
        }


       // create attack action, intrinsic weapon as default
        return new AttackAction(targetActor, direction);


        }

    }

