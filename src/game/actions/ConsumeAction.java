// declare package
package game.actions;

// import game and engine packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * A class representing an action to consume something
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 */
public class ConsumeAction extends Action {

    /**
     * A consumable item to be consumed
     */
    private final Consumable consumableItem;

    /**
     * Constructor of the ConsumeAction class
     *
     * @param consumableItem item to be consumed
     */
    public ConsumeAction(Consumable consumableItem){
        this.consumableItem = consumableItem;
    }

    /**
     * Perform consume action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return consumableItem.consume(actor);

    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " consumes " + consumableItem;

    }
}
