// declare package
package game.grounds;

// import game package
import game.actors.npcs.Spawnable;
import game.utils.Ability;

/**
 * A class that represents the hut in the Ancient Woods map
 *
 * Created by:
 * @author Koe Rui En
 *
 */
// empty huts in Ancient Woods
public class Hut extends Spawner{

    /**
     * Constructor of the Hut class
     *
     * @param enemy enemy to be spawned in certain chance of time
     * @param iniSpawnPercentage percentage to spawn an enemy
     */
    public Hut (Spawnable enemy, double iniSpawnPercentage) {

        // initialise instance of hut
        // The hut is displayed as "h".
        super('h', enemy, iniSpawnPercentage);

        this.addCapability(Ability.SUNNY);

    }

}
