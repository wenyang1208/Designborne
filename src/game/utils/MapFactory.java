package game.utils;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HollowSoldier;
import game.actors.WanderingUndead;
import game.grounds.*;
import game.grounds.Void;
import game.weapons.Broadsword;

import java.util.Arrays;
import java.util.List;

/**
 * @author yangdan
 */
public class MapFactory {


    private static final FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
            new Wall(), new Floor(), new Puddle(), new Void());


    public MapFactory(){ }


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


        // add two grave yards that can spawn wandering undead to the abandoned village map
        Graveyard graveyard = new Graveyard( new WanderingUndead(), 0.25f );

        abandonedVillageMap.at(50, 1).setGround( graveyard );
        abandonedVillageMap.at(35, 10).setGround( graveyard );

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
                "....+~~~~..++++++++~~~..~~~~~..~~~~~...."
        );
        GameMap burialGroundMap = new GameMap(groundFactory, map);


        // add two grave yards that can spawn hollow soldier to the burial ground map
        Graveyard graveyard = new Graveyard( new HollowSoldier(), 0.10f );

        burialGroundMap.at(2, 14).setGround( graveyard );
        burialGroundMap.at(38, 11).setGround( graveyard );

        return burialGroundMap;
    }


}
