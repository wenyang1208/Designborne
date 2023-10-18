package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Upgradable;
import edu.monash.fit2099.engine.actions.Action;

/**
 * An Upgrade class to upgrade the items of the player.
 *
 * Created by:
 * @author Chua Wen Yang
 *
 */
public class UpgradeAction extends Action {
  private final Upgradable item;

  public UpgradeAction(Upgradable item){
    this.item = item;
  }

  /**
   * Perform the upgrade action.
   *
   * @param actor The actor performing the action.
   * @param map The map the actor is on.
   *
   * @return string description of what happened (the result of the action being performed) that can be displayed to the user.
   */
  @Override
  public String execute(Actor actor, GameMap map) {
    return item.upgradedBy(actor);
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

    return actor + " upgrades " + item + ".";

  }
}
