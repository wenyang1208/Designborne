// declare package
package game.actors.npcs;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.Rune;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.FancyMessage;
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
 * @author Yang Dan
 * @author Chua Wen Yang
 */
public abstract class Enemy extends Actor implements Resettable{

    /* Attribute */
    /**
     * a map of behaviours of an enemy
     */
    private Map<Integer, Behaviour> behaviours = new TreeMap<>(); // TreeMap is used so that behaviours are stored in order

    /**
     * runes, the currency used in the world of “Designborne”
     */
    private Rune runes;

   private GameMap currMap;

    /**
     * The constructor of the Enemy class.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, Rune runes, GameMap map) {

        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.runes = runes;

        // All enemies can wander and attack
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(500, new AttackBehaviour());

        this.currMap = map;

        // register to reset manager
        ResetManager.getInstanceReset().registerResettable(this, true);

    }

    /**
     * A method to add new behaviour of an enemy
     *
     * @param i key to be added as an integer
     * @param behaviour behaviour of an enemy as values to be added
     */
    public void addBehaviour(Integer i, Behaviour behaviour){

        this.behaviours.put(i, behaviour);

    }

    /**
     * A method to remove the existing behaviour of an enemy
     *
     * @param i key to be added as an integer
     */
    public void removeBehaviour(Integer i){

        this.behaviours.remove(i);
    }

    // get behaviours
    /**
     * A method to get a map of behaviours of an enemy
     *
     * @return a map of behaviours of an enemy
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Select the valid action with the highest priority
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     *
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
     * Returns a new collection of the Actions that the otherActor can do to enemies
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     *
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        this.currMap = map;
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }

    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     *
     * @return a string describing what happened when the actor is unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {

        // drop items once defeated
        dropItem(map);

        // need to remove from reset manager after died
        ResetManager.getInstanceReset().removeResettable(this);

        return super.unconscious(actor, map);

    }

    /**
     * Method that can be executed when the Enemy is unconscious due to natural causes or accident
     *
     * @param map where the Player fell unconscious
     *
     * @return a string describing what happened when the Player is unconscious
     */
    public String unconscious(GameMap map) {

        // need to remove from reset manager after died
        ResetManager.getInstanceReset().removeResettable(this);

        return super.unconscious(map);

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

    /**
     * Retrieves the Rune associated with this Enemy.
     *
     * @return The Rune object associated with this Enemy.
     */
    public Rune getRunes(){return this.runes;}

    /**
     * Drop items on the ground once the enemy is defeated
     *
     * @param map the map containing the Enemy
     *
     */
    public void dropItem(GameMap map) {

        // every enemy will 100% drop runes
        this.getRunes().getDropAction(this).execute(this,map);

    }

    // All spawned enemies (not including bosses) will be removed from the map.
    /**
     * Provides a way for any entities be it actors or items or grounds on the GameMap that have to be reset
     * after player dies due to any causes
     */
    @Override
    public void reset(){

        // remove all enemies except boss from the map
        currMap.removeActor(this);

        // need to remove from reset manager after reset
        ResetManager.getInstanceReset().removeResettable(this);

    }

}
