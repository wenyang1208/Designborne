package game.actors.npc;

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
import game.items.RefreshingFlask;
import game.utils.Status;

import java.util.ArrayList;


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
        super("Hollow Soldier", '&', 200);

        // can attack the player with its limbs, dealing 50 damage with 50% accuracy.
        this.damage = 50;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // get location of the actor to attack
        Location actor = map.locationOf(this);

        // attack player is nearby (within the surrounding of the enemy or one block away from the enemy)
        for (Exit exit : actor.getExits()) {

            // get location of exit
            Location destination = exit.getDestination();

            // check location of exit contains target actor
            if (destination.containsAnActor()) {

                // get the target actor
                Actor targetActor = destination.getActor();

                // check status of target actor
                // if player, hostile to enemy
                // then can attack
                if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {

                    getBehaviours().put(0, new AttackBehaviour(targetActor, exit.getName()));

                }

            }

        }

        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
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
     * Collect items that created by an enemy once it is defeated
     *
     * @return a list of item that created by the defeated enemy
     */
    @Override
    public ArrayList<Item> getDroppedItems() {

        ArrayList<Item> droppedItems = new ArrayList<>();

        if (Math.random() <= 0.2){

            droppedItems.add(new HealingVial());

        }

        if (Math.random() <= 0.3) {

            droppedItems.add(new RefreshingFlask());

        }

        return droppedItems;
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


}
