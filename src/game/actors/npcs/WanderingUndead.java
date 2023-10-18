// declare package
package game.actors.npcs;

// import game and engine packages
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.OldKey;
import game.items.Rune;


/**
 * A class that represent WanderingUndead enemy in the Abandoned Village
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 */
public class WanderingUndead extends Enemy implements Spawnable{

    // wandering undead has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;

    /**
     * Constructor for the WanderingUndead class
     *
     */
    public WanderingUndead(GameMap map) {

        super("Wandering Undead", 't', 100,new Rune(50), map);

        // can attack the player with its limbs, dealing 30 damage with 50% accuracy
        this.damage = 30;

    }


    /**
     * Creates and returns an intrinsic weapon for Wandering Undead with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Wandering Undead
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for wandering undead
        return new IntrinsicWeapon(damage, "whacks");

    }


    /**
     * Spawn the WanderingUndead instance
     *
     * @return a new spawned WanderingUndead instance
     */
    @Override
    public Enemy spawnMethod(Location location) {

        return new WanderingUndead(location.map());

    }


    /**
     * Drop items on the ground once the Wandering Undead is defeated
     *
     * @param map the map containing the Wandering Undead
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes once defeated
//        this.getRunes().getDropAction(this).execute(this,map);
        super.dropItem(map);

        // 25 % drop old key
        if (dropItemChance(0.25)){

            new OldKey().getDropAction(this).execute(this,map);

        }

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }

}
