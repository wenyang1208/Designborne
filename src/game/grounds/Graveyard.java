// declare package
package game.grounds;

// import engine and game packages
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Enemy;

/**
 * A class that represents the graveyard in the different game maps
 *
 * Created by:
 * @author Koe Rui En
 *
 */
// there are several graveyards in the village
public class Graveyard extends Ground{

    // declare instance variables

    // save attributes of enemies
    /**
     * location of gate to travel to
     */
    private Enemy enemy;

    // spawn percentage
    private double spawnPercentage;


    /**
     * Parameterised constructor of the Graveyard class
     *
     * @param actor actor to be spawned in certain chance of time
     */
    public Graveyard(Enemy actor, double iniSpawnPercentage) {

        // initialise instance of graveyard
        // The graveyard is displayed as “n”.
        super('n');

        enemy = actor;

        spawnPercentage = iniSpawnPercentage;


    }


    /**
     * Graveyard can also experience the joy of time.
     * A chance to spawn either Hollow Soldier or Wandering Undead at every turn of game
     *
     * @param location The location of the Graveyard
     */
    @Override
    public void tick(Location location) {


        // check location contains wandering undead and chance to spawn with 25%  at every turn of game
        if (Math.random() <= spawnPercentage && !location.containsAnActor()) {

            // spawn the wandering undead
            // add spawned enemies to the location
            location.addActor(enemy.spawnMethod());

        }

    }










}

