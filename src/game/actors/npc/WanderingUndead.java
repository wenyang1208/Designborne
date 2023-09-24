// declare package
package game.actors.npc;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.items.HealingVial;
import game.items.OldKey;
import game.utils.Status;

import java.util.ArrayList;


/**
 * A class that represent WanderingUndead enemy in the Abandoned Village
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Yang Dan
 */
public class WanderingUndead extends Enemy {


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
    public WanderingUndead() {

        super("Wandering Undead", 't', 100);

        // can attack the player with its limbs, dealing 30 damage with 50% accuracy
        this.damage = 30;

    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     *
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // get location of the actor to attack
        Location actor = map.locationOf(this);

        // attack player is nearby (within the surrounding of the enemy or one block away from the enemy)
        for (Exit exit: actor.getExits()) {

            // get location of exit
            Location destination = exit.getDestination();

            // check location of exit contains target actor
            if (destination.containsAnActor()){

                // get the target actor
                Actor targetActor = destination.getActor();

                // check status of target actor
                // if player, hostile to enemy status
                // then can attack
                if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)){

                    getBehaviours().put(0, new AttackBehaviour(targetActor, exit.getName()));

                }

            }

        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }

        return new DoNothingAction();
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
     * Return a boolean value after randomly generating a value within the chance to drop an item
     *
     * @param percentage the percentage of chance to drop an item
     *
     * @return a boolean value after randomly generating a value within the chance to drop an item
     */
    @Override
    public boolean dropItemChance(double percentage) {

        return (Math.random() <= percentage);

    }

    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {

        // 25 % drop old key
        if (dropItemChance(0.25)){

            new OldKey().getDropAction(this).execute(this,map);

        }

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }

    // once defeated, also has a 20% chance to drop a healing vial.
    // 25 % drop old key
//    /**
//     * Collect items that created by an enemy once it is defeated
//     *
//     * @return a list of item that created by the defeated enemy
//     */
//    @Override
//    public ArrayList<Item> getDroppedItems() {
//
//        ArrayList<Item> droppedItems = new ArrayList<>();
//
//        if (Math.random() <= 0.25){
//
//            droppedItems.add(new OldKey());
//
//        }
//
//        if (Math.random() <= 0.2){
//
//            droppedItems.add(new HealingVial());
//
//        }
//
//        return droppedItems;
//
//    }


    /**
     * Spawn the WanderingUndead instance
     *
     * @return a new spawned WanderingUndead instance
     */
    @Override
    public Enemy spawnMethod() {

        return new WanderingUndead();

    }




}
