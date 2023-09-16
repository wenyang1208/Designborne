// declare package
package game.weapons;

// import engine and game package
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.utils.Status;

// starting weapon item for the player
// extend from weapon item class that implement item
/**
 * Class representing the Broadsword weapon that can be used to attack the enemy.
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class Broadsword extends SkillWeapon {

    // skill turn
    /**
     * Number of turns of skill
     */
    private int skillTurn;

    // broadsword special skill: Focus status (true: active, false: inactive)
    /**
     * Broadsword's skill status (true: active, false: inactive)
     */
    private boolean skillActiveStatus;


    /**
     * Constructor of the Broadsword class
     *
     */

    // default constructor
    public Broadsword() {

        // instantiate weapon item with corresponding attributes
        // damage = 110, attack accuracy = 80%
        // char 1
        super("Broadsword", '1', 110, "slashes", 80);

        // Broadsword has to be picked up, override value again
        portable = true;

        // set skill turn (initial 5 turns）
        setSkillTurn(5);

        // skill initial status
        setSkillStatus(false);

        // broadsword has focus skill action
        this.addCapability(Status.HAS_SPECIAL_SKILL);

    }

    // get skill turn
    /**
     * Get the number of turns of skill
     *
     * @return an integer containing the number of turns of skill
     */
    public int getSkillTurn() {
        return skillTurn;
    }

    // set skill turn
    /**
     * Set the number of turns of skill
     *
     * @param newTurn a integer containing the number of turns of skill
     *
     */
    public void setSkillTurn(int newTurn) {

        if (newTurn >= 0) {

            skillTurn = newTurn;
        }

    }

    // get skill status
    /**
     * Get the status of the skill
     *
     * @return a boolean containing the initial status of the skill
     */
    public boolean getSkillStatus() {
        return skillActiveStatus;
    }

    // set skill status
    /**
     * Set the initial status of the skill (True: active, false: inactive)
     *
     * @param newStatus a boolean representing the initial status of the skill
     */
    public void setSkillStatus(boolean newStatus) {

        skillActiveStatus = newStatus;

    }


    // reset the weapon status to ori state
    /**
     * Reset the weapon to its original state
     */
   public void resetWeapon() {

        this.updateHitRate(80);
        this.updateDamageMultiplier(1.0f);
        this.setSkillStatus(false);

   }

    // allowable action
    // Broadsword has special skill (create focus action)
    /**
     * List of allowable actions that the Broadsword can perform to the current actor
     *
     * @param owner the actor that owns the broadsword
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {

        // declare ret, list of actions
        ActionList actions = new ActionList();

        // add relevant actions into list
        actions.add(new FocusAction(this));

        // return output, list of actions
        return actions;

    }

    /**
     * List of allowable actions that the Broadsword allows its owner do to other actor.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions (Actor otherActor, Location location){

        // declare ret, list of actions
        ActionList actions = new ActionList();

        // add actions into list
        actions.add(new AttackAction(otherActor, location.toString(),this));

        return actions;
    }


    // keep track of focus action turn
    /**
     * Inform a carried Item of the passage of time.
     *
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location currentLocation, Actor actor) {

        // skill active and deduct skill turn every turn
        if (getSkillStatus()) {
            int newTurn = getSkillTurn() - 1;
            setSkillTurn(newTurn);
            new Display().println("Skill Turn Left: " + (newTurn+1));

            // after 6 turns, reset to ori state
            if (newTurn < 0) {

                // reset weapon status after 5 turns
                new Display().println("No more skill turns left.");
                resetWeapon();

            }
        }
    }

}
