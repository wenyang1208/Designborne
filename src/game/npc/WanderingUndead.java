// declare package
package game.npc;

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
import game.items.HealingVials;
import game.items.OldKey;
import game.utils.Status;
import game.behaviours.AttackBehaviour;


/**
 * A class that represent WanderingUndead enemy in the Abandoned Village
 *
 * Created by:
 * @author Koe Rui En
 *
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
     * Method that can be executed when WanderingUndead is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where WanderingUndead fell unconscious
     *
     * @return a string describing what happened when the actor is unconscious
     */
    public String unconscious(Actor actor, GameMap map) {

        String ret = "";

        ret += dropItem(map) + "\n";
        ret += super.unconscious(actor, map);

        return ret;

    }


    // once defeated, also has a 20% chance to drop a healing vial.
    // 25 % drop old key
    /**
     * Drop items on the ground
     *
     * @param map the map containing WanderingUndead
     *
     * @return a String to print the description of what happened after this method is executed
     */
    public String dropItem(GameMap map){

        // declare output
        String ret = "";

        // 25 % drop old key
        if (dropItemChance(100)){

            ret += new OldKey().getDropAction(this).execute(this,map) + "\n";

        }

        else {

            ret +=  this + " does not drop Old Key" + "\n";

        }

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)){

           ret +=  new HealingVials().getDropAction(this).execute(this,map);

        }

        else {

            ret += this + " does not drop Healing Vial";

        }

        return ret;

    }

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
