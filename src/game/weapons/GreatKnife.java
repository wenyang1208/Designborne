package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.PurchaseAction;
import game.actions.SellAction;
import game.actions.StabAndStepAction;
import game.actions.UpgradeAction;
import game.items.Purchasable;
import game.items.Sellable;
import game.items.Upgradable;
import game.utils.Ability;
import game.utils.Status;

/**
 * Class representing the Great Knife weapon that is sold by Traveller
 *
 * Created by:
 * @author Yang Dan
 *
 * Modified by:
 * @author Chua Wen Yang
 */

public class GreatKnife extends WeaponItem implements Sellable, Purchasable, Upgradable {


    /* Default constants for the stamina used to activate the skill of great knife */
    /**
     * default constants for the stamina used to activate the skill of grear knife
     */
    private static final float REDUCED_STAMINA_RATE = 0.25f;

    /**
     * selling price of Great Knife
     */
    private static final int soldPrice = 175;

    /**
     * purchasing price of Great Knife
     */
    private static final int purchasedPrice = 300;


    /**
     * Constructor of Great Knife class
     *
     */
    public GreatKnife() {
        super("Great Knife", '>', 75, "stabs", 70);
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
            actions.add(new StabAndStepAction(otherActor, REDUCED_STAMINA_RATE, this));
        }
        // When otherActor is trader, player can sell this item
        if (otherActor.hasCapability(Status.TRADER))
            actions.add(new SellAction(this));
        if (otherActor.hasCapability(Status.UPGRADE_PERSON))
            actions.add(new UpgradeAction(this));
        return actions;

    }

    /**
     * Get the selling price of the Great Knife
     *
     * Great Knife can be sold at 175 runes
     *
     * @return an integer value representing the selling price of Great Knife
     */
    @Override
    public int getSellingPrice() {

        return soldPrice;
    }

    /**
     * Sell Great Knife to the trader
     *
     * There is a 10% chance of the traveller taking the runes from the player instead.
     *
     * @param actor Actor who sells Great Knife at the sale stage
     *
     * @return a sting showing the result of selling this item
     */
    @Override
    public String soldBy(Actor actor) {
        int price = getSellingPrice();

        actor.removeItemFromInventory( this ); // remove this item from the player's inventory

        if (Math.random() <= 0.1){ // there is 10% chance to take at most 175 runes from the player
            actor.deductBalance( Math.min(actor.getBalance(), price) );
            return "Traveller takes the runes from " + actor + ".";
        }

        actor.addBalance( price ); // successfully sold it, so add balance

        return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
    }

    /**
     * Get the purchasing price of the Great Knife
     *
     * Great Knife can be purchased at 300 runes
     *
     * @return an integer value representing the purchasable Great Knife's price
     */
    @Override
    public int getPurchasingPrice() {

        return purchasedPrice;

    }

    /**
     * Purchase Great Knife from the trader
     *
     * @param actor Actor who purchase Great Knife at the sale stage
     *
     * @return a string showing the result of after purchasing Great Knife
     */
    @Override
    public String purchasedBy(Actor actor) {
        int price = getPurchasingPrice();

        String string = "";

        if (Math.random() <= 0.05){
            price = price * 3;
            string = "Traveller asks to pay 3x the original price of the weapon. ";
        }

        if (actor.getBalance() < price)
            return string + "Balance is less than what the Traveller asks for, the purchase fails.";

        actor.deductBalance( price );
        actor.addItemToInventory(new GreatKnife());

        return string + actor + " successfully purchased " + this + " for " + price + " runes.";
    }

    /**
     * Upgrade Great Knife from the blacksmith
     *
     * @param actor Actor who upgrades the Healing Vial
     *
     * @return a string showing the result of after upgrading Great Knife
     */
    @Override
    public String upgradedBy(Actor actor) {
        int price = 2000;
        String string = "";
        if (actor.getBalance() < price)
            return string + "Balance is less than what the Blacksmith asks for, the upgrade fails.";
        actor.deductBalance(price);
        increaseHitRate((int) (super.chanceToHit() * 0.1));
        return "Great Knife's effectiveness has been improved!";
    }
}
