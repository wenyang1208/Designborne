package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class that represent PurchaseItem to give item price to be purchased.
 * Created by:
 * @author Chai Jun Lun
 *
 */
public class PurchaseItem {
    private Item item; // The item that can be purchased
    private double originalPrice; // The original price of the item
    private double currentPrice; // The current price of the item
    private double discountChance; // The chance of the item being modified
    private double floatingRate; // The rate by which the price can float up if discounted

    // Constructor to initialize a PurchaseItem
    public PurchaseItem(Item item, double originalPrice, double discountChance, double floatingRate){
        this.item = item;
        this.originalPrice = originalPrice;
        this.currentPrice = originalPrice;
        this.discountChance = discountChance;
        this.floatingRate = floatingRate;
    }

    // Method to set the current price of the item (may apply a discount)
    public void setCurrentPrice(){
        if (Math.random() <= discountChance){
            this.currentPrice = originalPrice * floatingRate; // Apply a discount if the random chance is met
        } else{
            this.currentPrice = originalPrice; // Keep the original price if no discount
        }
    }

    // Method to get the current price of the item
    public double getCurrentPrice(){
        return currentPrice;
    }

    // Method to get the item being purchased
    public Item getItem() {
        return item;
    }

    // Override the toString() method to provide a formatted string representation of the PurchaseItem
    @Override
    public String toString() {
        this.setCurrentPrice(); // Ensure the current price is up to date
        return "Item : " + item + ", Price : " + getCurrentPrice() + ", originalPrice : " + originalPrice;
    }
}
