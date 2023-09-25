// declare package
package game.actors.npc;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.Status;
import game.actions.AttackAction;

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
    private Map<Integer, Behaviour> behaviours = new TreeMap<>(); // TreeMap is used so that behaviours are stored in order


    /**
     * The constructor of the Enemy class.
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        // All enemies can wander and attack
        addBehaviour( 999, new WanderBehaviour() );
        addBehaviour( 500, new AttackBehaviour() );
    }


    public void addBehaviour(Integer i, Behaviour behaviour){
        this.behaviours.put(i, behaviour);
    }


    /**
     * Select the valid action with the highest priority
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }


    /**
     * Returns a new collection of the Actions that the otherActor can do to Hollow Soldier
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {

        dropItem(map);

        return super.unconscious(actor, map);

    }


    /**
     * Return a boolean value after randomly generating a value within the chance to drop an item
     *
     * @param percentage the percentage of chance to drop an item
     *
     * @return a boolean value after randomly generating a value within the chance to drop an item
     */
    public boolean dropItemChance(double percentage){
        return (Math.random() <= percentage);
    }



    // abstract method

    /**
     * Spawn the instance of an enemy
     *
     * @return a new spawned Enemy
     */
    public abstract Enemy spawnMethod();

    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     *
     */
    public abstract void dropItem(GameMap map);

}
