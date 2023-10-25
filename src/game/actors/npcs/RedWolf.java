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
 * A class that represent RedWolf enemy in the Ancient Woods
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class RedWolf extends Enemy implements Spawnable, AffectedByWeather {

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
     * default damage multiplier
     */
    private float damageMultiplier = 1.0f;

    // Key priority of behaviour
    /**
     * Key Priority of 1
     */
    private static final int KEY_PRIORITY_1 = 1;

    // drop healing vial percentage
    /**
     * percentage to drop healing vial 10%
     */
    private static final double DROP_HEALING_VIAL_PERCENTAGE = 0.10;

    /**
     * Constructor for the RedWolf class
     *
     * @param map instance of GameMap class to remove all spawning enemies expect Abxvervyer containing at game map
     */
    public RedWolf(GameMap map) {

        // displayed "r", 25 hp
        super("Red Wolf", 'r', 25,new Rune(25), map);

        // can attack the player by biting them, dealing 15 damage with 80% accuracy.
        this.damage = 15;
        this.hitRate = 80;
        this.addBehaviour(KEY_PRIORITY_1, new FollowBehaviour());

        // register to weather manager
        WeatherManager.getWeatherInstance().registerWeather(this);

    }

    /**
     * Creates and returns an intrinsic weapon for Red Wolf with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Red Wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        int damage = Math.round(this.damage * damageMultiplier);

        // create new intrinsic weapon for Red Wolf
        return new IntrinsicWeapon(damage, "bites", hitRate);

    }


    /**
     * Spawn the RedWolf instance
     *
     * @param location spawner location to spawn enemies
     *
     * @return a new spawned RedWolf instance
     */
    @Override
    public Enemy spawnMethod(Location location) {

        return new RedWolf(location.map());

    }


    // 10% chance to drop a healing vial once defeated
    /**
     * Drop items on the ground once the Red Wolf is defeated
     *
     * @param map the map containing the Red Wolf
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes once defeated
        super.dropItem(map);

        // 10% chance to drop a healing vial
        if (dropItemChance(DROP_HEALING_VIAL_PERCENTAGE)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

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
     * Method that can be executed when the Red Wolf is unconscious due to natural causes or accident
     *
     * @param map where the actor fell unconscious
     *
     * @return a string describing what happened when the Red Wolf is unconscious
     */
    public String unconscious(GameMap map) {

        WeatherManager.getWeatherInstance().unregisterWeather(this);

        return super.unconscious(map);

    }

    /**
     * Effect on Red Wolf when the weather is sunny
     *
     * @return a String description after affected by the sunny weather
     */
    @Override
    public String affectedBySunny() {

        // declare output
        String result = "";

        // deals 3 times the original damage when attacking the player
        // (i.e. 45% instead of 30%)
        this.damageMultiplier = 3.0f;

        result = this + " is becoming more aggressive.";

        // return result
        return result;
    }

    /**
     * Effect on Red Wolf when the weather is rainy
     *
     * @return a String description after affected by the rainy weather
     */
    @Override
    public String affectedByRainy() {

        String result = "";

        // reset back to ori state
        this.damageMultiplier = 1.0f;

        result = this + " is becoming less aggressive.";

        // return result
        return result;

    }


}
