package game.actors.npcs;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;

/**
 * A class that represent Traveller in the Forest
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yangdan
 *
 */
public class Traveller extends Trader {

    public Traveller() {
        super("Isolated Traveller", 'ඞ', 100);
        this.configure();

    }


    @Override
    public void configure() {
        this.addPair( new HealingVial(), this::purchaseHealingVial );
        this.addPair( new RefreshingFlask(), this::purchaseRefreshingFlask );
        this.addPair( new Broadsword(), this::purchaseBroadsword );
        this.addPair( new GreatKnife(), this::purchaseGreatKnife );
    }


    public String purchaseHealingVial(Actor actor){
        int price = 100;
        String string = "";
        if (Math.random() <= 0.25) {
            price = (int) (price * 1.5);
            string = this + " asks to pay 50% more. ";
        }
        if (actor.getBalance() < price)
            return string + "Balance is less than what the " + this + " asks for, the purchase fails.";
        actor.deductBalance( price );
        actor.addItemToInventory( new HealingVial() );
        return string + actor + " successfully purchased Healing Vial for " + price + " runes.";
    }


    public String purchaseRefreshingFlask(Actor actor){
        int price = 75;
        String string = "";
        if (Math.random() <= 0.1) {
            price = (int) (price * 0.8);
            string = this + " gives a 20% discount. ";
        }
        if (actor.getBalance() < price)
            return string + "Balance is less than what the " + this + " asks for, the purchase fails.";
        actor.deductBalance( price );
        actor.addItemToInventory( new RefreshingFlask() );
        return string + actor + " successfully purchased Refreshing Flask for " + price + " runes.";
    }


    public String purchaseBroadsword(Actor actor){
        int price = 250;
        if (actor.getBalance() < price)
            return "Balance is less than what the " + this + " asks for, the purchase fails.";
        actor.deductBalance( price );
        if (!(Math.random() <= 0.05)) {
            actor.addItemToInventory( new Broadsword());
            return actor + " successfully purchased Broadsword for " + price + " runes.";
        }
        return this + " takes " + price + " runes without giving the broadsword.";
    }


    public String purchaseGreatKnife(Actor actor){
        int price = 300;
        String string = "";
        if (Math.random() <= 0.05){
            price = price * 3;
            string = this + " asks to pay 3x the original price of the weapon. ";
        }
        if (actor.getBalance() < price)
            return string + "Balance is less than what the " + this + " asks for, the purchase fails.";
        actor.deductBalance( price );
        actor.addItemToInventory( new GreatKnife());
        return string + actor + " successfully purchased Great Knife for " + price + " runes.";
    }
}
