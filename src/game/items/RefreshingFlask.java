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
public class RefreshingFlask extends Item implements Consumable, Sellable{

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
        if (otherActor.hasCapability(Status.TRADER))
            return new ActionList( new SellAction(this, this.toString()) );
        return new ActionList();
    }


    @Override
    public int getSellingPrice() {
        return 25;
    }


    @Override
    public String soldBy(Actor actor) {
        boolean applySpecial = Math.random() <= 0.5;
        int price = getSellingPrice();
        actor.removeItemFromInventory( this );
        if (! applySpecial) {
            actor.addBalance( price );
            return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
        }
        return "Traveller takes " + this + " without paying " + actor;
    }
}
