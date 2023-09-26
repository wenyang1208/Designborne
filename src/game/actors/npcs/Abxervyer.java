// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.grounds.Gate;
import game.items.Rune;
import game.utils.Ability;
import game.utils.Status;

public class Abxervyer extends Enemy{

    // forest keeper has its own damage and hit rate

    // damage
    /**
     * damage to health
     */
    private final int damage;

    // hit rate/accuracy
    /**
     * hit rate to other actor
     */
    private final int hitRate;


    private Gate droppedGate;

    /**
     * Constructor for the Abxervyer class
     *
     */
    public Abxervyer() {

        // displayed "Y", 2000 hp
        super("Abxervyer", 'Y', 2000, new Rune(5000));

        // attack the player with its limbs, dealing 80 damage with 25% accuracy
        this.damage = 80;
        this.hitRate = 25;

        // can follow the player
        this.addBehaviour(1, new FollowBehaviour());

        // boss will not get hurt if they walk around in the Void
        this.addCapability(Ability.STEP_ON_VOID);

        // has ability to control weather
        this.addCapability(Ability.SUNNY);
        this.addCapability(Ability.RAINY);

    }


    public void setDroppedGate(Gate droppedGate) {
        this.droppedGate = droppedGate;
    }


    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {
        this.getRunes().getDropAction(this).execute(this,map);
        map.locationOf(this).setGround( this.droppedGate);
    }
}
