package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Purchasable {

//    /**
//     * Get the selling price of the purchasable item
//     *
//     * @return an integer value representing the purchasable price
//     */
//    int getPurchasingItemPrice();


    /**
     * Purchase an item from the trader
     *
     * @param actor Actor who purchase an item at the sale stage
     * @param itemPrice price of an item
     *
     * @return a string showing the result of purchasing item
     */
    String purchase(Actor actor, int itemPrice);


}
