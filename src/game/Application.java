package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.npc.*;
import game.grounds.*;
import game.grounds.Void;
import game.items.HealingVial;
import game.utils.FancyMessage;
import game.weapons.Broadsword;

/**
 * The main class to start the game.
 *
 * Created by:
 * @author Adrian Kristanto
 *
 * Modified by:
 * @author Koe Rui En
 * @author Yang Yang Dan
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        List<String> abandonedVillageMap = Arrays.asList(
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

        List<String> ancientWoodsMap = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++..............................++++++++.....~~~.....~~",
                "++...............#######..............++++.........~~.......",
                "++...............#_____#...........................~~~......",
                "+................#_____#............................~~......",
                ".................###_###............~...............~~.....~",
                "...............................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "....................~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~........~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

        List<String> abxervyerBattleRoomMap = Arrays.asList(

                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~");

        // Abandoned Village Game Map
        GameMap abandonedVillage = new GameMap(groundFactory, abandonedVillageMap);
        world.addGameMap(abandonedVillage);

        // Burial Ground Map
        GameMap burialGround = new GameMap(groundFactory, burialGroundMap);
        world.addGameMap(burialGround);

        // Ancient Wood Map
        GameMap ancientWoods = new GameMap(groundFactory, ancientWoodsMap);
        world.addGameMap(ancientWoods);

        //Abxervyer Battle Room Map
        GameMap abxervyerBattleRoom = new GameMap(groundFactory, abxervyerBattleRoomMap);
        world.addGameMap(abxervyerBattleRoom);

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        // Create player
        Player player = new Player("The Abstracted One", '@', 150, 200);

        // Add player to the game map
//        world.addPlayer(player, abandonedVillage.at(24, 5));
        world.addPlayer(player, ancientWoods.at(24,5));

        // try to add balance to player for testing
        player.addBalance(200);

        // try to add Traveller for testing
        ancientWoods.at(24,6).addActor(new Traveller());

        // try to add item to player
        player.addItemToInventory(new Broadsword());
        player.addItemToInventory(new HealingVial());

//        gameMap.at(24,4).addItem(new OldKey());

        // Create enemies in different game maps

        // create Wandering Undead in Abandoned Village Map
        abandonedVillage.at(28,4).addActor(new WanderingUndead());

        // create hollow soldier in Burial Ground Map
        burialGround.at(28,6).addActor(new HollowSoldier());

        // create red wolf and forest keeper in Ancient Wood Map
        ancientWoods.at(28,8).addActor(new ForestKeeper());
        ancientWoods.at(29,9).addActor(new RedWolf());

        // create broadsword weapon to add in the building (ground)
        Broadsword broadsword = new Broadsword();
        abandonedVillage.at(20,5).addItem(broadsword);

        // Set new ground at different game maps

        // set new ground (Graveyard and void in Abandoned Village Map)

        // graveyard (create wandering undead)
        // save attribute of wandering undead
        // spawn 25% chance at each turn
        abandonedVillage.at(30, 11).setGround(new Graveyard(new WanderingUndead(), 0.25));

        // void
        abandonedVillage.at(33,10).setGround(new Void());
        abandonedVillage.at(30, 8).setGround(new Void());

        // set new ground (Graveyard in Burial Ground Map)

        // graveyard (create hollow soldier)
        // save attribute of hollow soldier
        // spawn 10% chance at each turn
        burialGround.at(30, 11).setGround(new Graveyard(new HollowSoldier(), 0.10));

        // set new ground (Hut and Bush in Ancient Woods Map)

        // hut (create forest keeper)
        // save attribute of forest keeper
        // spawn 15% chance at each turn
        ancientWoods.at(30, 11).setGround(new Hut(new ForestKeeper(), 0.15));

        // bush (create red wolf)
        // save attribute of red wolf
        // spawn 30% chance at each turn
        ancientWoods.at(35, 11).setGround(new Bush(new RedWolf(), 0.30));

        // Travel Gate to specific Map

        // Add gate in Abandoned Village Map
        abandonedVillage.at(35,0).setGround(new Gate("Burial Ground", burialGround.at(35,0)));

        // Add gate in new map, Burial Ground
        burialGround.at(35, 0).setGround(new Gate("Abandoned Village", abandonedVillage.at(35,0)));
        burialGround.at(35, 0).setGround(new Gate("Ancient Woods", ancientWoods.at(35,0)));

        // Add gate in new map, Ancient Wood
        ancientWoods.at(35,0).setGround(new Gate("Burial Ground", burialGround.at(35, 0)));

        world.run();
    }
}
