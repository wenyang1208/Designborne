// declare package
package game.weapons;

// import engine and game packages
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.*;
import game.items.Purchasable;
import game.items.Upgradable;
import game.utils.Ability;
import game.utils.Status;
import game.actions.SellAction;
import game.items.Sellable;


 /**
 * Class representing the Broadsword weapon that can be used to attack the enemy.
 *
 * Created by:
 * @author Koe Rui En
  *
  * Modified by:
  * @author Yang Dan
 * @author Chua Wen Yang
 */
public class Broadsword extends WeaponItem implements Sellable, Purchasable, Upgradable {

    /* Default constants for the Broadsword object */
     /**
      * Default constants for the damage multiplier used to activate the skill of broadsword
      */
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
     /**
      * Default constants for the hit rate used to activate the skill of broadsword
      */
    private static final int DEFAULT_HIT_RATE = 80;

    /* Default constants for the skill activation */
     /**
      * Default constants to increase the damage multiplier of broadsword once its skill is activated
      */
    private static final float INCREASED_DAMAGE_MULTIPLIER = 0.1f;
     /**
      * Default constants for the hit rate of broadsword once its skill is activated
      */
    private static final int ACTIVATED_HIT_RATE = 90;
     /**
      * Default constants to decrease the stamina of the owner once its skill is activated
      */
    private static final float REDUCED_STAMINA_RATE = 0.2f;
     /**
      * Default constants to track the skill(Focus) duration once its skill is activated
      */
    private static final int FOCUS_DURATION = 5;

    /* Attribute of the Broadsword */
     /**
      * Default constants to indicate the remaining turn of the skill (Focus)
      */
    private int remainingFocusTurn;

     /**
      * selling price of Broadsword
      */
     private static final int soldPrice = 100;

     /**
      * purchasing price of Broadsword
      */
     private static final int purchasedPrice = 250;

     /**
      * damage increased after the upgrading process
      */
     private int increasedDamage;

    /**
     * Constructor of the Broadsword class
     *
     * It has one skill
     * remainingFocusTurn will be set to 5 when it is created
     */
    public Broadsword() {
        super("Broadsword", '1', 110, "slashes", DEFAULT_HIT_RATE);
        this.remainingFocusTurn = FOCUS_DURATION;
        this.addCapability(Ability.SKILL);
        this.increasedDamage = 0;
    }


    /**
     * Reset the attribute 'remainingFocusTurn' to 5
     */
    public void resetFocusTurn(){

        this.remainingFocusTurn = FOCUS_DURATION;

    }

    /**
     * Inform Broadsword on the ground of the passage of time.
     *
     * This method is called once per turn, if Broadsword rests upon the ground.
     *
     * When the broadsword is on the ground and it is still activated.
     * It means that the player just dropped it last turn. It should be inactivated by
     * reverting everything back to default.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.FULLY_ACTIVATED)){
            this.removeCapability(Status.FULLY_ACTIVATED);
            this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
            this.updateHitRate(DEFAULT_HIT_RATE);
            this.resetFocusTurn();
        }
    }


    /**
     * Inform a carried Item(Broadsword) of the passage of time.
     *
     * This method is called once per turn, if Broadsword is being carried.
     *
     * When the broad sword is in player's inventory, the duration of the focus effect should be reduced by 1
     * if it has been activated
     *
     * @param currentLocation The location of the actor carrying Broadsword
     * @param actor The actor carrying Broadsword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (this.hasCapability(Status.JUST_ACTIVATED)){
            this.resetFocusTurn();
            this.addCapability(Status.FULLY_ACTIVATED);
            this.removeCapability(Status.JUST_ACTIVATED);
        } else if(this.hasCapability(Status.FULLY_ACTIVATED)){ // normally reduce the duration by 1
            this.remainingFocusTurn--;
        }
        // If the effect time of the weapon in the inventory has expired, revert it back.
        if (this.remainingFocusTurn == 0){
            this.removeCapability(Status.FULLY_ACTIVATED);
            this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
            this.updateHitRate(DEFAULT_HIT_RATE);
        }
    }


    /**
     * List of allowable actions that the item can perform to the current actor
     *
     * Player can use 'Focus' skill as long as this broad sword is in the inventory.
     *
     * @param owner the actor that owns the Broadsword
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        return new ActionList(new FocusAction(this, INCREASED_DAMAGE_MULTIPLIER, ACTIVATED_HIT_RATE, REDUCED_STAMINA_RATE));
    }

     /**
      * Player can attack other actor with this broadsword.
      *
      * @param otherActor the other actor
      * @param location the location of the other actor
      *
      * @return an unmodifiable list of Actions
      */
     @Override
     public ActionList allowableActions(Actor otherActor, Location location) {
         ActionList actions = new ActionList();
         if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER))
             actions.add( new AttackAction(otherActor, location.toString(), this) );
         if (otherActor.hasCapability(Status.TRADER))
             actions.add(new SellAction(this));
         if (otherActor.hasCapability(Status.UPGRADE_PERSON))
           actions.add(new UpgradeAction(this));
         return actions;
     }

     /**
      * Get the selling price of the Broadsword
      *
      * Broadsword can be sold at 100 runes
      *
      * @return an integer value representing the selling price of Broadsword
      */
     @Override
     public int getSellingPrice() {

         return soldPrice;

     }

     /**
      * Sell Broadsword to the trader
      *
      * @param actor Actor who sells Broadsword at the sale stage
      *
      * @return a sting showing the result of after selling Broadsword
      */
     @Override
     public String soldBy(Actor actor) {
         int price = getSellingPrice();
         actor.removeItemFromInventory( this );
         actor.addBalance( price );
         return actor + " successfully sold " + this + " for " + price + " runes to Traveller.";
     }

     /**
      * Get the purchasing price of the Broadsword
      *
      * Broadsword can be purchased at 250 runes
      *
      * @return an integer value representing the purchasable Broadsword's price
      */
     @Override
     public int getPurchasingPrice() {

         return purchasedPrice;

     }

     /**
      * Purchase Broadsword from the trader
      *
      * @param actor Actor who purchase Broadsword at the sale stage
      *
      * @return a string showing the result of after purchasing Broadsword
      */
     @Override
     public String purchasedBy(Actor actor) {
         int price = getPurchasingPrice();

         if (actor.getBalance() < price) {
             return "Balance is less than what the Traveller asks for, the purchase fails.";
         }

         actor.deductBalance(price);

         if (Math.random() <= 0.05) {
             return "Traveller takes " + price + " runes without giving the " + this + ".";
         }

         actor.addItemToInventory(new Broadsword());

         return actor + " successfully purchased " + this + " for " + price + " runes.";
     }

   /**
    * Get the current damage of the broadsword
    *
    * @return the current damage of the broadsword
    */
   @Override
   public int damage() {
       return super.damage() + this.increasedDamage;
   }

   /**
    * Upgrade Broadsword from the blacksmith
    *
    * @param actor Actor who upgrades the Healing Vial
    *
    * @return a string showing the result of after upgrading Broadsword
    */
   @Override
   public String upgradedBy(Actor actor) {
     int price = 1000;
     String string = "";
     if (actor.getBalance() < price)
       return string + "The Broadsword requires 1000 runes to upgrade.";
     actor.deductBalance(price);
     this.increasedDamage = this.increasedDamage + 10;
     return "Broadsword's effectiveness has been improved!";
   }
 }
