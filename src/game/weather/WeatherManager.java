// declare package
package game.weather;

// import game and engine packages
import edu.monash.fit2099.engine.displays.Display;


import java.util.ArrayList;

/**
 * A Weather Manager class that handles all the changes of weathers
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chua Wen Yang
 *
 */
public class WeatherManager {

  /**
   * instance of WeatherManager class
   */
  private static WeatherManager instanceWeather = null;

  // weather turn change
  /**
   * constant turns of weather to be changed
   */
  private static final int WEATHER_TURN_COUNTER = 3;

  // list of classes affected by weather
  /**
   * a list of classes that affected by weather
   */
  private ArrayList<AffectedByWeather> weatherArrayList;

  /**
   * initial state of weather
   */
  // initial weather
  private Weather weather = Weather.SUNNY;

  /**
   * counter to count the turns of weather
   */
  private int weatherCounter = 0;

  /**
   * A factory method that ensures only one instance of the WeatherManager class can be created
   *
   * @return an instance of the WeatherManager class
   */
  public static WeatherManager getWeatherInstance(){

    WeatherManager weather = new WeatherManager();

    if (instanceWeather == null){

      instanceWeather = weather;

    }

    return instanceWeather;
  }

  /**
   * A private constructor to set the list of classes that affected by weather
   */
  private WeatherManager() {

    this.weatherArrayList = new ArrayList<>();

  }

  // display weather
  /**
   * A method to display the description of the current weather
   *
   * @param display the I/O object to which messages may be written
   */
  public void displayWeather(Display display) {

    switch (weather) {

      // sunny weather
      case SUNNY -> {
        display.println("The weather is sunny...");
        break;
      }

      // rainy weather
      case RAINY -> {
        display.println("The weather is rainy...");
        break;
      }

      default -> display.println("Invalid weather");

    }
  }

  // count weather turn
  /**
   * A method to count the turn of weather
   */
  public void countWeatherTurn (){

    weatherCounter++;

  }

  // get current weather turn
  /**
   * A getter to get the current weather's counter
   *
   * @return an integer representing counter of the turn of weather
   */
  public int getWeatherCounter() {

    return weatherCounter;

  }

  // toggle weather
  /**
   * A method to toggle weather
   */
  public void toggleWeather(){

    switch(weather){

      case SUNNY -> {
        weather = Weather.RAINY;
        break;
      }

      case RAINY -> {
        weather = Weather.SUNNY;
        break;
      }

      default -> weather = null;
    }

  }

  // register class that affected by weather
  /**
   * A method to add an actor or ground that affected by weather to a list of AffectedByWeathers
   *
   * @param weatherAffected an actor or ground that may be affect by the current weather to be added
   */
  public void registerWeather(AffectedByWeather weatherAffected) {

    weatherArrayList.add(weatherAffected);

  }


  /**
   * A method to remove an actor or ground that affected by weather to a list of AffectedByWeathers
   *
   * Example: the actor may be unconscious due to defeated or dead naturally, remove from list
   *
   * @param weatherAffected an actor or ground that may be affect by the current weather to be removed
   */
  public void unregisterWeather(AffectedByWeather weatherAffected) {

    weatherArrayList.remove(weatherAffected);

  }


  // run weather manager
  /**
   * Run the weather manager when it is called
   *
   * @param display the I/O object to which messages may be written
   */
  public void run(Display display){

    // count the turn
    countWeatherTurn();

    // change weather for every 3 turns
    if (getWeatherCounter() % WEATHER_TURN_COUNTER == 0){

      toggleWeather();

    }

    // display weather
    displayWeather(display);

    // class affected by weather
    // loop through list
    if (weather == Weather.SUNNY) {
      for (AffectedByWeather affectedByWeather : weatherArrayList) {

        display.println(affectedByWeather.affectedBySunny());
      }

    }

    else {

      for (AffectedByWeather affectedByWeather : weatherArrayList) {

        display.println(affectedByWeather.affectedByRainy());

      }

    }

  }

}


