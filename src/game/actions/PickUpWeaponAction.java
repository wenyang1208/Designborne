// declare package
package game.actions;

// import game and engine packages
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.SkillWeapon;

/**
 * A PickUpWeaponAction class that represents an action for picking up a skilled weapon and adding into the actor's inventory.
 *
 * Created by:
 * @author Koe Rui En
 */
public class PickUpWeaponAction extends PickUpAction {

    // weapon item that has special skill
    /**
     * Special skilled weapon item
     */
    private SkillWeapon weaponItem;


    /**
     * Constructor of the PickUpWeaponAction class
     *
     * @param weapon the item to drop
     */
    public PickUpWeaponAction(SkillWeapon weapon) {

        super(weapon);
        this.weaponItem = weapon;

    }

    /**
     * When a skilled weapon item is dropped, add the skilled weapon item from the actor's inventory
     * and add it to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return a string describing who has dropped a skilled weapon item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // reset skill weapon to ori state
        weaponItem.resetWeapon();

        // call super execute method
        super.execute(actor, map);

        // return description
        return menuDescription(actor);

    }

}
