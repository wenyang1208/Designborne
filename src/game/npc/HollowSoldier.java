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
import game.behaviours.AttackBehaviour;
import game.items.HealingVials;
import game.items.RefreshingFlask;
import game.utils.Status;


/**
 * A class that represent HollowSoldier enemy in the Burial Ground
 *
 * Created by:
 * @author Koe Rui En
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


    // this enemy has a 20% chance to drop a healing vial
    // has a 30% chance to drop a refreshing flask
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
     * Method that can be executed when the HollowSoldier is unconscious due to the action of another actor
     *
     * @param actor the perpetrator
     * @param map where the HollowSoldier fell unconscious
     *
     * @return a string describing what happened when the HollowSoldier is unconscious
     */
    public String unconscious(Actor actor, GameMap map) {

        String ret = "";

        ret += dropItem(map) + "\n";
        ret += super.unconscious(actor, map);

        return ret;

    }

    // this enemy has a 20% chance to drop a healing vial
    // has a 30% chance to drop a refreshing flask
    /**
     * Drop items on the ground
     *
     * @param map the map containing HollowSoldier
     *
     * @return a String to print the description of what happened after this method is executed
     */
    public String dropItem(GameMap map) {

        String ret = "";

        // 20% chance to drop a healing vial
        if (dropItemChance(0.20)) {

            ret += new HealingVials().getDropAction(this).execute(this,map) + "\n";

        }

        else {

            ret +=  this + " does not drop Healing Vials" + "\n";

        }

        // has a 30% chance to drop a refreshing flask
       if (dropItemChance(0.30)){


           ret += new RefreshingFlask().getDropAction(this).execute(this,map);

       }

        else{

            ret +=  this + " does not drop Refreshing Flask";

        }

        return ret;


    }

    /**
     * Spawn the HollowSoldier instance
     *
     * @return a new spawned HollowSoldier instance
     */
    @Override
    public  Enemy spawnMethod() {

        return new HollowSoldier();

    }

}
