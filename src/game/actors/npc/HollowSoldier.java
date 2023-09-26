package game.actors.npc;

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
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;
import game.utils.Status;

/**
 * A class that represent HollowSoldier enemy in the Burial Ground
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Yang Dan
 *
 */
public class HollowSoldier extends Enemy {


    // hollow soldier has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;


    /**
     * Constructor for the HollowSoldier class
     *
     */
    // constructor
    public HollowSoldier() {

        // hollow soldier
        // 200 HP, 50 damage, 50 accuracy
        super("Hollow Soldier", '&', 200, new Rune(100));

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
    public Enemy spawnMethod() {

        return new HollowSoldier();

    }


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
        if (dropItemChance(0.20)) {

            new HealingVial().getDropAction(this).execute(this,map);

        }


        // has a 30% chance to drop a refreshing flask
        if (dropItemChance(0.30)){

            new RefreshingFlask().getDropAction(this).execute(this,map);

        }

    }

}
