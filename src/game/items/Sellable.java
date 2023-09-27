package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A interface representing items that can be sold to traders
 *
 * Created:
 * @author yangdan
 */
public interface Sellable {


    /**
     * Get the selling price of the sellable item
     * @return an integer value representing the selling price
     */
    int getSellingPrice();


    /**
     * Sell the item to the trader
     * @param actor Actor who sells items at the sale stage
     * @param trader Actor who takes items at the sale stage
     * @return a string showing the result of selling item
     */
    String sell(Actor actor, Actor trader);

}
