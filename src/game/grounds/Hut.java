// declare package
package game.grounds;

// import game package
import game.actors.npcs.Spawnable;
import game.weather.AffectedByWeather;
import game.weather.WeatherManager;

/**
 * A class that represents the hut in the different game maps (Ancient Woods and Overgrown Sanctuary Maps)
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chai Jun Lun
 *
 */
// empty huts in Ancient Woods
public class Hut extends Spawner implements AffectedByWeather {

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

        // register to weather manager
        WeatherManager.getWeatherInstance().registerWeather(this);

    }

    /**
     * Effect on Hut when the weather is sunny
     *
     * @return a String description after affected by the sunny weather
     */
    @Override
    public String affectedBySunny() {

        // declare output
        String result = "";

        // at 2 times the original spawning rate (i.e. 30% instead of 15%)
        setSpawnPercentage(getSpawnPercentage() * 2);

        result = this + " is becoming more active.";

        // return result
        return result;
    }

    /**
     * Effect on Hut when the weather is rainy
     *
     * @return a String description after affected by the rainy weather
     */
    @Override
    public String affectedByRainy() {

        // declare output
        String result = "";

        // Huts’ spawning rate returns to normal
        setSpawnPercentage(getIniSpawnPercentage());

        result = this + " is becoming less active.";

        // return result
        return result;
    }


    /**
     * Override toString() from Object class
     *
     * @return a String description of the Hut
     */
    @Override
    public String toString() {

        return "Hut";

    }
}
