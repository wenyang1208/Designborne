// declare package
package game.weapons;

// import engine and game packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.FocusAction;
import game.actions.AttackAction;
import game.utils.Ability;
import game.utils.Status;


 /**
 * Class representing the Broadsword weapon that can be used to attack the enemy.
 *
 * Created by:
 * @author Koe Rui En
  *
  * Modified by:
  * @author Yang Dan
 *
 */
public class Broadsword extends WeaponItem {


    /* Default constants for the Broadsword object */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private static final int DEFAULT_IT_RATE = 80;

    /* Default constants for the skill activation */
    private static final float INCREASED_DAMAGE_MULTIPLIER = 0.1f;
    private static final int ACTIVATED_HIT_RATE = 90;
    private static final float REDUCED_STAMINA_RATE = 0.2f;
    private static final int FOCUS_DURATION = 5;

    /* Attribute of the Broadsword */
    private int remainingFocusTurn;


    /**
     * Constructor of the Broadsword object
     * It has one skill
     * remainingFocusTurn will be set to 5 when it is created
     */
    public Broadsword() {
        super("BroadSword", '1', 110, "slashes", DEFAULT_IT_RATE);
        this.remainingFocusTurn = FOCUS_DURATION;
        this.addCapability(Ability.SKILL);
    }


    /**
     * Reset the attribute 'remainingFocusTurn' to 5
     */
    public void resetFocusTurn(){
        this.remainingFocusTurn = FOCUS_DURATION;
    }


    /**
     * When the broad sword is on the ground and it is still activated.
     * It means that the player just dropped it last turn. It should be inactivated by
     * reverting everything back to default.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.FULLY_ACTIVATED)){
            this.removeCapability(Status.FULLY_ACTIVATED);
            this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
            this.updateHitRate(DEFAULT_IT_RATE);
            this.resetFocusTurn();
        }
    }


    /**
     * When the broad sword is in player's inventory, the duration of the focus effect should be reduced by 1
     * if it is been activated.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.hasCapability(Status.JUST_ACTIVATED)){
            this.resetFocusTurn();
            this.addCapability(Status.FULLY_ACTIVATED);
            this.removeCapability(Status.JUST_ACTIVATED);
        } else if(this.hasCapability(Status.FULLY_ACTIVATED)){ // normally reduce the duration by 1
            this.remainingFocusTurn--;
        }
        // If the effect time of the weapon in the inventory has expired, revert it back.
        if (this.remainingFocusTurn == 0){
            this.removeCapability(Status.FULLY_ACTIVATED);
            this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
            this.updateHitRate(DEFAULT_IT_RATE);
        }
    }


    /**
     * Player can use 'Focus' skill as long as this broad sword is in the inventory.
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        return new ActionList( new FocusAction(this, INCREASED_DAMAGE_MULTIPLIER, ACTIVATED_HIT_RATE, REDUCED_STAMINA_RATE) );
    }

    /**
     * Player can attack other actor with this broad sword.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        return new ActionList( new AttackAction(otherActor, location.toString(), this) );
    }
}
