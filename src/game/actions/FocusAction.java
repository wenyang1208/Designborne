// declare package
package game.actions;

// import engine and game packages
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.weapons.Broadsword;
import game.weapons.SkillWeapon;

// special skill of Broadsword
/**
 * A FocusAction class that represents the special skill of Broadsword weapon
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public class FocusAction extends Action {

    // focus skill (action) has broadsword
    /**
     * Instance of the Broadsword class
     */
    private Broadsword weapon;

    // skill name
    /**
     * Special skill name
     */
    private String skillName;


    // constructor
    /**
     * Default constructor of the FocusAction class
     *
     * @param iniBroadsword the instance of the Broadsword class
     */
    public FocusAction(Broadsword iniBroadsword) {

        // initialise focus action instance
        setBroadsword(iniBroadsword);

        // set name of focus
        setSkillName("Focus");

        // set (not increase) the weapon’s hit rate to 90%
        weapon.updateHitRate(90);

    }

    // getter

    // get skill name
    /**
     * A getter to get skill name
     */
    public String getSkillName() {return skillName;}


    // setter

    // skill name
    /**
     * A setter to set the name of the skill
     *
     * @param newSkillName the name of the skill
     */
    public void setSkillName(String newSkillName) {

        if (newSkillName.length() >= 1){

            skillName = newSkillName;

        }
    }

    // broadsword
    /**
     * A setter to set the instance of the Broadsword class
     *
     * @param newBroadsword the instance of the Broadsword class
     */
    public void setBroadsword(Broadsword newBroadsword){

        if (newBroadsword != null){

            weapon = newBroadsword;

        }
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     *
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // declare out
        String ret = "";

        //  skill is active
        weapon.setSkillStatus(true);

        //  skill turn
        weapon.setSkillTurn(5);

        // increase the weapon’s damage multiplier by 10% for 5 turns and
        // weapon damage = 10% // 0.1f
        weapon.increaseDamageMultiplier(0.1f);

        // player's stamina is reduced by 20% of their maximum stamina
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,(int)(actor.getAttributeMaximum(BaseActorAttributes.STAMINA)* 0.2));

        // description of action after being executed
        ret += "The skill," + getSkillName() + ",has successfully been activated." + "\n";
        ret += "Broadsword New Hit Rate: " + weapon.chanceToHit() + "\n";
        ret += "Broadsword New Damage: " + weapon.damage() + "\n";

        // return output
        return ret;

    }

    // return the action description to console menu (for user to choose)
    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     *
     * @return the action description to be displayed on the menu
     */
    @Override
    public String menuDescription(Actor actor) {

        // declare output
        String ret = "";

        ret = actor + " activates the skill " + getSkillName();

        // return out
        return ret;

    }
}
