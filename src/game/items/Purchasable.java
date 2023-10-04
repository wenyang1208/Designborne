// declare package
package game.items;

// import engine and game packages
import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be purchased by players
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 */
public interface Purchasable {

    /**
     * Get the selling price of the purchasable item
     *
     * @return an integer value representing the purchasable price
     */
    int getPurchasingPrice();

    /**
     * Purchase an item from the trader
     *
     * @param actor Actor who purchase an item at the sale stage
     *
     * @return a string showing the result of purchasing item
     */
    String purchasedBy(Actor actor);


}
