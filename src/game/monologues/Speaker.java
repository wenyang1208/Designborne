package game.monologues;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * An interface class representing the actor who can speak monologue
 *
 * Created by:
 * @author Yang Dan
 *
 */
public interface Speaker {

    /**
     * Generates a list of monologues that can be dialogued with the listener
     *
     * @param actor the listener (player)
     *
     * @return a list of monologues that can be dialogued with actor
     */
    ArrayList<Monologue> generateMonologues(Actor actor);


}
