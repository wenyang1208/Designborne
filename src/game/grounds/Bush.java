// declare package
package game.grounds;

// import game package
import game.actors.npc.Enemy;

/**
 * A class that represents the bushes in the Ancient Woods map
 *
 * Created by:
 * @author Koe Rui En
 *
 */
// player attempts to run away to hide in nearby bushes
public class Bush extends Spawner{

    /**
     * Constructor of the Bush class
     *
     * @param enemy enemy to be spawned in certain chance of time
     * @param iniSpawnPercentage percentage to spawn an enemy
     */
    public Bush (Enemy enemy, double iniSpawnPercentage) {

        // initialise instance of bush
        // The hut is displayed as "m".
        super('m', enemy, iniSpawnPercentage);

    }

}
