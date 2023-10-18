package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.monologues.Monologue;
import game.monologues.Speaker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author yangdan
 *
 * A class representing an action to listen monologue
 */
public class ListenAction extends Action {

    /**
     * Speaker to listen to
     */
    private Speaker speaker;

    /**
     * Constructor of the ListenAction object
     * @param speaker speaker to listen to
     */
    public ListenAction(Speaker speaker){
        this.speaker = speaker;
    }

    /**
     * Actor can listen monologue said by speaker
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // get a list of monologues (not all of it can be spoken about)
        ArrayList<Monologue> monologues = speaker.generateMonologues(actor);
        // access the boolean value in each monologue, and filter those with false value
        List<Monologue> filteredMonologues = monologues.stream()
                .filter(Monologue::getCondition)
                .collect(Collectors.toList());
        // At this point, all monologues in the list can be delivered, so choose one randomly
        int index = new Random().nextInt( filteredMonologues.size() );
        return filteredMonologues.get(index).toString();
    }


    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + speaker;
    }

}
