package solutions.day12;

import reader.Reader;
import solutions.day12.navigation.Navigation;
import solutions.day12.navigation.SimpleNavigation;
import solutions.day12.navigation.WaypointNavigation;

import java.util.*;

public class Day12 {

    private final String FILENAME = "day12.txt";
    private List<String> fileLines;


    public Day12(){
        prepareData();
    }

    private void prepareData(){
        this.fileLines = Reader.readFileLineByLine(FILENAME);
    }

    public long task1 (){
        return getSolution(new SimpleNavigation());
    }

    public long task2 (){
        return getSolution(new WaypointNavigation());
    }


    private int getSolution(Navigation navigation){
        Ship ship = new Ship();
        ship.setNavigation(navigation);

        for(String com: fileLines){
            ship.getNavigation().doCommand(com);
        }

        int solution = Math.abs(ship.getNavigation().getEastWestPos()) +
                Math.abs(ship.getNavigation().getNorthSouthPos());

        return solution;
    }

}
