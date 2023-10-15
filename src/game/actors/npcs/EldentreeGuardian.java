// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.FollowBehaviour;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;
import game.utils.Ability;

/**
 * A class that represent Eldentree Guardian enemy in the Overgrown Sanctuary
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class EldentreeGuardian extends Enemy implements Spawnable{

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
     * The constructor of the EldentreeGuardian class.
     *
     */
    public EldentreeGuardian() {

        // represented by “e”, 250 hitpoints
        // drop 250 runes when defeated.
        super("Eldentree Guardian", 'e', 250, new Rune(250));

        // can attack the player with their limbs, dealing 50 damage with 80% accuracy
        this.damage = 50;
        this.hitRate = 80;
        this.addBehaviour(1, new FollowBehaviour());

        // they can walk around in the Void with no consequences.
        this.addCapability(Ability.STEP_ON_VOID);

    }

    /**
     * Creates and returns an intrinsic weapon for Eldentree Guardianwith different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Eldentree Guardian
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for Eldentree Guardian
        return new IntrinsicWeapon(damage, "puches", hitRate);

    }

    /**
     * Spawn the EldentreeGuardian instance
     *
     * @return a new spawned EldentreeGuardian instance
     */
    @Override
    public Enemy spawnMethod() {

        return new EldentreeGuardian();

    }


    // 25% chance of dropping a healing vial
    // 15% chance of dropping a refreshing flask
    /**
     * Drop items on the ground once the Eldentree Guardian is defeated
     *
     * @param map the map containing the Eldentree Guardian
     */
    @Override
    public void dropItem(GameMap map) {

//        // 100% drop runes once defeated
//        this.getRunes().getDropAction(this).execute(this,map);
        super.dropItem(map);

        // 25% chance to drop a healing vial
        if (dropItemChance(0.25)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

        // 15% chance to drop a refreshing flask
        if (dropItemChance(0.15)){

            new RefreshingFlask().getDropAction(this).execute(this,map);

        }

    }

}
