// declare package
package game.grounds;

// import game package
import game.actors.npcs.Spawnable;
import game.utils.Ability;
import game.weather.AffectedByWeather;
import game.weather.WeatherManager;

/**
 * A class that represents the hut in the Ancient Woods map
 *
 * Created by:
 * @author Koe Rui En
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

    @Override
    public String affectedByRainy() {

        // declare output
        String result = "";

        // 1.5 times the original spawning rate (i.e. 45% instead of 30%)
        setSpawnPercentage(getSpawnPercentage() * 1.5);

        result = this + " is becoming less active.";

        // return result
        return result;
    }

    @Override
    public String toString() {

        return "Hut";
    }
}
