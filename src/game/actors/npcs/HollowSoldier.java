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
 * @author Chai Jun Lun
 * @author Chua Wen Yang
 */
public class HollowSoldier extends Enemy implements Spawnable{


    // hollow soldier has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;

    // drop healing vial percentage
    /**
     * percentage to drop healing vial 20%
     */
    private static final double DROP_HEALING_VIAL_PERCENTAGE = 0.20;

    // drop refreshing flask percentage
    /**
     * percentage to drop refreshing flask 30%
     */
    private static final double DROP_REFRESHING_FLASK_PERCENTAGE = 0.30;

    /**
     * constant damage done of the Hollow Soldier
     */
    private static final int DEFAULT_DAMAGE_OF_HOLLOW_SOLDIER = 50;

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
        this.damage = DEFAULT_DAMAGE_OF_HOLLOW_SOLDIER;
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
        if (dropItemChance(DROP_HEALING_VIAL_PERCENTAGE)) {

            new HealingVial().getDropAction(this).execute(this,map);

        }

        // has a 30% chance to drop a refreshing flask
        if (dropItemChance(DROP_REFRESHING_FLASK_PERCENTAGE)){

            new RefreshingFlask().getDropAction(this).execute(this,map);

        }

    }

}
