// declare package
package game.items;

// import engine and game packages
import edu.monash.fit2099.engine.items.Item;
import game.utils.Ability;

/**
 * An OldKey class that represents a key for the player to unlock the gate
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class OldKey extends Item {

    /***
     * Constructor for the Old Key class
     *
     */
    // Old Key (represented by “-”)
    public OldKey() {

        super("Old Key", '-', true);

        // add ability of key
        this.addCapability(Ability.UNLOCK_GATE);

    }

}
