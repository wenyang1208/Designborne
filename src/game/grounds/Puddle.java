package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.Consumable;
import java.util.Objects;

/**
 * A class that represents puddle
 *
 * Modified by:
 * @author Chua Wen Yang
 */
public class Puddle extends Ground implements Consumable {
    private final float increasePercentage;

    /**
     * Constructor for the Puddle class.
     * Creates a Puddle ground object represented by the '~' character.
     * Initializes the increasePercentage to 0.01f, representing the percentage of stamina increase when consumed.
     */
    public Puddle() {
        super('~');
        this.increasePercentage = 0.01f;
    }

    /**
     * Retrieves the list of allowable actions for an actor when interacting with this Puddle ground object.
     * Actors can consume the Puddle when standing on it, and the direction should not be an exit direction.
     *
     * @param actor     The actor interacting with the Puddle.
     * @param location  The location containing the Puddle.
     * @param direction The direction of the interaction (should not be an exit direction).
     * @return An ActionList containing the ConsumeAction if the conditions are met, otherwise an empty ActionList.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        // The player only consume water puddle when standing on it, direction is not exit.getName()
        if (Objects.equals(direction, ""))
            return new ActionList(new ConsumeAction(this));
        return new ActionList();
    }

    /**
     * Consumes the Puddle, restoring the actor's health and stamina based on a percentage increase.
     *
     * @param owner The actor consuming the Puddle.
     * @return A message indicating that the actor has consumed the Puddle and the increase in stamina and health.
     */
    @Override
    public String consume(Actor owner) {
        int increaseValue = (int)(increasePercentage * owner.getAttributeMaximum(BaseActorAttributes.STAMINA));
        owner.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,1);
        owner.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,increaseValue);
        return owner + " consumes " + this + ", and it restores the stamina by " + increaseValue + " points and the health by 1 point.";
    }

    /**
     * Returns a string representation of the Puddle.
     *
     * @return A string representing the Puddle as "water puddle".
     */
    @Override
    public String toString(){
        return "water puddle";
    }
}
