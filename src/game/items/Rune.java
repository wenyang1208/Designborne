package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * A class representing a Rune item that can be consumed by actors to increase their balance.
 * Runes are consumable items that, when consumed, increase the actor's balance and remove them from the actor's inventory.
 *
 * Created by:
 * @author Chua Wen Yang
 *
 * Modified by:
 * @author Koe Rui En
 */
public class Rune extends Item implements Consumable, Resettable{

  /**
   * value of rune
   */
  private int value;

  // boolean value to indicate reset condition
  /**
   * Boolean value to indicate reset condition
   */
  private boolean reset;

  /**
   * Constructor for the Rune class.
   *
   * @param value The value of the Rune, which represents the amount it increases the actor's balance when consumed.
   */
  public Rune(int value) {

    super(value + " Runes", '$', true);
    this.value = value;

    // register to reset manager
    ResetManager.getInstanceReset().registerResettable(this);

    // default value
    this.reset = false;

  }

  /**
   * Retrieves the list of allowable actions for the owner actor when interacting with this Rune item
   *
   * @param owner The actor interacting with the Rune
   *
   * @return An ActionList containing the ConsumeAction for consuming this Rune.
   */
  @Override
  public ActionList allowableActions(Actor owner) {
    return new ActionList( new ConsumeAction(this) );
  }

  /**
   * Consumes the Rune, increasing the actor's balance by the specified value and removing it from the actor's inventory
   *
   * @param owner The actor consuming the Rune
   *
   * @return A message indicating that the actor has consumed the Rune
   */
  @Override
  public String consume(Actor owner) {
    owner.addBalance(this.value);
    owner.removeItemFromInventory(this);
    return owner + "consumes " + this + ", and it increases the wallet balance by " + this.value + ".";
  }

  /**
   * Provides a way for all the runes to wiped off when it is on the ground except the runes dropped by the Player
   * after player dies due to any causes and triggers the game reset
   */
  @Override
  public void reset() {

    // remove rune from the ground after game resets
    // -> All runes dropped in previous turns that the player did not pick up will also be
    // wiped from the map (runes -> enemies & player)
    this.reset = true;

  }

  // remove runes from current location if the player didn't pick up
  /**
   * Inform an Item on the ground of the passage of time
   *
   * This method is called once per turn, if the item rests upon the ground.
   *
   * @param currentLocation The location of the ground on which we lie.
   */
  @Override
  public void tick(Location currentLocation) {

    if (reset) {
      currentLocation.removeItem(this);
      ResetManager.getInstanceReset().removeResettable(this);
    }

  }

}
