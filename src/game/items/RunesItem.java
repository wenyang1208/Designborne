package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Represents a PricingItem class that provides pricing information for items available for purchase or sale.
 *
 * Created by: Chai Jun Lun
 */
public class RunesItem {
    private Item item;
    private double originalPrice;
    private double currentPrice; // The current price of the item
    private double discountChance;
    private double floatingRate;

    /**
     * Constructor to initialize a PricingItem.
     *
     * @param item           The item that can be purchased or sold.
     * @param originalPrice  The original price of the item.
     * @param discountChance The chance of the item being modified with a discount.
     * @param floatingRate   The rate by which the price can float up if discounted.
     */
    public RunesItem(Item item, double originalPrice, double discountChance, double floatingRate){
        this.item = item;
        this.originalPrice = originalPrice;
        this.currentPrice = originalPrice;
        this.discountChance = discountChance;
        this.floatingRate = floatingRate;
    }

    /**
     * Method to set the current price of the item (may apply a discount).
     */
    public void setCurrentPrice(){
        if (Math.random() <= discountChance){
            this.currentPrice = originalPrice * floatingRate; // Apply a discount if the random chance is met
        } else{
            this.currentPrice = originalPrice; // Keep the original price if no discount
        }
    }

    /**
     * Method to get the current price of the item.
     *
     * @return The current price of the item.
     */
    public double getCurrentPrice(){
        return currentPrice;
    }

    /**
     * Method to get the item being purchased or sold.
     *
     * @return The item being purchased or sold.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Override the toString() method to provide a formatted string representation of the PricingItem.
     *
     * @return A string representation of the PricingItem.
     */
    @Override
    public String toString() {
        this.setCurrentPrice(); // Ensure the current price is up to date
        return "Item : " + item + ", Runes : " + getCurrentPrice() + ", originalRunes : " + originalPrice;
    }
}
