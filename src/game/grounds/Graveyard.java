// declare package
package game.grounds;

// import engine and game packages
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npc.Enemy;

/**
 * A class that represents the graveyard in the different game maps
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Koe Rui En
 *
 */
// there are several graveyards in the village
public class Graveyard extends Spawner{


    /**
     * Constructor of the Graveyard class
     *
     * @param actor actor to be spawned in certain chance of time
     */
    public Graveyard(Enemy actor, double iniSpawnPercentage) {

        // initialise instance of graveyard
        // The graveyard is displayed as “n”.
        super('n', actor, iniSpawnPercentage);

    }


}

