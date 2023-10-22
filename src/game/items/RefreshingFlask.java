// declare package
package game.items;

// import game and engine packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.utils.Status;

/**
 * A class that represents the RefreshingFlask for the player to increase their stamina
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 * @author Chua Wen Yang
 */
public class RefreshingFlask extends Item implements Consumable, Sellable, Purchasable, Upgradable{

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of RefreshingFlask item
     */
    private float healingPercentage;

    /**
     * selling price of Refreshing Flask
     */
    private static final int soldPrice = 25;

    /**
     * purchasing price of Refreshing Flask
     */
    private static final int purchasedPrice = 75;

    /**
     * Constructor for the RefreshingFlask class
     *
     * It has the ability to increase stamina point after consumption
     */
    public RefreshingFlask() {

        super("Refreshing flask", 'u', true);

        // increase their stamina by 20% of their maximum stamina
        this.healingPercentage = 0.2f;
    }


    /**
     * A refreshing flask item can have a special skill that can increase the current actor's stamina points
     *
     * @param owner the actor that owns the item
     *
     * @return a list of Actions for actor acts on the RefreshingFlask item
     */
    @Override
    public ActionList allowableActions(Actor owner) {

        return new ActionList( new ConsumeAction(this) );

    }

    /**
     * A consume method to allow owner consume the Refreshing flask
     *
     * @param owner the owner of a consumed item which is Refreshing flask
     */
    @Override
    public String consume(Actor owner) {

        int value = (int)(healingPercentage * owner.getAttributeMaximum(BaseActorAttributes.STAMINA));

        owner.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, value);
        owner.removeItemFromInventory(this);

        return owner + " consumes " + this + ", and it restores the stamina by " + value + " points.";

    }

    /**
     * List of allowable actions that Refreshing Flask allows its owner do to other actor
     *
     * The player will sell Refreshing Flask to the other actor if the other actor has the capability of Status.TRADER
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
     * Get the selling price of the Refreshing Flask
     *
     * Refreshing Flask can be sold at 25 runes
     *
     * @return an integer value representing the selling price of Refreshing Flask
     */
    @Override
    public int getSellingPrice() {

        return soldPrice;

    }

    /**
     * Sell Refreshing Flask to the trader
     *
     * There is a 50% chance of the traveller taking the Refreshing Flask from the player without paying the player
     *
     * @param actor Actor who sells Refreshing Flask at the sale stage
     *
     * @return a sting showing the result after selling Refreshing Flask
     */
    @Override
    public String soldBy(Actor actor) {
        int price = getSellingPrice();

        actor.removeItemFromInventory( this );

        if (Math.random() <= 0.5) {
            return "Traveller takes " + this + " without paying " + actor;
        }

        actor.addBalance( price );

        return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
    }

    /**
     * Get the purchasing price of Refreshing Flask
     *
     * Refreshing Flask can be purchased at 75 runes
     *
     * @return an integer value representing the purchasable Refreshing Flask's price
     */
    @Override
    public int getPurchasingPrice() {

        return purchasedPrice;

    }

    /**
     * Purchase Refreshing Flask from the trader
     *
     * @param actor Actor who purchase Refreshing Flask at the sale stage
     *
     * @return a string showing the result of after purchasing Refreshing Flask
     */
    @Override
    public String purchasedBy(Actor actor) {
        int price = getPurchasingPrice();

        String string = "";

        if (Math.random() <= 0.1) {
            price = (int) (price * 0.8);
            string = "Travellers gives a 20% discount.";
        }

        if (actor.getBalance() < price) {
            return string + "Balance is less than what the Traveller asks for, the purchase fails.";
        }

        actor.deductBalance(price);
        actor.addItemToInventory(new RefreshingFlask());

        return string + actor + " successfully purchased " + this + " for " + price + " runes.";
    }

    /**
     * Upgrade Refreshing Flask from the blacksmith
     *
     * @param actor Actor who upgrades the Refreshing Flask
     *
     * @return a string showing the result of after upgrading Refreshing Flask
     */
    @Override
    public String upgradedBy(Actor actor) {
        int price = 175;
        String string = "";
        if (actor.getBalance() < price)
            return string + "The Refreshing Flask requires 175 runes to upgrade.";
        actor.deductBalance(price);
        this.addCapability(Status.UPGRADED);
        this.healingPercentage = 1.0f;
        return "Refreshing Flask's effectiveness has been improved!";
    }
}
