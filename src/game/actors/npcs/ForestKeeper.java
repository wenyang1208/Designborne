// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
import game.items.HealingVial;
import game.items.Rune;
import game.weather.AffectedByWeather;
import game.weather.WeatherManager;

/**
 * A class that represent ForestKeeper enemy in the Ancient Woods
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class ForestKeeper extends Enemy implements Spawnable, AffectedByWeather {

    // forest keeper has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;

    // hit rate/accuracy
    /**
     * hit rate to other actor
     */
    private final int hitRate;

    /**
     * Key priority of follow behaviour
     */
    private static final int KEY_PRIORITY_1  = 1;

    /**
     * percentage to drop healing vial 20%
     */
    private static final double DROP_HEALING_VIAL_PERCENTAGE  = 0.20;

    /**
     * healing point of 10
     */
    private static final int HEALING_POINT  = 10;

    /**
     * Constructor for the ForestKeeper class
     *
     */
    public ForestKeeper(GameMap map) {

        // displayed "8", 125 hp
        super("Forest Keeper", '8', 125,new Rune(50), map);

        // can attack the player with its limbs, dealing 25 damage with 75% accuracy
        this.damage = 25;
        this.hitRate = 75;

        // can follow the player
        this.addBehaviour(KEY_PRIORITY_1, new FollowBehaviour());

        // register to weather manager
        WeatherManager.getWeatherInstance().registerWeather(this);

    }

    /**
     * Creates and returns an intrinsic weapon for Forest Keeper with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Forest Keeper
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for forest keeper
        return new IntrinsicWeapon(damage, "whacks", hitRate);

    }

    // 20% chance to drop a healing vial once defeated
    // 100% chance to drop runes once defeated
    /**
     * Drop items on the ground once the Forest Keeper is defeated
     *
     * @param map the map containing the Forest Keeper
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes once defeated
        super.dropItem(map);

        // 20% chance to drop a healing vial
        if (dropItemChance(DROP_HEALING_VIAL_PERCENTAGE)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }

    /**
     * Spawn the ForestKeeper instance
     *
     * @param location spawner location to spawn enemies
     *
     * @return a new spawned ForestKeeper instance
     */
    @Override
    public Enemy spawnMethod(Location location) {

        return new ForestKeeper(location.map());

    }

    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     *
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {

        WeatherManager.getWeatherInstance().unregisterWeather(this);

        return super.unconscious(actor, map);

    }

    /**
     * Method that can be executed when the Forest Keeper is unconscious due to natural causes or accident
     *
     * @param map where the actor fell unconscious
     *
     * @return a string describing what happened when the Forest Keeper is unconscious
     */
    public String unconscious(GameMap map) {

        WeatherManager.getWeatherInstance().unregisterWeather(this);

        return super.unconscious(map);

    }


    /**
     * Effect on Forest Keeper when the weather is sunny
     *
     * @return a String description after affected by the sunny weather
     */
    @Override
    public String affectedBySunny() {
        return this + " does not feel anything";
    }

    /**
     * Effect on Forest Keeper when the weather is rainy
     *
     * @return a String description after affected by the rainy weather
     */
    @Override
    public String affectedByRainy() {
        // healed by 10 points
        this.heal(HEALING_POINT);
        return this + " feels rejuvenated.";
    }
}
