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
 * @author yangdan
 */
public class StabAndStepAction extends Action {


    private Actor enemy;
    private float reducedStaminaRate;
    private WeaponItem weapon;


    public StabAndStepAction(Actor enemy, float reducedStaminaRate, WeaponItem weapon){
        this.weapon = weapon;
        this.reducedStaminaRate = reducedStaminaRate;
        this.enemy = enemy;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getAttribute(BaseActorAttributes.STAMINA) < (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)) ){
            return actor + "can't activate " + this.weapon + "'s skill because of insufficient stamina.";
        }
        String string = "";
        string += new AttackAction(this.enemy, "", this.weapon).execute(actor, map);
        string += "\n";
        for (Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (!location.containsAnActor()) {
                string += new MoveActorAction(location, "to " + location.toString()).execute(actor, map);
                string += "\n";
                break;
            }
        }
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return string;
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of " + this.weapon + " on " + this.enemy;
    }


}
