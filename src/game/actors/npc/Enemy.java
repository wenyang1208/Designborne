// declare package
package game.actors.npc;

// import game and engine packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.WanderBehaviour;
import game.utils.Status;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class that represents all the enemies in the entities
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Yang Dan
 *
 */
public abstract class Enemy extends Actor {


    /* Attribute */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();


    /**
     * Constructor for the Enemy class
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     *
     */
    public Enemy(String name, char displayChar, int hitPoints) {

        super(name, displayChar, hitPoints);
//        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.behaviours.put(999, new WanderBehaviour());

    }


    public void addBehaviour(Integer i, Behaviour behaviour){
        this.behaviours.put(i, behaviour);
    }


    /**
     * A getter to get the map of behaviours of enemies
     */
    public Map<Integer, Behaviour> getBehaviours() {

        return behaviours;
    }


    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     *
     * @return list of attack actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // declare list of actions
        ActionList actions = new ActionList();

        // default weapon (intrinsic)
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){

            actions.add(new AttackAction(this, direction));

        }

        return actions;

    }

//    @Override
//    public String unconscious(Actor actor, GameMap map) {
//
//        for (Item item : getDroppedItems()){
//            map.locationOf(this).addItem(item);
//        }
//        return super.unconscious(actor, map);
//    }

    @Override
    public String unconscious(Actor actor, GameMap map) {

        dropItem(map);

        return super.unconscious(actor, map);

    }

    // abstract method

    /**
     * Spawn the instance of an enemy
     *
     * @return a new spawned Enemy
     */
    public abstract Enemy spawnMethod();

    /**
     * Return a boolean value after randomly generating a value within the chance to drop an item
     *
     * @param percentage the percentage of chance to drop an item
     *
     * @return a boolean value after randomly generating a value within the chance to drop an item
     */
    public abstract boolean dropItemChance(double percentage);

    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     *
     */
    public abstract void dropItem(GameMap map);

    /**
     * Collect items that created by an enemy once it is defeated
     *
     * @return a list of item that created by the defeated enemy
     */
//    public abstract ArrayList<Item> getDroppedItems();



}
