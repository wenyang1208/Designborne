package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.*;
import game.grounds.Void;
import game.items.OldKey;
import game.npc.HollowSoldier;
import game.npc.WanderingUndead;
import game.utils.FancyMessage;
import game.weapons.Broadsword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Koe Rui En
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__.....................................................",
                "...#..___#.................................................",
                "...###.###................#######..........................",
                "..........................#_____#..........................",
                "........~~................#_____#..........................",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~................................................",
                "....~~~~~.................................###..##..........",
                "~~~~~~~...................................#___..#..........",
                "~~~~~~....................................#..___#..........",
                "~~~~~~~~~.................................#######..........");

        List<String> burialGroundMap = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        // Abandoned Village Game Map
        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        // Create new game map(burial ground)
        GameMap burialGround = new GameMap(groundFactory, burialGroundMap);
        world.addGameMap(burialGround);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Create enemy - wandering undead
        // 28,4
        gameMap.at(28,4).addActor(new WanderingUndead());

        // create player
        Player player = new Player("The Abstracted One", '@', 150, 200);

        world.addPlayer(player, gameMap.at(24, 5));

//        gameMap.at(24,4).addItem(new OldKey());

        // create broadsword weapon to add in the building (ground)
        Broadsword broadsword = new Broadsword();
        gameMap.at(20,5).addItem(broadsword);

        // Set new ground (Graveyard and void in Abandoned Village Map)

        // graveyard (create wandering undead)
        // save attribute of wandering undead
        gameMap.at(30, 11).setGround(new Graveyard(new WanderingUndead(), 0.25));

        // void
        // 3310
        gameMap.at(33,10).setGround(new Void());
        gameMap.at(30, 8).setGround(new Void());

        // Travel Gate

        // Add gate in Abandoned Village Map
        gameMap.at(35,0).setGround(new Gate("Burial Ground", burialGround.at(35,0)));

        // Add gate in new map, Burial Ground
        burialGround.at(35, 0).setGround(new Gate("Abandoned Village", gameMap.at(35,0)));

        // Create hollow soldier
        burialGround.at(28,6).addActor(new HollowSoldier());

        // Set new ground (Graveyard and void in Abandoned Village Map)

        // graveyard (create hollow soldier)
        // save attribute of hollow soldier
         burialGround.at(30, 11).setGround(new Graveyard(new HollowSoldier(), 0.10));

        world.run();
    }
}
