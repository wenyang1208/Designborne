package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author yangdan
 */
public abstract class Trader extends Actor {


    private HashMap<Item, Function<Actor, String>> functionMap = new HashMap<>();


    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability( Status.TRADER );
    }


    public abstract void configure();


    public void addPair(Item item, Function<Actor, String> function){
        this.functionMap.put(item, function);
    }


    @Override
    public String toString() {
        return name;
    }


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Map.Entry<Item, Function<Actor, String>> entry : this.functionMap.entrySet()){
                Item item = entry.getKey();
                Function<Actor, String> function = entry.getValue();
                actions.add( new PurchaseAction(item, function));
            }
        }
        return actions;
    }

}

