package game.controllers;


import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npcs.Enemy;
import game.grounds.Spawner;
import game.utils.Ability;

public class WeatherManager {

    private static WeatherManager instanceWeather = null;

    public static WeatherManager getInstance(){

        WeatherManager weather = new WeatherManager();

        if (instanceWeather == null){

            instanceWeather = weather;

        }

        return instanceWeather;
    }

    public static void sunnyWeatherAffect(Spawner spawner, Enemy enemy, GameMap map){

     new Display().println("The weather is sunny...");

     String result = "";

     // check spawner is hut
     if (spawner.hasCapability(Ability.SUNNY)){

         // The huts spawn the “Forest Keeper” enemy at 2 times the original spawning rate
         // (i.e. 30% instead of 15%)
         spawner.setSpawnPercentage(spawner.getSpawnPercentage() * 2);
         result += "The forest keepers are becoming more active\n";

     }

     map.tick();

     result += "The red wolves are becoming less active\n";

     if (enemy.hasCapability(Ability.SUNNY)){

         enemy.updateDamageMultiplier(enemy.getIntrinsicWeapon().damage() * 3);
         result += "The " + enemy + " are becoming more aggressive.\n";

     }

        System.out.println(result);

    }

    public static void rainyWeatherAffect(){

        new Display().println("The weather is rainy...");

    }

    // change weather
    // wrong, maybe has better way??
    public static void toggleWeather(CapabilitySet capabilitySet, Enum<?> capability){

        capabilitySet.removeCapability(capability);

    }

}
