// declare package
package game.grounds;

// import engine and game packages
import game.actors.npcs.Spawnable;

/**
 * A class that represents the graveyard in the different game maps (Abandoned Village and Burial Ground Maps)
 *
 * Created by:
 * @author Koe Rui En
 *
 */
// there are several graveyards in the village
public class Graveyard extends Spawner{

    /**
     * Constructor of the Graveyard class
     *
     * @param enemy enemy to be spawned in certain chance of time
     * @param iniSpawnPercentage percentage to spawn an enemy
     */
    public Graveyard(Spawnable enemy, double iniSpawnPercentage) {

        // initialise instance of graveyard
        // The graveyard is displayed as “n”.
        super('n', enemy, iniSpawnPercentage);

    }


}

