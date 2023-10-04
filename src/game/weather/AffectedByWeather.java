// declare package
package game.weather;

// class affect by weather will implement this class
/**
 * An interface representing effect of weathers on those affected actors or grounds
 *
 * Created:
 * @author Koe Rui En
 */
public interface AffectedByWeather {

    /**
     * Effect on the affected actors or grounds when the weather is sunny
     *
     * @return a String description after affected by the sunny weather
     */
    String affectedBySunny();

    /**
     * Effect on the affected actors or grounds when the weather is rainy
     *
     * @return a String description after affected by the rainy weather
     */
    String affectedByRainy();

}
