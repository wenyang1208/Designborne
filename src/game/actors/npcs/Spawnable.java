package game.actors.npcs;

import edu.monash.fit2099.engine.positions.Location;

import java.util.Locale;

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
     * @param location spawner location to spawn enemies
     *
     * @return a new spawned Enemy
     */
    Enemy spawnMethod(Location location);

}
