// declare packages
package game.items;

// import engine and game packages
import edu.monash.fit2099.engine.items.Item;

/**
 * An abstract class that represents a consumable item for the actors to consume
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public abstract class Consumable extends Item implements Healing {


    /***
     * Constructor of the Consumable Class
     *
     * @param name the name of the Item
     * @param displayChar the character to use to represent the Consumable item if it is on the ground
     * @param portable true if and only if the Consumable item can be picked up
     */
    public Consumable(String name, char displayChar, boolean portable) {

        super(name, displayChar, portable);

    }

    //consume

}
