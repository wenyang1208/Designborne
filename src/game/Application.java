package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.utils.FancyMessage;
import game.utils.MapFactory;

/**
 * The main class to start the game.
 */
public class Application {


    public static void main(String[] args) {

        World world = new World(new Display());


        displayTitle();


        // create an abandoned village map
        GameMap abandonedVillageMap = MapFactory.getAbandonedVillageMap();


        // create a burial ground map
        GameMap burialGroundMap = MapFactory.getBurialGroundMap();


        // creat a gate to burial ground map, and put it in the abandoned village map
        MapFactory.addGate( abandonedVillageMap.at(30,0), burialGroundMap.at(39, 14), "Burial Ground" );


        // creat a gate to abandoned village map, and put it in the burial ground map
        MapFactory.addGate( burialGroundMap.at(23,0), abandonedVillageMap.at(0, 5), "Abandoned Village" );


        // add these two maps to the world
        world.addGameMap(abandonedVillageMap);
        world.addGameMap(burialGroundMap);


        // player will be in the abandoned village map initially
        Player player = new Player("The Abstracted One", '@', 150, 200);
        world.addPlayer(player, abandonedVillageMap.at(29, 5));


        world.run();
    }


    private static void displayTitle(){
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }


}
