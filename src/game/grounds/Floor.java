package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Ability;
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
     * Override the method to implement impassable terrain, or terrain that is only passable if conditions are met.
     * To check the actor can pass the terrain or not.
     *
     * @param actor the Actor to check
     * @return boolean value to indicate actor can enter the terrain or not
     */
    // Enemy (Wandering Undead and Hollow Soldier) cannot enter a floor,
    // represented by the display character “_”
    // player can run back to safety if their health is low.
    @Override
    public boolean canActorEnter(Actor actor) {

        // check ability of actor

        // if player, can enter
        return actor.hasCapability(Ability.ENTER_FLOOR);

    }
}
