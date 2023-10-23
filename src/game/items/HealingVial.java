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
import game.actions.UpgradeAction;
import game.utils.Status;

/**
 * A class that represents the HealingVial item for the player to increase their health.
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 * @author Chua Wen Yang
 * @author Koe Rui En
 */
public class HealingVial extends Item implements Consumable, Sellable, Purchasable, Upgradable {

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of HealingVial item
     */
    private float healingPercentage;

    /**
     * selling price of Healing Vial
     */
    private static final int soldPrice = 35;

    /**
     * purchasing price of Healing Vial
     */
    private static final int purchasedPrice = 100;

    /**
     * upgrading price of Healing Vial
     */
    private static final int upgradePrice = 250;

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
     * The player will sell Healing Vial to the other actor if the other actor has the capability of Status.TRADER
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADER))
            actions.add( new SellAction(this) );
        if (otherActor.hasCapability(Status.UPGRADE_PERSON) && !this.hasCapability(Status.UPGRADED))
            actions.add(new UpgradeAction(this));
        return actions;
    }

    /**
     * Get the selling price of the Healing Vial
     *
     * Healing Vial can be sold at 35 runes
     *
     * @return an integer value representing the selling price of Healing Vial
     */
    @Override
    public int getSellingPrice() {

        return soldPrice;

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


    /**
     * Get the purchasing price of Healing Vial
     *
     * Healing Vial can be purchased at 100 runes
     *
     * @return an integer value representing the purchasable Healing Vial's price
     */
    @Override
    public int getPurchasingPrice() {

        return purchasedPrice;

    }

    /**
     * Purchase Healing Vial from the trader
     *
     * @param actor Actor who purchase Healing Vial at the sale stage
     *
     * @return a string showing the result of after purchasing Healing Vial
     */
    @Override
    public String purchasedBy(Actor actor) {
        int price = getPurchasingPrice();

        String string = "";

        if (Math.random() <= 0.25) {
            price = (int) (price * 1.5);
            string = "Traveller asks to pay 50% more.";
        }

        if (actor.getBalance() < price)
            return string + "Balance is less than what the Traveller asks for, the purchase fails.";

        actor.deductBalance(price);
        actor.addItemToInventory( new HealingVial() );

        return string + actor + " successfully purchased " + this + " for " + price + " runes.";
    }

    /**
     * Get the upgrading price of Healing Vial
     *
     * Healing Vial can be upgraded at 250 runes
     *
     * @return an integer value representing the upgradable Healing Vial's price
     */
    @Override
    public int getUpgradingPrice() {

        return upgradePrice;

    }

    /**
     * Update Healing Vial from the Blacksmith
     *
     * @param actor Actor who upgrades the Healing Vial
     *
     * @return a string showing the result of after upgrading Healing Vial
     */
    @Override
    public String upgradedBy(Actor actor) {
        int price = getUpgradingPrice();

        String string = "";

        if (actor.getBalance() < price) {
            return string + "The " + this + " requires " + price + " runes to upgrade.";
        }

        actor.deductBalance(price);
        // can be only upgraded once
        this.addCapability(Status.UPGRADED);
        this.healingPercentage = 0.8f;
        string = this + "'s effectiveness has been improved!";

        return string;

    }

}
