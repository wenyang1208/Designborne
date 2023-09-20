package game.actors;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.RefreshingFlask;

import java.util.ArrayList;


/**
 * class representing the enemy Hollow Soldier
 */
public class HollowSoldier extends Enemy {


    /**
     * damage to health
     */
    private final int damage;


    /**
     * Constructor of HollowSoldier
     * It has the ability to drop healing vial and refreshing flask after defeated by actor
     * It has two types of behaviours (actions) can perform each turn
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        addBehaviour( 999, new WanderBehaviour() );
        addBehaviour( 500, new AttackBehaviour() );
        this.damage = 50;
    }


    /**
     * Create an intrinsic weapon for the Hollow Soldier
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "smacks");
    }


    @Override
    public ArrayList<Item> getDroppedItems() {
        ArrayList<Item> droppedItems = new ArrayList<>();
        if ( Math.random() <= 0.2 ){
            droppedItems.add( new HealingVial() );
        }
        if ( Math.random() <= 0.3 ){
            droppedItems.add( new RefreshingFlask() );
        }
        return droppedItems;
    }


    @Override
    public Enemy spawnMethod() {
        return new HollowSoldier();
    }


}
