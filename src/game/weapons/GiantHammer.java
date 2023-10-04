package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;
import game.actions.SellAction;
import game.items.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * class representing the giant hammer weapon that can only be picked from the room at the end of the forest
 *
 * @author yangdan
 */

public class GiantHammer extends WeaponItem implements Sellable {


    /* Default constants for the stamina used to activate the skill of giant hammer */
    /**
     * Default constants for the stamina used to activate the skill of giant hammer
     */
    private static final float REDUCED_STAMINA_RATE = 0.05f;

    /**
     * Constructor of Giant Hammer class
     *
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "slams", 90);
        this.addCapability(Ability.SKILL);
    }


    /**
     * Player can "use" this weapon to otherActor that is around
     *
     * For example, player can attack otherActor with this weapon
     * For example, player can apply skilled attack to otherActor with this weapon
     * For example, player can sell this weapon to the otherActor if it is TRADER
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     *
     * @return actions a list of Actions that the player can perform to otherActor with this weapon
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        // When otherActor is enemy, player can attack with this weapon
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new GreatSlamAction(otherActor, REDUCED_STAMINA_RATE, this));
        }
        // When otherActor is trader, player can sell this item
        if (otherActor.hasCapability(Status.TRADER))
            actions.add( new SellAction(this, this.toString()) );
        return actions;
    }


    /**
     * Get the selling price of the Giant Hammer
     *
     * Giant hammer can be sold at 250 runes
     *
     * @return an integer value representing the selling price of  Giant Hammer
     */
    @Override
    public int getSellingPrice() {
        return 250;
    }

    /**
     * Sell giant hammer to the trader.
     *
     * @param actor Actor who sells items at the sale stage
     *
     * @return a sting showing the result of selling this item
     */
    @Override
    public String soldBy(Actor actor) {
        int price = getSellingPrice();
        actor.removeItemFromInventory( this ); // remove this item from the player's inventory
        actor.addBalance( price ); // add balance
        return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
    }

}
