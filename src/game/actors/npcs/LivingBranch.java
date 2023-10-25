//declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Bloodberry;
import game.items.Rune;
import game.utils.Ability;



/**
 * A class that represent Living Branch enemy in the Overgrown Sanctuary
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Chai Jun Lun
 * @author Chua Wen Yang
 */
public class LivingBranch extends Enemy implements Spawnable{


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

    // Key priority of behaviour
    /**
     * Key Priority of Wander Behaviour
     */
    private static final int KEY_PRIORITY_999 = 999;

    // drop healing vial percentage
    /**
     * percentage to drop bloodberry 50%
     */
    private static final double DROP_BLOODBERRY_PERCENTAGE = 0.50;

    /**
     * constant damage done of the Living Branch
     */
    private static final int DEFAULT_DAMAGE_OF_LIVING_BRANCH = 250;

    /**
     * constant hit rate of the Living Branch
     */
    private static final int DEFAULT_HIT_RATE_OF_LIVING_BRANCH = 90;


    /**
     * The constructor of the LivingBranch class.
     *
     * @param map instance of GameMap class to remove all spawning enemies expect Abxvervyer containing at game map
     *
     */
    public LivingBranch(GameMap map) {

        // represented by “?”, 75 hitpoints
        // drop 500 runes when defeated.
        super("Living Branch", '?', 75, new Rune(500), map);

        // can attack the player with their limbs, dealing 250 damage with 90% accuracy
        this.damage = DEFAULT_DAMAGE_OF_LIVING_BRANCH;
        this.hitRate = DEFAULT_HIT_RATE_OF_LIVING_BRANCH;

        // this enemy cannot wander and follow
        // 999 -> Wander Behaviour
        this.removeBehaviour(KEY_PRIORITY_999);

        // they can walk around in the Void with no consequences.
        this.addCapability(Ability.STEP_ON_VOID);


    }

    /**
     * Creates and returns an intrinsic weapon for Living Branch with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Living Branch
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for Living Branch
        return new IntrinsicWeapon(damage, "pokes", hitRate);

    }

    /**
     * Spawn the LivingBranch instance
     *
     * @param location spawner location to spawn enemies
     *
     * @return a new spawned LivingBranch instance
     */
    @Override
    public Enemy spawnMethod(Location location) {

        return new LivingBranch(location.map());

    }


    // 50% chance of dropping a Bloodberry.
    /**
     * Drop items on the ground once the Living Branch is defeated
     *
     * @param map the map containing the Living Branch
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes once defeated
        super.dropItem(map);

        // 50% chance of dropping a Bloodberry.
        if (dropItemChance(DROP_BLOODBERRY_PERCENTAGE)) {

            new Bloodberry().getDropAction(this).execute(this, map);

        }

    }
}
