// declare package
package game.items;

// import engine
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actions.ConsumeAction;

/**
 * A class that represents the HealingVials for the player to increase their health.
 *
 * Created by:
 * @author Koe Rui En
 *
 */
// implement consumable
public class HealingVials extends Consumable implements Healing{

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of HealingVials item
     */
    private float healingPercentage;

    // number of uses of healing vial
    /**
     * Number of uses of a HealingVials item
     */
    private int numberOfUses;

    /***
     * Constructor of HealingVials class
     *
     */
    // healing vial (represented by the display character “a”),
    public HealingVials() {

        super("Healing Vial", 'a',true);

        // heal 10% of their maximum health
        this.healingPercentage = 0.1f;

        // can be only consumed once
        setNumberOfUses(1);
    }


    // getter
    /**
     * Get the number of uses of the HealingVials
     *
     * @return the number of uses of the HealingVials item
     */
    public int getUses() {return numberOfUses;}

    // setter
    /**
     * Set the number of uses of the HealingVials
     *
     * @param newUses an integer to set new number of uses of a HealingVials item
     */
    public void setNumberOfUses(int newUses) {

        numberOfUses = newUses;

    }

    // heal 10% of player maximum health
    /**
     * A healing method to heal the owner when owner consume HealingVials item
     *
     * @param owner the owner of the HealingVials item
     */
    @Override
    public void heal(Actor owner) {

        // compute increasing points
        int healPoints = (int) (healingPercentage*owner.getAttributeMaximum(BaseActorAttributes.HEALTH));

        // increase stamina of the owner
        owner.heal(healPoints);

    }

    /**
     * Get the number of uses of the HealingVials item
     *
     * @return the number of uses of the HealingVials item
     */
    @Override
    public int getNumberOfUses() {

        return getUses();

    }

    /**
     * List of allowable actions that the HealingVial item can perform to the current actor
     *
     * @param owner the actor that owns the HealingVial item
     *
     * @return a list of Actions for actor acts on the HealingVials item
     */
    public ActionList allowableActions(Actor owner){

        // declare output
        ActionList actions =  new ActionList();

        if (getNumberOfUses() > 0) {

            actions.add(new ConsumeAction( this));

        }

        // return output
        return actions;

    }

}
