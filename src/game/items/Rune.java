package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * A class representing a Rune item that can be consumed by actors to increase their balance.
 * Runes are consumable items that, when consumed, increase the actor's balance and remove them from the actor's inventory.
 *
 * Created by:
 * @author Chua Wen Yang
 */
public class Rune extends Item implements Consumable{

  /**
   * value of rune
   */
  private int value;

  /**
   * Constructor for the Rune class.
   *
   * @param value The value of the Rune, which represents the amount it increases the actor's balance when consumed.
   */
  public Rune(int value) {
    super(value + " Runes", '$', true);
    this.value = value;
  }

  /**
   * Retrieves the list of allowable actions for the owner actor when interacting with this Rune item.
   *
   * @param owner The actor interacting with the Rune.
   * @return An ActionList containing the ConsumeAction for consuming this Rune.
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    return new ActionList( new ConsumeAction(this) );
  }

  /**
   * Consumes the Rune, increasing the actor's balance by the specified value and removing it from the actor's inventory.
   *
   * @param owner The actor consuming the Rune.
   * @return A message indicating that the actor has consumed the Rune.
   */
  @Override
  public String consume(Actor owner) {
    owner.addBalance(this.value);
    owner.removeItemFromInventory(this);
    return owner + "consumes " + this + ", and it increases the wallet balance by " + this.value + ".";
  }

}
