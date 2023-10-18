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

  // ???
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
    ResetManager.getInstanceReset().registerResettable(this, true);

    // default value
    this.reset = false;

  }

  // just for new runes created during reset
  public Rune(int value, boolean isAdd){

    super(value + " Runes", '$', true);
    this.value = value;

    // register to reset manager
    ResetManager.getInstanceReset().registerResettable(this, isAdd);

    // default value
    this.reset = false;
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

  /**
   * Provides a way for any entities be it actors or items or grounds on the GameMap that have to be reset
   * after player dies due to any causes
   */
  @Override
  public void reset() {

    // remove rune from the ground after game resets
    // -> All runes dropped in previous turns that the player did not pick up will also be
    // wiped from the map (runes -> enemies & player)
    this.reset = true;

  }

  // remove runes from current location if the player didn't pick up
  @Override
  public void tick(Location currentLocation) {

    if (reset) {
      currentLocation.removeItem(this);
      ResetManager.getInstanceReset().removeResettable(this);
    }

  }

}
