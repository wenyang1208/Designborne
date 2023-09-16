// declare package
package game.items;

// import engine and game packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actions.ConsumeAction;

/**
 * A class that represents the RefreshingFlask for the player to increase their stamina
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class RefreshingFlask extends Consumable implements Healing {

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of RefreshingFlask item
     */
    private float healingPercentage;

    // number of uses of refreshing flask
    /**
     * Number of uses of a RefreshingFlask item
     */
    private int numberOfUses;

    /***
     * Constructor for the RefreshingFlask class
     *
     */
    // refreshing flask (represented by the display character “u”)
    public RefreshingFlask() {

        super("Refreshing Flask", 'u', true);

        // increase their stamina by 20% of their maximum stamina
        this.healingPercentage = 0.2f;

        // can be only consumed once
        this.setNumberOfUses(1);

    }

    /**
     * A getter to get the number of uses of the RefreshingFlask item
     *
     * @return the number of uses of the RefreshingFlask item
     */
    public int getUses() {return numberOfUses;}


    /**
     * Set the number of uses of the RefreshingFlask item
     *
     * @param newUses an integer to set new number of uses of a RefreshingFlask item
     */
    public void setNumberOfUses(int newUses) {

        numberOfUses = newUses;

    }


    // increase player's stamina by 20% of their maximum stamina
    /**
     * A healing method to heal the owner when owner consume RefreshingFlask item
     *
     * @param owner the owner of the RefreshingFlask item
     */
    @Override
    public void heal(Actor owner) {

        // compute increasing stamina
        int increaseStaminaPoints = (int) (healingPercentage*owner.getAttributeMaximum(BaseActorAttributes.STAMINA));

        // increase stamina of the owner
        owner.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,increaseStaminaPoints);

    }

    /**
     * Get the number of uses of the RefreshingFlask item
     *
     * @return the number of uses of the RefreshingFlask item
     */
    @Override
    public int getNumberOfUses() {

        return getUses();

    }

    /**
     * List of allowable actions that the RefreshingFlask item can perform to the current actor
     *
     * @param owner the actor that owns the RefreshingFlask item
     *
     * @return a list of Actions for actor acts on the RefreshingFlask item
     */
    public ActionList allowableActions(Actor owner){

        // declare output
        ActionList actions =  new ActionList();

        if (getNumberOfUses() > 0) {

            actions.add(new ConsumeAction(this));

        }

        // return output
        return actions;

    }

}
