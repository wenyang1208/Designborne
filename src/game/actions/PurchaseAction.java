package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.npc.Traveller;
import game.items.PurchaseItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseAction extends Action {

    private Map<Character, PurchaseItem> HotkeyPurchasedItemMap;
    /**
     * The Actor that is traveller
     */
    private Traveller traveller;

    /**
     * Constructor.
     *
     * @param traveller the traveller
     */
    public PurchaseAction(Traveller traveller) {
        this.traveller = traveller;
        this.HotkeyPurchasedItemMap = new HashMap<>();
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        PurchaseItem purchaseItem;
        purchaseItem = showMenu(this.traveller.getMenu());
        if (purchaseItem == null){
            return "You have quit the menu.";
        }
        if (purchaseItem.getCurrentPrice() <= actor.getBalance()) {
            actor.deductBalance((int) purchaseItem.getCurrentPrice());
            actor.addItemToInventory(purchaseItem.getItem());
            return ("The purchase of " + purchaseItem.getItem() + " is successful. Your balance now is: " + actor.getBalance());

        } else {
            return (actor + "'s balance is not enough to buy " + purchaseItem.getItem() + "Your balance now is: " + actor.getBalance()) ;
        }
    }

    public PurchaseItem showMenu(List<PurchaseItem> menu) {
        char choice;
        PurchaseItem selectedItem = null;

        do {
            new Display().println("#########################################");
            char hotkey = 'a';
            for (PurchaseItem purchaseItem : menu) {
                HotkeyPurchasedItemMap.put(hotkey, purchaseItem);
                new Display().println(hotkey + ". " + purchaseItem);
                hotkey++;
            }
            new Display().println("To quit the Menu, please input 'h' ");
            choice = new Display().readChar();

            if (choice == 'h') {
                return null; //quit the menu
            }

            selectedItem = HotkeyPurchasedItemMap.get(choice);

            if (selectedItem == null) {
                new Display().println("Invalid choice. Please choose a valid option (a, b, c, or h).");
            }
        } while (selectedItem == null);

        return selectedItem;
    }




    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchase item from " + traveller;
    }
}
