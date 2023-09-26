// declare package
package game.actors.npc;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.HealingVial;
import game.items.Rune;
import game.utils.Status;

/**
 * A class that represent ForestKeeper enemy in the Ancient Woods
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class ForestKeeper extends Enemy{


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
     * Constructor for the ForestKeeper class
     *
     */
    public ForestKeeper() {

        // displayed "8", 125 hp
        super("Forest Keeper", '8', 125,new Rune(50));

        // can attack the player with its limbs, dealing 25 damage with 75% accuracy
        this.damage = 25;
        this.hitRate = 75;

        // can follow the player
        this.addBehaviour(1, new FollowBehaviour());

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
    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes
        this.getRunes().getDropAction(this).execute(this,map);

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }

    /**
     * Spawn the ForestKeeper instance
     *
     * @return a new spawned ForestKeeper instance
     */
    @Override
    public Enemy spawnMethod() {

        return new ForestKeeper();

    }

}
