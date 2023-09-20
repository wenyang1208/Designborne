package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * class representing healing vial
 */
public class HealingVial extends Item implements Consumable{


    /**
     * Constructor
     * It has the ability to increase health point after consumption
     */
    public HealingVial() {
        super("Healing vial", 'a', true);
    }


    /**
     * A healing item can have a special skill that can increase the current actor's hit points.
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        return new ActionList( new ConsumeAction(this) );
    }


    @Override
    public String consume(Actor actor) {
        int value = (int)(0.1 * actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, value);
        actor.removeItemFromInventory(this);
        return actor + " consumes " + this + ", and it restores the health by " + value + " points.";
    }
}
