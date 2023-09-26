// declare package
package game.grounds;

//import engine packages
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

// bottomless pits within the village.
// This bottomless pit is also known as the Void
/**
 * A class that represents the void inside a building
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class Void extends Ground {


    // constructor
    /**
     * Constructor of the Void class
     *
     */
    public Void() {

        // display character “+"
        super('+');

    }

    // player or enemy step on it will die, print you died msg
    /**
     *  Overrides the tick method from abstract ground class.
     *  This is to check every turn whether there is an actor step on it and immediately kill the actor.
     *
     * 	@param location the location of the Ground
     *
     */
    @Override
    public void tick(Location location) {
        String ret  = "";

        // check current location contains actor or not
        if (location.containsAnActor()){

            // get actor at this location
            Actor target = location.getActor();

            ret += target + " has stepped into the void" + "\n";

            // check target is boss or not
            if (!target.hasCapability(Status.FOREST_WATCHER)){

                ret += target.unconscious(location.map());

            }

            // display what happened after this action has been executed
            new Display().println(ret);

        }

    }


}

