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
import game.utils.Status;

/**
 * A class that represents the RefreshingFlask for the player to increase their stamina
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class RefreshingFlask extends Item implements Consumable, Sellable, Purchasable{

    // healing percentage
    /**
     * A healing percentage to heal the holder/owner of RefreshingFlask item
     */
    private final float healingPercentage;

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


    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.TRADER))
            actions.add( new SellAction(this, this.toString()) );
        return actions;
    }


    @Override
    public int getSellingPrice() {
        return 25;
    }


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


    @Override
    public String getPurchasableName() {
        return this.toString();
    }

    @Override
    public int getPurchasingPrice() {
        return 75;
    }


    @Override
    public String purchasedBy(Actor actor) {
        int price = getPurchasingPrice();
        String string = "";
        if (Math.random() <= 0.1) {
            price = (int) (price * 0.8);
            string = "Travellers gives a 20% discount. ";
        }
        if (actor.getBalance() < price)
            return string + "Balance is less than what the Traveller asks for, the purchase fails.";
        actor.deductBalance( price );
        actor.addItemToInventory( new RefreshingFlask() );
        return string + actor + " successfully purchased Refreshing Flask for " + price + " runes.";
    }
}
