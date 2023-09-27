package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class represents the action to perform Great Knife's special skill.
 *
 * @author yangdan
 */
public class StabAndStepAction extends Action {


    /**
     * Actor to be attacked
     */
    private Actor enemy;
    /**
     * Stamina rate needed to be reduced when perform this sepcial attack
     */
    private float reducedStaminaRate;
    /**
     * Weapon that having this stab and step skill
     */
    private WeaponItem weapon;


    /**
     * Constructor of this action
     * @param enemy Actor to be attacked
     * @param reducedStaminaRate Stamina rate needed to be reduced when perform this sepcial attack
     * @param weapon Weapon that having this stab and step skill
     */
    public StabAndStepAction(Actor enemy, float reducedStaminaRate, WeaponItem weapon){
        this.weapon = weapon;
        this.reducedStaminaRate = reducedStaminaRate;
        this.enemy = enemy;
    }


    /**
     * Perform this stab and step action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // The player can't use the special skill when there is not enough stamina value
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) ){
            return actor + "can't activate " + this.weapon + "'s skill because of insufficient stamina.";
        }
        String string = "";

        // attack the enemy
        string += new AttackAction(this.enemy, "", this.weapon).execute(actor, map) + "\n";

        // randomly choose a unoccupied location to step to
        for (Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (!location.containsAnActor()) {
                string += new MoveActorAction(location, "to " + location.toString()).execute(actor, map);
                string += "\n";
                break;
            }
        }

        // reduce stamina
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
