package game.actors.npcs;

/**
 * A Spawnable interface to spawn enemy
 *
 * Created by:
 * @author Yang Dan
 */
public interface Spawnable {

    /**
     * Spawn the instance of an enemy
     *
     * @return a new spawned Enemy
     */
    Enemy spawnMethod();

}
