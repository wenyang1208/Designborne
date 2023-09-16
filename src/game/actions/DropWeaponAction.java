// declare package
package game.actions;

// import engine and game packages
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.SkillWeapon;

/**
 * A DropWeaponAction class that represents an action for dropping a skilled weapon from the actor's inventory
 *
 * Created by:
 * @author Koe Rui En
 */
public class DropWeaponAction extends DropAction{

    // weapon item that has special skill
    /**
     * Special skilled weapon item
     */
    private SkillWeapon weaponItem;


    /**
     * Constructor of the DropWeaponSkillAction class
     *
     * @param weapon the weapon item to be dropped
     */
    public DropWeaponAction(SkillWeapon weapon) {

        super(weapon);
        this.weaponItem = weapon;

    }

    /**
     * When a skilled weapon item is dropped, remove the skilled weapon item from the actor's inventory
     * and add it to the current location of the actor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     *
     * @return a string describing who has dropped skilled weapon item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        weaponItem.resetWeapon();
        actor.removeItemFromInventory(weaponItem);
        map.locationOf(actor).addItem(weaponItem);
        return menuDescription(actor);

    }



}
