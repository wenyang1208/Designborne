package game.actors.npcs;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;

/**
 * A class that represent HollowSoldier enemy in the Burial Ground
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class HollowSoldier extends Enemy implements Spawnable{


    // hollow soldier has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;


    /**
     * Constructor for the HollowSoldier class
     *
     * @param map instance of GameMap class to remove all spawning enemies expect Abxvervyer containing at game map
     *
     */
    // constructor
    public HollowSoldier(GameMap map) {

        // hollow soldier
        // 200 HP, 50 damage, 50 accuracy
        super("Hollow Soldier", '&', 200, new Rune(100), map);

        // can attack the player with its limbs, dealing 50 damage with 50% accuracy.
        this.damage = 50;
    }


    /**
     * Creates and returns an intrinsic weapon for Hollow Soldier with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for HollowSoldier
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for hollow soldier
        return new IntrinsicWeapon(damage, "smacks");

    }


    /**
     * Spawn the HollowSoldier instance
     *
     * @return a new spawned HollowSoldier instance
     */
    @Override
    public Enemy spawnMethod(Location location) {

        return new HollowSoldier(location.map());

    }


    /**
     * Drop items on the ground once the Hollow Soldier is defeated
     *
     * @param map the map containing the Hollow Soldier
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes once defeated
        super.dropItem(map);

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)) {

            new HealingVial().getDropAction(this).execute(this,map);

        }

        // has a 30% chance to drop a refreshing flask
        if (dropItemChance(0.30)){

            new RefreshingFlask().getDropAction(this).execute(this,map);

        }

    }

}
