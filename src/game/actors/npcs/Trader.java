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


    /**
     * Each trader has their own items to sell, and each item is sold in a different way.
     * Use hashmap to store it, and key will be the item to be sold, value will be a method containing the algorithem of
     * selling this specific item.
     */
    private HashMap<Item, Function<Actor, String>> functionMap = new HashMap<>();


    /**
     * Constructor of the trader
     * @param name name of the trader
     * @param displayChar the character that will represent the trader in the display
     * @param hitPoints the trader's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability( Status.TRADER ); // This can be used to identify if the actor is a trader or not.
    }


    /**
     * This abstract method will be overridden by concrete trader sub classes.
     * And it establishes the hashmap of the trader
     */
    public abstract void configure();


    /**
     * Add a key-value pair to the hashMap
     * @param item Item sold by the trader
     * @param function Way of selling this specific item by the trader
     */
    public void addPair(Item item, Function<Actor, String> function){
        this.functionMap.put(item, function);
    }


    /**
     * Only the name of the trader is shown instead of the name and hp level like the default.
     * @return Name of the trader
     */
    @Override
    public String toString() {
        return name;
    }


    /**
     * Trader will do nothing
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction instance
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    /**
     * When the trader is around the palyer, it allows player to take purchase action
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){ // trader will sell items to PLAYER
            for (Map.Entry<Item, Function<Actor, String>> entry : this.functionMap.entrySet()){
                Item item = entry.getKey(); // get the item sold by this trader
                Function<Actor, String> function = entry.getValue(); // get the way of selling this item by this trader
                actions.add( new PurchaseAction(item, function)); // create a purchase action
            }
        }
        return actions;
    }

}

