// declare package
package game.grounds;

// import game package
import game.actors.npcs.Spawnable;
import game.weather.AffectedByWeather;
import game.weather.WeatherManager;

/**
 * A class that represents the bushes in the Ancient Woods map
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chai Jun Lun
 *
 */
// player attempts to run away to hide in nearby bushes
public class Bush extends Spawner implements AffectedByWeather {

    /**
     * Constructor of the Bush class
     *
     * @param enemy enemy to be spawned in certain chance of time
     * @param iniSpawnPercentage percentage to spawn an enemy
     */
    public Bush (Spawnable enemy, double iniSpawnPercentage) {

        // initialise instance of bush
        // The hut is displayed as "m".
        super('m', enemy, iniSpawnPercentage);

        // register to weather manager
        WeatherManager.getWeatherInstance().registerWeather(this);

    }

    /**
     * Effect on Bush when the weather is sunny
     *
     * @return a String description after affected by the sunny weather
     */
    @Override
    public String affectedBySunny() {

        // declare output
        String result = "";

        // Bushes’ spawning rate returns to normal
        setSpawnPercentage(getIniSpawnPercentage());

        result = this + " is becoming more less active.";

        // return result
        return result;
    }

    /**
     * Effect on Bush when the weather is rainy
     *
     * @return a String description after affected by the rainy weather
     */
    @Override
    public String affectedByRainy() {

        // declare output
        String result = "";

        // 1.5 times the original spawning rate (i.e. 45% instead of 30%)
        setSpawnPercentage(getSpawnPercentage() * 1.5);

        result = this + " is becoming more active.";

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

        return "Bush";
    }

}
