// declare package
package game.grounds;

// import engine and game packages
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npcs.Spawnable;

/**
 * An abstract class to spawn different enemies in the different game maps
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chua Wen Yang
 *
 */
public abstract class Spawner extends Ground {

    // declare instance variables

    // save attributes of enemies
    /**
     * enemy to be spawned
     */
    private Spawnable spawnedEnemy;

    // spawn percentage
    /**
     * percentage to spawn an enemy
     */
    private double spawnPercentage;

    // initial spawn percentage
    /**
     * initial percentage to spawn an enemy
     */
    private double iniSpawnPercentage;

    private Location newLocation;

    /**
     * Constructor of the abstract Spawner class
     *
     * @param displayChar character to display for this type of terrain
     * @param enemy enemy to be spawned in certain chance of time
     * @param iniSpawnPercentage the percentage to spawn an enemy
     *
     */
    public Spawner(char displayChar, Spawnable enemy, double iniSpawnPercentage) {

        super(displayChar);

        spawnedEnemy = enemy;

        setSpawnPercentage(iniSpawnPercentage);

        this.iniSpawnPercentage = iniSpawnPercentage;

    }

    // set spawn percentage
    /**
     * A setter to set the spawn percentage to spawn an enemy
     *
     * @param newSpawnPercentage the new spawn percentage to spawn an enemy
     */
    public void setSpawnPercentage(double newSpawnPercentage) {

        if (spawnPercentage >= 0){

            spawnPercentage = newSpawnPercentage;

        }
    }

    /**
     * A getter to get the spawn percentage to spawn an enemy
     *
     * @return a double that representing the spawn percentage to spawn an enemy
     */
    public double getSpawnPercentage() {

        return spawnPercentage;

    }

    /**
     * A getter to get the initial spawn percentage to spawn an enemy
     *
     * @return a double that representing the initial spawn percentage to spawn an enemy
     */
    public double getIniSpawnPercentage (){

        return iniSpawnPercentage;
    }

    /**
     * Spawner abstract class can also experience the joy of time.
     * A chance to spawn different enemies at every turn of game
     *
     * @param location The location of the ground that extends Spawner abstract class
     */
    @Override
    public void tick(Location location) {

        // location enemies spawned
        newLocation = location;

        // check location contains enemies and chance to spawn at certain percentage at every turn of game
        if (Math.random() <= spawnPercentage && !location.containsAnActor()) {

            // add spawned enemies to the location
            location.addActor(spawnedEnemy.spawnMethod(newLocation));

        }

    }


}
