// declare package
package game.items;

// import game and engine packages
import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Consumable interface
 *
 * Created by:
 * @author Yang Yang Dan
 *
 * Modified by:
 * @author Koe Rui En
 */
public interface Consumable {

    /**
     * A consume method to allow owner consume the item
     *
     * @param owner the owner of a consumed item
     */
    String consume(Actor owner);

}
