// declare package
package game.npc;

// import game and engine packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;
import game.actions.AttackAction;
import game.behaviours.WanderBehaviour;

// import java libraries
import java.util.HashMap;
import java.util.Map;


/**
 * An abstract class that represents all the enemies in the entities
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public abstract class Enemy extends Actor {

    // behaviour map
    /**
     * A map of behaviours of enemies
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for the Enemy class
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     *
     */
    public Enemy(String name, char displayChar, int hitPoints) {

        // initialise super constructor
        super(name, displayChar, hitPoints);

        // behaviours lists
        this.behaviours.put(999, new WanderBehaviour());


    }

    // getter to get map of behaviour
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
     * @return a String to print the description of what happened after this method is executed
     */
    public abstract String dropItem(GameMap map);







}


