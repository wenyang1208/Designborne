package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.*;


/**
 * A class represents the SellAction class that allows an actor to sell items to traders.
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yang Dan
 */
public class SellAction extends Action {

    /**
     * An item to be sold
     */
    private final Sellable item;

    /**
     * Constructor of SellAction class.
     *
     * @param item an item to be sold
     */
    public SellAction(Sellable item){
        this.item = item;
    }


    /**
     * Perform the sell action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return string description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        return item.soldBy(actor);
    }


    /**
     * Describe what action will be performed in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        return actor + " sells " + item + ".";

    }


}
