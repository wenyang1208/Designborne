package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

/**
 * A GreatSlamAction class represents the action to perform Giant Hammer's special skill.
 *
 * Created by:
 * @author Yang Dan
 */
public class GreatSlamAction extends Action {

    /**
     * Actor to be attacked
     */
    private Actor enemy;
    /**
     * Stamina rate needed to be reduced when perform this sepcial attack
     */
    private float reducedStaminaRate;
    /**
     * Weapon that having this great slam skill
     */
    private WeaponItem weapon;


    /**
     * Constructor of GreatSlamAction class
     *
     * @param enemy Actor to be attacked
     * @param reducedStaminaRate Stamina rate needed to be reduced when perform this sepcial attack
     * @param weapon Weapon that having this great slam skill
     */
    public GreatSlamAction(Actor enemy, float reducedStaminaRate, WeaponItem weapon){
        this.weapon = weapon;
        this.reducedStaminaRate = reducedStaminaRate;
        this.enemy = enemy;
    }


    /**
     * Perform this great slam action
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return string description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // The player can't use the special skill when there is not enough stamina value
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) ){
            return actor + "can't activate " + this.weapon + "'s skill because of insufficient stamina.";
        }

        String string = "";

        // First, attack the main enemy, dealing 100% of the weapon damage
        string += new AttackAction(enemy, "", this.weapon).execute(actor, map) + "\n";

        // Set the damage multiplier of this weapon to 0.5 so that it attacks the rest with only 50% damage
        this.weapon.updateDamageMultiplier(0.5f);
        for (Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_PLAYER)){
                Actor enemy = location.getActor();
                string += new AttackAction(enemy, "", this.weapon).execute(actor, map) + "\n";
            }
        }
        // Player will also be affected by this special effect with 50% damage of this weapon
        string += new AttackAction(actor, "", this.weapon).execute(actor, map) + "\n";

        // Set the damage multiplier back to normal
        this.weapon.updateDamageMultiplier(1.0f);
        // Reduce stamina
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return string;
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
        return actor + " activates the skill of " + this.weapon + " on " + this.enemy;
    }
}
