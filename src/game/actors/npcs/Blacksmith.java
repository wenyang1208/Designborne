package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.Status;

/**
 * A class that represents Blacksmith.
 *
 * Created by:
 * @author Chua Wen Yang
 *
 */
public class Blacksmith extends Actor {
  /**
   * The constructor of the blacksmith class.
   */
  public Blacksmith() {
    super("Blacksmith", 'B', 100);
    this.addCapability(Status.UPGRADE_PERSON);
  }

  /**
   * Select the valid action with the highest priority
   *
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   *
   * @return the Action to be performed
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
    return new DoNothingAction();
  }
}
