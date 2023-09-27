package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.*;


/**
 * Represents the SellAction class that allows an actor to sell items to a Traveller.
 *
 * Created by: Chai Jun Lun
 *
 * Midified by: Yang dan
 */
public class SellAction extends Action {


    private final Sellable item;
    private final String name;
    private final Actor trader;


    public SellAction(Sellable item, String name, Actor trader){
        this.item = item;
        this.name = name;
        this.trader = trader;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        return this.item.sell(actor, trader);
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + this.name + ".";
    }


}
