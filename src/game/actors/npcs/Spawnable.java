package game.actors.npcs;

/**
 * @author yangdan
 */
public interface Spawnable {

    /**
     * Spawn the instance of an enemy
     *
     * @return a new spawned Enemy
     */
    Enemy spawnMethod();

}
