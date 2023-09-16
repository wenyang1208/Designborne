// declare package
package game.actions;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;


/**
 * A ConsumeAction class for the owner to consume an item
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class ConsumeAction extends Action {

    // healing item to be consumed
    /**
     * An item to be consumed
     */
    private Consumable item;

    // constructor
    /**
     * Default constructor for ConsumeAction class to consume an item
     *
     * @param item an item to be consumed
     */
    public ConsumeAction(Consumable item){

        this.item = item;

    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // declare output
        String ret = "";

//         pass actor to consume
//        item.consume(actor)

        item.heal(actor);
        item.setNumberOfUses(item.getNumberOfUses()-1);

        actor.removeItemFromInventory(item);

        ret += actor + " has used the " + item + " to heal himself.\n";
        ret += actor + " New Attributes: \n";
        ret += String.format("HP: %d/%d\n", actor.getAttribute(BaseActorAttributes.HEALTH), actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
        ret += String.format("Stamina: %d/%d\n", actor.getAttribute(BaseActorAttributes.STAMINA), actor.getAttributeMaximum(BaseActorAttributes.STAMINA));

        // return output
        return ret;

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

        // declare output
        String ret = "";

        // return output
        ret = actor + " consumes the " + item;

        return ret;

    }
}
