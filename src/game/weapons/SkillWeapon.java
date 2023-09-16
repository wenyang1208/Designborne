// declare package
package game.weapons;

// import engine and game packages
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Class representing WeaponItems that has as a skill.
 *
 * Created by:
 * @author Koe Rui En
 *
 */
public abstract class SkillWeapon extends WeaponItem {

    /**
     * Constructor of the SkillWeapon
     *
     * @param name        name of the SkillWeapon
     * @param displayChar character to use for display when SkillWeapon is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon
     * @param hitRate     the probability/chance to hit the target.
     */
    public SkillWeapon(String name, char displayChar, int damage, String verb, int hitRate) {

        super(name, displayChar, damage, verb, hitRate);

    }

    // abstract method

    /**
     * Reset the weapon to its original state
     */
    public abstract void resetWeapon();

    /**
     * Set the initial status of the skill (True: active, false: inactive)
     *
     * @param newStatus a boolean representing the initial status of the skill
     */
    public abstract void setSkillStatus(boolean newStatus);

    /**
     * Get the number of turns of skill
     *
     * @return an integer containing the number of turns of skill
     */
    public abstract int getSkillTurn();

    /**
     * Get the status of the skill
     *
     * @return a boolean containing the initial status of the skill
     */
    public abstract boolean getSkillStatus();

    /**
     * Set the number of turns of skill
     *
     * @param newTurn an integer containing the number of turns of skill
     */
    public abstract void setSkillTurn(int newTurn);







}


