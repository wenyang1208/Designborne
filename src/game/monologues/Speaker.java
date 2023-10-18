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
     * Generates a collection of monologues for an Actor based on their capabilities and the game context.
     *
     * @param actor The Actor for whom the monologues are being generated.
     *
     * @return An ArrayList of Monologue objects containing dialogues tailored to the Actor's capabilities and the game context.
     */
    ArrayList<Monologue> generateMonologues(Actor actor);


}
