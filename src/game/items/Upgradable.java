package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing items that can be upgraded
 *
 * Created by:
 * @author Chua Wen Yang
 *
 * Modified by:
 * @author Koe Rui En
 */
public interface Upgradable {

  /**
   * Upgrade the item using the provided actor.
   *
   * @param actor The actor performing the upgrade.
   *
   * @return A string describing the result of the upgrade action.
   */
  String upgradedBy(Actor actor);

  /**
   * Get the upgrading price of an item to be upgraded
   *
   * @return an integer value representing the upgrading price of an item
   */
  int getUpgradingPrice();

}
