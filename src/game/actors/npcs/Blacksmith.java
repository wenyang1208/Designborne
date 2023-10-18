package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.monologues.Monologue;
import game.monologues.Speaker;
import game.utils.Ability;
import game.utils.Status;

import java.util.ArrayList;

/**
 * A class that represents Blacksmith.
 *
 * Created by:
 * @author Chua Wen Yang
 *
 * Modified by:
 * @author Yang Dan
 */
public class Blacksmith extends Actor implements Speaker {

  /**
   * A list of monologues that can be spoken to the player
   */
  private final ArrayList<Monologue> monologues = new ArrayList<>();

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

  /**
   * Generates a collection of monologues for an Actor based on their capabilities and the game context.
   *
   * @param actor The Actor for whom the monologues are being generated.
   *
   * @return An ArrayList of Monologue objects containing dialogues tailored to the Actor's capabilities and the game context.
   */
  @Override
  public ArrayList<Monologue> generateMonologues(Actor actor) {
    monologues.add( new Monologue(true, "I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.", "BlackSmith") );
    monologues.add( new Monologue(true, "It’s dangerous to go alone. Take my creation with you on your adventure!", "BlackSmith") );
    monologues.add( new Monologue(true, "Ah, it’s you. Let’s get back to make your weapons stronger.", "BlackSmith") );
    monologues.add( new Monologue(actor.hasCapability(Ability.DEFEATED_ABXERVYER), "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.", "BlackSmith") );
    monologues.add( new Monologue(!actor.hasCapability(Ability.DEFEATED_ABXERVYER), "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!", "BlackSmith") );
    monologues.add( new Monologue(actor.hasCapability(Ability.STAB_AND_STEP), "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.", "BlackSmith") );
    return monologues;
  }

  /**
   * Returns a new collection of the Actions that the otherActor can do to Traveller
   *
   * @param otherActor the Actor that might be performing attack
   * @param direction  String representing the direction of the other Actor
   * @param map        current GameMap
   *
   * @return A collection of Actions.
   */
  @Override
  public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
    if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.COMPLETE)){
      return new ActionList(new ListenAction(this));
    }
    return new ActionList();
  }

}
