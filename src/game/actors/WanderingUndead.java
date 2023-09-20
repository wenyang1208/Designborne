package game.actors;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.OldKey;

import java.util.ArrayList;


/**
 * class representing the enemy Wandering Undead
 */
public class WanderingUndead extends Enemy {


    /**
     * damage to health
     */
    private final int damage;


    /**
     * Constructor of WanderingUndead
     * It has the ability to drop key and healing vial after defeated by actor
     * It has two types of behaviours (actions) can perform each turn
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
        addBehaviour( 999, new WanderBehaviour() );
        addBehaviour( 500, new AttackBehaviour() );
        this.damage = 30;
    }


    /**
     * Create an intrinsic weapon for the Undead
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "whacks");
    }


    @Override
    public ArrayList<Item> getDroppedItems() {
        ArrayList<Item> droppedItems = new ArrayList<>();
        if ( Math.random() <= 0.25 ){
            droppedItems.add( new OldKey() );
        }
        if ( Math.random() <= 0.2 ){
            droppedItems.add( new HealingVial() );
        }
        return droppedItems;
    }


    @Override
    public Enemy spawnMethod() {
        return new WanderingUndead();
    }


}
