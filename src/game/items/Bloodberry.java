package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A class representing a Bloodberry item that can be consumed by actors to increase their maximum health.
 * Bloodberry is a consumable item that, when consumed, increases the maximum health of the actor consuming it.
 * It also removes the Bloodberry from the actor's inventory.
 *
 * Created by:
 * @author Chua Wen Yang
 *
 * Modified by
 * @author Yang Dan
 */
public class Bloodberry extends Item implements Consumable, Sellable{

  /**
   * Constructor for the Bloodberry class.
   * Creates a Bloodberry item with the name "Bloodberry", represented by '*', and makes it stackable.
   */
  public Bloodberry() {
    super("Bloodberry", '*', true);
  }

  /**
   * Retrieves the list of allowable actions for the owner actor when interacting with this Bloodberry item.
   *
   * @param owner The actor interacting with the Bloodberry.
   *
   * @return An ActionList containing the ConsumeAction for consuming this Bloodberry.
   */
  @Override
  public ActionList allowableActions(Actor owner) {

    return new ActionList( new ConsumeAction(this) );

  }

  /**
   * Consumes the Bloodberry, increasing the actor's maximum health by 5 points and removing it from the actor's inventory.
   *
   * @param owner The actor consuming the Bloodberry.
   * @return A message indicating that the actor has consumed the Bloodberry and the increase in maximum health.
   */
  @Override
  public String consume(Actor owner) {
    owner.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE,5);
    owner.removeItemFromInventory(this);
    return owner + " consumes " + this + ", and it increases the maximum health by " + 5 + " points.";
  }

  /**
   * List of allowable actions that Bloodberry allows its owner do to other actor
   *
   * The player will sell item to the other actor if the other actor has the capability of Status.TRADER
   *
   * @param otherActor the other actor
   * @param location the location of the other actor
   *
   * @return an unmodifiable list of Actions
   */
  @Override
  public ActionList allowableActions(Actor otherActor, Location location) {
    if (otherActor.hasCapability(Status.TRADER))
      return new ActionList( new SellAction(this) );
    return new ActionList();
  }

  /**
   * Get the selling price of the Bloodberry
   *
   * @return an integer value representing the selling price of Bloodberry
   */
  @Override
  public int getSellingPrice() {
    return 10;
  }

  /**
   * Sell the Bloodberry to the trader
   *
   * @param actor Actor who sells Bloodberry at the sale stage
   *
   * @return a string showing the result of selling Bloodberry
   */
  @Override
  public String soldBy(Actor actor) {
    actor.removeItemFromInventory( this );
    int price = getSellingPrice();
    actor.addBalance( price );
    return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
  }

}
