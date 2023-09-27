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
 * @author yangdan
 */
public class GreatSlamAction extends Action {


    private Actor enemy;
    private float reducedStaminaRate;
    private WeaponItem weapon;


    public GreatSlamAction(Actor enemy, float reducedStaminaRate, WeaponItem weapon){
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
        string += new AttackAction(enemy, "", this.weapon).execute(actor, map) + "\n";
        this.weapon.updateDamageMultiplier(0.5f);
        for (Exit exit : map.locationOf(actor).getExits()){
            Location location = exit.getDestination();
            if (location.containsAnActor() && location.getActor().hasCapability(Status.HOSTILE_TO_PLAYER)){
                Actor enemy = location.getActor();
                string += new AttackAction(enemy, "", this.weapon).execute(actor, map) + "\n";
            }
        }
        string += new AttackAction(actor, "", this.weapon).execute(actor, map) + "\n";
        this.weapon.updateDamageMultiplier(1.0f);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int)(reducedStaminaRate * actor.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        return string;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of " + this.weapon + " on " + this.enemy;
    }
}
