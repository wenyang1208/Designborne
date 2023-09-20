package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

/**
 * class representing an action that activate weapon's skill
 */
public class ActivateSkillAction extends Action {


    /* Attributes */
    private WeaponItem weapon;
    private float damageMultiplierIncrease;
    private int activatedHitRate;
    private float reducedStaminaRate;


    /**
     * Constructor of this ActivateSkillAction
     * @param weapon weapon that is to be activated
     * @param damageMultiplierIncrease Amount of increase in weapon's damage multiplier after activation
     * @param activatedHitRate New hit rate after activation
     * @param reducedStaminaRate Stamina required to activate the skill
     */
    public ActivateSkillAction(WeaponItem weapon, float damageMultiplierIncrease, int activatedHitRate, float reducedStaminaRate){
        this.weapon = weapon;
        this.damageMultiplierIncrease = damageMultiplierIncrease;
        this.activatedHitRate = activatedHitRate;
        this.reducedStaminaRate = reducedStaminaRate;
    }


    /**
     * Perform activation action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
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
     * @param actor The actor performing the action.
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of " + this.weapon.toString();
    }
}
