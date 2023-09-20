// declare package
package game.grounds;

//import engine packages
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

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

        if (location.containsAnActor()){
            Actor actor = location.getActor();
            actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, 0);
            new Display().println( actor + " has stepped into the void" );
            new Display().println( actor.unconscious(location.map()) );
        }

    }


    }

