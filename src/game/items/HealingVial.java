//
package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A class that represents the HealingVial item for the player to increase their health.
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class HealingVial extends Item implements Consumable, Sellable{

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of HealingVial item
     */
    private final float healingPercentage;

    /**
     * Constructor of HealingVial class
     *
     * It has the ability to increase health point after consumption
     */
    public HealingVial() {

        super("Healing vial", 'a', true);
        this.healingPercentage = 0.1f;

    }

    /**
     * A HealingVial item can have a special skill that can increase the current actor's hit points.
     *
     * @param owner the actor that owns the item
     *
     * @return a list of Actions for actor acts on the HealingVial item
     */
    @Override
    public ActionList allowableActions(Actor owner) {

        return new ActionList( new ConsumeAction(this) );

    }

    /**
     * A consume method to allow owner consume the item
     *
     * @param owner the owner of a consumed item
     */
    @Override
    public String consume(Actor owner) {

        int value = (int)(healingPercentage * owner.getAttributeMaximum(BaseActorAttributes.HEALTH));

        owner.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, value);
        owner.removeItemFromInventory(this);

        return owner + " consumes " + this + ", and it restores the health by " + value + " points.";

    }

    /**
     * List of allowable actions that Healing Vial allows its owner do to other actor
     *
     * The player will sell item to the other actor if the other actor has the capability of Status.TRADER
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        if (otherActor.hasCapability(Status.TRADER))
            return new ActionList( new SellAction(this, this.toString()) );
        return new ActionList();
    }

    /**
     * Get the selling price of the Healing Vial
     *
     * @return an integer value representing the selling price of Healing Vial
     */
    @Override
    public int getSellingPrice() {
        return 35;
    }


    /**
     * Sell the Healing Vial to the trader
     *
     * @param actor Actor who sells Healing Vial at the sale stage
     *
     * @return a string showing the result of selling Healing Vial
     */
    @Override
    public String soldBy(Actor actor) {
        int price = getSellingPrice();
        String string = "";
        if (Math.random() <= 0.1) {
            price = price * 2;
            string = "Traveller pays 2x the original price. ";
        }
        actor.removeItemFromInventory( this );
        actor.addBalance( price );
        return string + actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
    }

}
