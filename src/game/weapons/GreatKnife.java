package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actions.StabAndStepAction;
import game.items.Sellable;
import game.utils.Ability;
import game.utils.Status;

/**
 * @author yangdan
 */
public class GreatKnife extends WeaponItem implements Sellable {


    /* Default constants for the skill activation */
    private static final float REDUCED_STAMINA_RATE = 0.25f;


    public GreatKnife() {
        super("Great Knife", '>', 75, "stabs", 70);
        this.addCapability(Ability.SKILL);
    }


    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new StabAndStepAction(otherActor, REDUCED_STAMINA_RATE, this));
        }
        if (otherActor.hasCapability(Status.TRADER))
            actions.add( new SellAction(this, this.toString(), otherActor) );
        return actions;

    }


    @Override
    public int getSellingPrice() {
        return 175;
    }


    @Override
    public String sell(Actor actor, Actor trader) {
        int price = getSellingPrice();
        actor.removeItemFromInventory( this );
        if (Math.random() < 0.1){
            actor.deductBalance( Math.min(actor.getBalance(), price) );
            return trader + " takes the runes from " + actor + ".";
        }
        actor.addBalance( price );
        return actor + " successfully sold " + this + " for " + price + " runes to " + trader + ".";
    }

}
