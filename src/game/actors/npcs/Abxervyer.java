// declare package
package game.actors.npcs;

// import engine and game packages
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
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

    /**
     * Constructor for the Abxervyer class
     *
     */
    public Abxervyer() {

        // displayed "Y", 2000 hp
        super("Abxervyer", 'Y', 2000,new Rune(5000));

        // attack the player with its limbs, dealing 80 damage with 25% accuracy
        this.damage = 80;
        this.hitRate = 25;

        // can follow the player
        this.addBehaviour(1, new FollowBehaviour());

        // boss will not get hurt if they walk around in the Void
        this.addCapability(Status.FOREST_WATCHER);

        // has ability to control weather
        this.addCapability(Ability.SUNNY);
        this.addCapability(Ability.RAINY);

    }

    /**
     * Spawn the instance of an enemy
     *
     * @return a new spawned Enemy
     */
    @Override
    public Enemy spawnMethod() {
        return null;
    }

    /**
     * Drop an item on the ground
     *
     * @param map the map containing the Enemy
     */
    @Override
    public void dropItem(GameMap map) {

    }
}
