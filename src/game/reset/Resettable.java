// declare package
package game.reset;

/**
 * A Resettable interface to reset all entities including actors, items and grounds
 * in GameMap after player dies due to any causes
 *
 * Created by:
 * @author Koe Rui En
 */
// all classes that need to be reset will implement this class
public interface Resettable {

    /**
     * Provides a way for any entities be it actors or items or grounds on the GameMap that have to be reset
     * after player dies due to any causes
     */
    void reset();

}
