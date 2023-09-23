package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

/**
 * A FocusAction class that represents the special skill of Broadsword weapon
 *
 * Created by:
 * @author Koe Rui En
 *
 * Modified by:
 * @author Yang Dan
 *
 */
public class FocusAction extends Action {

    /* Attributes */
    /**
     * Weapon that have special skill, Focus
     */
    private WeaponItem weapon;
    /**
     * Damage multiplier of weapon to be increased
     */
    private float damageMultiplierIncrease;
    /**
     * Hit rate of weapon to be activated
     */
    private int activatedHitRate;
    /**
     * Stamina rate needed to be reduced when Focus action is activated
     */
    private float reducedStaminaRate;


    /**
     * Constructor of FocusAction class
     *
     * @param weapon weapon that is to be activated
     * @param damageMultiplierIncrease Amount of increase in weapon's damage multiplier after activation
     * @param activatedHitRate New hit rate after activation
     * @param reducedStaminaRate Stamina required to activate the skill
     *
     */
    public FocusAction(WeaponItem weapon, float damageMultiplierIncrease, int activatedHitRate, float reducedStaminaRate){
        this.weapon = weapon;
        this.damageMultiplierIncrease = damageMultiplierIncrease;
        this.activatedHitRate = activatedHitRate;
        this.reducedStaminaRate = reducedStaminaRate;
    }


    /**
     * Perform focus action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // The player can't activate skill when there is not enough stamina value
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) ){
            return actor + "can't activate " + this.weapon.toString() + "'s skill because of insufficient stamina.";
        }
        // Indicate that the weapon is successfully
        this.weapon.addCapability(Status.JUST_ACTIVATED);
        // Increase multiplier and hit rate. Decrease stamina
        this.weapon.increaseDamageMultiplier( this.damageMultiplierIncrease );
        this.weapon.updateHitRate( this.activatedHitRate );
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return actor + " takes a deep breath and focuses all their might!";
    }


    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of " + this.weapon.toString();
    }
}
