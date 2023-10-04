package game.weather;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.Enemy;
import game.grounds.Spawner;
import game.utils.Ability;

import java.util.ArrayList;


public class WeatherManager {

  private static WeatherManager instanceWeather = null;

  // weather turn change
  private static final int WEATHER_TURN_COUNTER = 3;

  // list of classes affected by weather
  private ArrayList<AffectedByWeather> weatherArrayList;

  // initial weather
  private Weather weather = Weather.SUNNY;

  private int weatherCounter = 0;

  public static WeatherManager getWeatherInstance(){

    WeatherManager weather = new WeatherManager();

    if (instanceWeather == null){

      instanceWeather = weather;

    }

    return instanceWeather;
  }

  /**
   * A private constructor
   */
  private WeatherManager() {

    this.weatherArrayList = new ArrayList<>();

  }

  // display weather
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
  public void countWeatherTurn (){

    weatherCounter++;

  }

  // get current weather turn
  public int getWeatherCounter() {

    return weatherCounter;

  }

  // toggle weather
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
  public void registerWeather(AffectedByWeather weatherAffected) {

    weatherArrayList.add(weatherAffected);

  }

  public void unregisterWeather(AffectedByWeather weatherAffected) {

    weatherArrayList.remove(weatherAffected);

  }


  // run weather manger
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


