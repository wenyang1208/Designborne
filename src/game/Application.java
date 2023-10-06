package game;

import game.items.Bloodberry;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.npcs.*;
import game.grounds.*;
import game.grounds.Void;
import game.items.OldKey;
import game.utils.FancyMessage;
import game.weapons.Broadsword;
import game.weapons.GiantHammer;

/**
 * The main class to start the game.
 *
 * Created by:
 * @author Adrian Kristanto
 *
 * Modified by:
 * @author Koe Rui En
 * @author Yang Dan
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());



        List<String> abandonedVillageMap = Arrays.asList(
                "...........................................................",
                "...#######.................................................",
                "...#__......................................++++...........",
                "...#..___#..................................+++++++........",
                "...###.###................#######.............+++..........",
                "..........................#_____#...............+++........",
                "........~~................#_____#................+.........",
                ".........~~~..............###_###...............++.........",
                "...~~~~~~~~....+++.........................................",
                "....~~~~~........+++++++..................###..##...++++...",
                "~~~~~~~..............+++..................#___..#...++.....",
                "~~~~~~.................++.................#..___#....+++...",
                "~~~~~~~~~.................................#######.......++.");

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


        // Create GameMap
        GameMap abandonedVillage = new GameMap(groundFactory, abandonedVillageMap);
        GameMap burialGround = new GameMap(groundFactory, burialGroundMap);
        GameMap ancientWoods = new GameMap(groundFactory, ancientWoodsMap);
        GameMap abxervyerBattleRoom = new GameMap(groundFactory, abxervyerBattleRoomMap);

        // Add maps to the world
        world.addGameMap(abandonedVillage);
        world.addGameMap(burialGround);
        world.addGameMap(ancientWoods);
        world.addGameMap(abxervyerBattleRoom);

        // Configure abandonedVillage
        Graveyard graveyard1 = new Graveyard( new WanderingUndead(), 0.25f );
        abandonedVillage.at(50, 1).setGround(graveyard1);
        abandonedVillage.at(35, 10).setGround( graveyard1);
        abandonedVillage.at(27, 6).addItem(new Broadsword());
        // create wandering undead
        abandonedVillage.at(28,4).addActor(new WanderingUndead());

        // Configure burialGround
        Graveyard graveyard2 = new Graveyard(new HollowSoldier(), 0.10f );
        burialGround.at(2, 14).setGround(graveyard2);
        burialGround.at(38, 11).setGround(graveyard2);
        // Create hollow soldier
        burialGround.at(28,6).addActor(new HollowSoldier());

        // Configure ancientWoods
        Hut hut = new Hut(new ForestKeeper(), 0.15f );
        Bush bush = new Bush(new RedWolf(), 0.30f );
        ancientWoods.at(43, 7).setGround(hut);
        ancientWoods.at(43, 9).setGround(bush);
        ancientWoods.at(57, 10).addItem(new Bloodberry());
        ancientWoods.at(55, 2).addItem(new Bloodberry());
        ancientWoods.at(8, 11).addItem(new Bloodberry());
        ancientWoods.at(19, 3).addActor(new Traveller());
        //create red wolf and forest keeper
        ancientWoods.at(40,10).addActor(new RedWolf());
        ancientWoods.at(35,10).addActor(new ForestKeeper());
        // create bloodberry
        ancientWoods.at(21, 4).addItem(new Bloodberry());


        // Configure abxervyerBattleRoom
        abxervyerBattleRoom.at(32, 0).setGround(hut);
        abxervyerBattleRoom.at(34, 0).setGround(hut);
        abxervyerBattleRoom.at(2, 11).setGround(hut);
        abxervyerBattleRoom.at(2, 19).setGround(hut);
        abxervyerBattleRoom.at(25, 19).setGround(bush);
        abxervyerBattleRoom.at(22, 16).setGround(bush);
        abxervyerBattleRoom.at(16, 0).setGround(bush);
        abxervyerBattleRoom.at(39, 12).addItem(new GiantHammer());
        Abxervyer abxervyer = new Abxervyer();
        // set gate for boss so it can save it
        abxervyer.setDroppedGate(new Gate("Ancient Woods", ancientWoods.at(0, 5)));
        // create abxervyer, the forest keeper
        abxervyerBattleRoom.at(12, 9).addActor( abxervyer );

        // Create gate to travel to another map

        // create a gate to burial ground map, and put it in the abandoned village map
        abandonedVillage.at(30,0).setGround(new Gate("Burial Ground", burialGround.at(23, 0)) );

        // create a gate to abandoned village map, and put it in the burial ground map
        burialGround.at(23,0).setGround(new Gate("Abandoned Village", abandonedVillage.at(30, 0)) );

        // create a gate to ancient woods map, and put it in the burial ground map
        burialGround.at(0, 10).setGround(new Gate("Ancient Woods", ancientWoods.at(30, 0)));

        // create a gate to burial ground map, and put it in the ancient woods map
        ancientWoods.at(30, 0).setGround(new Gate("Burial Ground", burialGround.at(0, 10)));

        // create a gate to abxervyer battle room map, and put it in the abxervyer battle map
        ancientWoods.at(0, 6).setGround(new Gate("Abxervyer Battle Room", abxervyerBattleRoom.at(39, 13)) );

        // Set Player
        // 150 hit points (the health attribute) and 200 stamina
        Player player = new Player("The Abstracted One", '@', 15000, 20000);

        // Add player to the map
//        world.addPlayer(player, abandonedVillage.at(29, 5));
//        world.addPlayer(player, burialGround.at(0,10));
        world.addPlayer(player, ancientWoods.at(30, 3));
//        world.addPlayer(player, abxervyerBattleRoom.at(14, 10));

        // test

        // inventory
        player.addItemToInventory(new OldKey());
//        player.addBalance(10000);
//        player.addItemToInventory(new Bloodberry());
//        player.addItemToInventory(new GiantHammer());

        // travel
//        world.addPlayer(player, ancientWoods.at(0, 6));

        // attack enemy
//        abxervyerBattleRoom.at(14, 11).addActor(new ForestKeeper());
//        abxervyerBattleRoom.at(15, 12).addActor(new RedWolf());
//        ancientWoods.at(21,4).addActor(new RedWolf());


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        world.run();

    }

}
