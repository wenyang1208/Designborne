package game.utils;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.npc.HollowSoldier;
import game.actors.npc.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.weapons.Broadsword;

import java.util.Arrays;
import java.util.List;

/**
 * A MapFactory class that handles creating GameMap
 *
 * Created by:
 * @author Yang Yang Dan
 *
 * Modified by:
 * @author Koe Rui En
 *
 */
public class MapFactory {


    private static final FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void());


    public MapFactory(){}


    public static void addGate(Location oriLocation, Location destLocation, String destMapName){
        Gate gate = new Gate(destMapName, destLocation);
        oriLocation.setGround( gate );
    }


    public static GameMap getAbandonedVillageMap(){
        List<String> map = Arrays.asList(
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

        GameMap abandonedVillageMap = new GameMap(groundFactory, map);


        // create graveyard that can spawn wandering undead to the abandoned village map
        // spawns 15% chance at each turn
        Graveyard graveyard = new Graveyard( new WanderingUndead(), 0.25f);

        // set location of graveyards
        abandonedVillageMap.at(50, 1).setGround( graveyard);
        abandonedVillageMap.at(35, 10).setGround( graveyard);

        // add the starting weapon to the map
        abandonedVillageMap.at(27, 6).addItem(new Broadsword());

        return abandonedVillageMap;
    }


    public static GameMap getBurialGroundMap(){

        List<String> map = Arrays.asList(
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

        GameMap burialGroundMap = new GameMap(groundFactory, map);

        // create graveyard that can spawn hollow soldier to the burial ground map
        // spawns 10% chance at each turn
        Graveyard graveyard = new Graveyard(new HollowSoldier(), 0.10f );

        // set location of graveyards
        burialGroundMap.at(2, 14).setGround(graveyard);
        burialGroundMap.at(38, 11).setGround(graveyard);

        return burialGroundMap;

    }

    public static GameMap getAncientWoodMap(){

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

        GameMap ancientWoods = new GameMap(groundFactory, ancientWoodsMap);

        // create hut and bush that spawn Forest Keeper and Red Wolf respectively

        // create graveyard that can spawn hollow soldier to the burial ground map

        // hut spawns 15% chance at each turn
//        Hut hut = new Hut(new ForestKeeper(), 0.15);

        // bush spawns 30% chance at each turn
//        Bush bush = new Hut(new RedWolf(), 0.30);

        // set location of hut and bush
//        ancientWoods.at(2, 14).setGround(hut);
//        ancientWoods.at(38, 11).setGround(hut);

        // ancientWoods.at(3, 14).setGround(bush);
//        ancientWoods.at(37, 11).setGround(bush);

        return ancientWoods;

    }

    public static GameMap getAbxervyerRoomMap() {

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

        GameMap abxervyerBattleRoom = new GameMap(groundFactory, abxervyerBattleRoomMap);


        return abxervyerBattleRoom;
    }


}
