package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.displays.Display;
import game.items.RunesItem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an abstract class, TradingAction, which is the base class for PurchaseAction and SellAction.
 * This class provides common functionality for buying and selling actions.
 *
 * Created by:
 * @author Chai Jun Lun
 */
public abstract class TradingAction extends Action {
    // A map used to associate purchase options with hotkeys
    private Map<Character, RunesItem> HotkeyPurchasedItemMap = new HashMap<>();;

    /**
     * Method to display the menu and choose the pricing item to be purchased or sold.
     *
     * @param menu The list of PricingItem objects representing the available items.
     * @return The selected PricingItem or null if the menu is quit.
     */
    public RunesItem showMenu(List<RunesItem> menu) {
        char choice;

        // Initialize selectedItem to null to ensure it enters the while loop
        RunesItem selectedItem = null;
        do {
            new Display().println("#########################################");
            char hotkey = 'a';
            for (RunesItem runesItem : menu) {
                HotkeyPurchasedItemMap.put(hotkey, runesItem);
                new Display().println(hotkey + ". " + runesItem);
                hotkey++;
            }
            new Display().println("To quit the Menu, please input 'z' ");
            choice = new Display().readChar();

            if (choice == 'z') {
                return null; //quit the menu
            }

            selectedItem = HotkeyPurchasedItemMap.get(choice);

            if (selectedItem == null) {
                new Display().println("Invalid choice. Please choose a valid option.");
            }
        } while (selectedItem == null);

        return selectedItem;
    }
}
