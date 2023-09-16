// declare package
package game.items;

// import engine and game packages
import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface class for healing the owner
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public interface Healing {

    // heal method
    /**
     * A healing method to heal the owner when owner consume it
     *
     * @param owner the owner of a healing item
     */
    void heal(Actor owner);

    // uses of healing item
    /**
     * Get the number of uses of the healing item
     *
     * @return an integer containing the number of uses of a healing item
     */
    int getNumberOfUses();

    // set number of uses
    /**
     * Set the number of uses of the healing item
     *
     * @param newUses an integer to set new number of uses of a healing item
     */
    void setNumberOfUses(int newUses);

}
