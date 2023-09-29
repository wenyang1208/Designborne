// declare package
package game.actors.npcs;

// import game and engine packages
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;

/**
 * A class that represent Isolated Traveller, one of the traders, in the Forest
 *
 * Created by:
 * @author Koe Rui En
 */
public class IsolatedTraveller extends Trader {

    /**
     * The constructor of the IsolatedTraveller class.
     *
     */
    // constructor
    public IsolatedTraveller() {

        super("Isolated Traveller", 'ඞ', 200);

        // add items into the traveller's inventory, with the item's price respectively
        addTraderItem(new HealingVial(), 100);
        addTraderItem(new RefreshingFlask(), 75);
        addTraderItem(new Broadsword(),250);

    }

}
