// declare package
package game.items;

// import game and engine packages
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Consumable interface to consume something
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 */
public interface Consumable {

    /**
     * A consume method to allow owner consume the item
     *
     * @param owner the owner of a consumed item
     */
    String consume(Actor owner);

}
