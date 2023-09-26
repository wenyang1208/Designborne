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
import game.utils.Status;

/**
 * A class that represent ForestKeeper enemy in the Ancient Woods
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class ForestKeeper extends Enemy{


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
    public ForestKeeper() {

        // displayed "8", 125 hp
        super("Forest Keeper", '8', 125);

        // can attack the player with its limbs, dealing 25 damage with 75% accuracy
        this.damage = 25;
        this.hitRate = 75;

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

//                getBehaviours().put(1, new FollowBehaviour(targetActor));

                // check status of target actor
                // if player, hostile to enemy status
                // attack behaviour has the highest priority
                // follows the player, till either the player is unconscious or the enemy is unconscious
                if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)){

                    getBehaviours().put(0, new AttackBehaviour(targetActor, exit.getName()));

                    if (targetActor.isConscious() || this.isConscious()) {
                        getBehaviours().put(1, new FollowBehaviour(targetActor));
                    }

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
     * Creates and returns an intrinsic weapon for Forest Keeper with different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon for Forest Keeper
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {

        // create new intrinsic weapon for forest keeper
        return new IntrinsicWeapon(damage, "whacks", hitRate);

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

    // 20% chance to drop a healing vial once defeated
    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)){

            new HealingVial().getDropAction(this).execute(this,map);

        }

    }

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
     * Spawn the ForestKeeper instance
     *
     * @return a new spawned ForestKeeper instance
     */
    @Override
    public Enemy spawnMethod() {

        return new ForestKeeper();

    }

}
