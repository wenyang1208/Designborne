// declare package
package game.actions;

// import game and engine packages
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A TravelAction class that represents an action for an actor to be travelled to another location
 *
 * Created by:
 * @author Koe Rui En
 */
public class TravelAction extends MoveActorAction {

    /**
     * Name of the destination of the move
     */
    private String travelDestinationName;


    /**
     * Constructor to create an Action that will move the Actor to a Location.
     *
     * @param moveToLocation the destination of the move
     * @param direction      the direction of the move (to be displayed in menu)
     * @param travelDestination the name of the destination of the move as String
     */
    public TravelAction(Location moveToLocation, String direction, String travelDestination) {

        super(moveToLocation, direction);
        this.travelDestinationName = travelDestination;

    }

    /**
     * Returns a description of the destination that actor travelled to display in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return a String, where the destination the actor travel
     */
    @Override
    public String menuDescription(Actor actor) {

        String ret = "";

        ret = actor + " travels to " + travelDestinationName;

        return ret;

    }





}
