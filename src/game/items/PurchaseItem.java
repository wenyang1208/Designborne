package game.items;

import edu.monash.fit2099.engine.items.Item;

public class PurchaseItem {
    private Item item;
    private double originalPrice;
    private double currentPrice;
    private double sellingPrice;
    private double discountChance;
    private double floatingRate;

    public PurchaseItem(Item item, double originalPrice, double discountChance, double floatingRate){
        this.item = item;
        this.originalPrice = originalPrice;
        this.currentPrice = originalPrice;
        this.discountChance = discountChance;
        this.floatingRate = floatingRate;
    }

    public void setCurrentPrice(){
        if (Math.random() <= discountChance){
            this.currentPrice = originalPrice * floatingRate;
        } else{
            this.currentPrice = originalPrice;
        }
    }

    public double getCurrentPrice(){
        return currentPrice;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        this.setCurrentPrice();
        return "Item : " + item + ", Price : " + getCurrentPrice() + ", originalPrice : " + originalPrice;
    }
}
