package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A class representing a Bloodberry item that can be consumed by actors to increase their maximum health.
 * Bloodberry is a consumable item that, when consumed, increases the maximum health of the actor consuming it.
 * It also removes the Bloodberry from the actor's inventory.
 *
 * Created by:
 * @author Chua Wen Yang
 */
public class Bloodberry extends Item implements Consumable{

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
}
