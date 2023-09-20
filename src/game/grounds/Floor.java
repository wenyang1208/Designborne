package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }


    /**
     * If actor is Enemy, it can't step on floor
     *
     * @param actor the Actor to check
     * @return true if actor is not Enemy
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !actor.hasCapability(Status.HOSTILE_TO_PLAYER);
    }

}
