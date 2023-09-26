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
 * A class that represent RedWolf enemy in the Ancient Woods
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class RedWolf extends Enemy{

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
     * Constructor for the WanderingUndead class
     *
     */
    public RedWolf() {

        // displayed "r", 25 hp
        super("Red Wolf", 'r', 25,new Rune(25));

        // can attack the player by biting them, dealing 15 damage with 80% accuracy.
        this.damage = 15;
        this.hitRate = 80;
        this.addBehaviour(1, new FollowBehaviour());

    }


    /**
     * Creates and returns an intrinsic weapon for Red Wolf with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Red Wolf
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for Red Wolf
        return new IntrinsicWeapon(damage, "bites", hitRate);

    }


    /**
     * Spawn the RedWolf instance
     *
     * @return a new spawned RedWolf instance
     */
    @Override
    public Enemy spawnMethod() {

        return new RedWolf();

    }


    // 10% chance to drop a healing vial once defeated
    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {

        // 100% drop runes
        this.getRunes().getDropAction(this).execute(this,map);

        // 10% chance to drop a healing vial
        if (dropItemChance(0.10)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }


}
