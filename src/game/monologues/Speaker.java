package game.monologues;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * @author yangdan
 *
 * Class representing the actor who can speak monologue
 */
public interface Speaker {

    /**
     * Generates a list of monologues that can be dialogued with the listener
     * @param actor Listener
     * @return List of monologues that can be dialogued with actor
     */
    ArrayList<Monologue> generateMonologues(Actor actor);


}
