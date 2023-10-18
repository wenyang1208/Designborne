package game.actors.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.PurchaseAction;
import game.items.HealingVial;
import game.items.Purchasable;
import game.items.RefreshingFlask;
import game.monologues.Monologue;
import game.monologues.Speaker;
import game.utils.Ability;
import game.utils.Status;
import game.weapons.Broadsword;
import game.weapons.GreatKnife;

import java.util.ArrayList;

/**
 * A class that represent Isolated Traveller in the Forest
 *
 * Created by:
 * @author Chai Jun Lun
 *
 * Modified by:
 * @author Yang Dan
 * @author Chai Jun Lun
 *
 */
public class Traveller extends Actor implements Speaker {

    /**
     * A list of monologues that can be spoken to the player
     */
    private final ArrayList<Monologue> monologues = new ArrayList<>();
    /**
     * A list of Purchasable items
     */
    private ArrayList<Purchasable> purchasables = new ArrayList<>();


    /**
     * Constructor of the Traveller class
     *
     */
    public Traveller() {
        super("Isolated Traveller", 'ඞ', 100);
        this.addCapability(Status.TRADER);
        configure();
    }


    /**
     * A method to add items to be carried and sold to other actor into a list of Purchasable items
     */
    private void configure(){
        this.purchasables.add( new HealingVial() );
        this.purchasables.add( new RefreshingFlask() );
        this.purchasables.add( new Broadsword() );
        this.purchasables.add( new GreatKnife() );
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
        ActionList actions  = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Purchasable purchasable : this.purchasables){
                actions.add( new PurchaseAction(purchasable));
            }
            actions.add(new ListenAction(this));
        }
        return actions;
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
        monologues.add( new Monologue(true, "Of course, I will never give you up, valuable customer!", this) );
        monologues.add( new Monologue(true, "I promise I will never let you down with the quality of the items that I sell.", this) );
        monologues.add( new Monologue(true, "You can always find me here. I'm never gonna run around and desert you, dear customer!", this) );
        monologues.add( new Monologue(true, "I'm never gonna make you cry with unfair prices.", this) );
        monologues.add( new Monologue(true, "Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.", this) );
        monologues.add( new Monologue(true, "Don't worry, I’m never gonna tell a lie and hurt you.", this) );
        monologues.add( new Monologue(!actor.hasCapability(Ability.DEFEATED_ABXERVYER), "You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.", this) );
        monologues.add( new Monologue(actor.hasCapability(Ability.GREAT_SLAM), "Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.", this) );
        monologues.add( new Monologue(actor.hasCapability(Ability.DEFEATED_ABXERVYER) && actor.hasCapability(Ability.GREAT_SLAM), "Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.", this) );
        return monologues;
    }
}
