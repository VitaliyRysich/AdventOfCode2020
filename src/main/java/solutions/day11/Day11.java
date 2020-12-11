package solutions.day11;

import javafx.util.Pair;
import reader.Reader;

import java.util.*;

public class Day11 {

    private final String FILENAME = "day11.txt";
    private char [][] seats;
    private int task = 1;

    private Map<Integer, Long> combinationsHash = new HashMap<>();


    public Day11(){
        prepareData();
    }

    private void prepareData(){
        List<String> file = Reader.readFileLineByLine(FILENAME);

        int row = file.size();
        int column = file.get(0).length();

        seats = new char[row][column];

        for (int i = 0; i < row; i ++){
            String rowValue = file.get(i);
            seats[i] = rowValue.toCharArray();
        }
    }


    public long task1 (){
        int occupiedSeatsAfterOccupying = -1;
        int occupiedSeatsAfterEmptying = -2;

        while (occupiedSeatsAfterEmptying != occupiedSeatsAfterOccupying){
            becomeOccupied();
            occupiedSeatsAfterOccupying = countOfOccupiedSeats();

            becomeEmpty();
            occupiedSeatsAfterEmptying = countOfOccupiedSeats();
        }
        return occupiedSeatsAfterEmptying;
    }

    public long task2 (){
        task = 2;
        prepareData();

        int occupiedSeatsAfterOccupying = -1;
        int occupiedSeatsAfterEmptying = -2;

        while (occupiedSeatsAfterEmptying != occupiedSeatsAfterOccupying) {
            becomeOccupied();
            occupiedSeatsAfterOccupying = countOfOccupiedSeats();

            becomeEmpty();
            occupiedSeatsAfterEmptying = countOfOccupiedSeats();
        }


        return occupiedSeatsAfterEmptying;
    }


    public void printGrid(char[][] seats)
    {
        for(int i = 0; i < seats.length; i++)
        {
            for(int j = 0; j < seats[0].length; j++)
            {
                System.out.print(seats[i][j]);
            }
            System.out.println();
        }
    }


    private boolean ifSeatCanBeChangedToEmpty(int roxIndex, int columnIndex){

        if(seats[roxIndex][columnIndex] != '#') return false;

        int countAdjacentOccupiedSeats = (task == 1)
                ? countOfAdjacentSeats(roxIndex, columnIndex)
                :countOfDirectionVisibleSeats(roxIndex, columnIndex);

        if(countAdjacentOccupiedSeats >= (task == 1 ? 4 : 5)) return true;

        return false;
    }


    private boolean ifSeatCanBeChangedToOccupied(int roxIndex, int columnIndex){

        if(seats[roxIndex][columnIndex] != 'L') return false;

        int countAdjacentOccupiedSeats = (task == 1)
                ? countOfAdjacentSeats(roxIndex, columnIndex)
                :countOfDirectionVisibleSeats(roxIndex, columnIndex);

        if(countAdjacentOccupiedSeats == 0) return true;

        return false;
    }


    int countOfAdjacentSeats(int roxIndex, int columnIndex){
        List<Pair> listOfAdjacents = Arrays.asList(
                new Pair(roxIndex-1, columnIndex-1),
                new Pair(roxIndex-1, columnIndex),
                new Pair(roxIndex-1, columnIndex+1),
                new Pair(roxIndex, columnIndex-1),
                new Pair(roxIndex, columnIndex+1),
                new Pair(roxIndex+1, columnIndex-1),
                new Pair(roxIndex+1, columnIndex),
                new Pair(roxIndex+1, columnIndex+1)
        );
        int count = 0;

        for (Pair pair: listOfAdjacents){
            int rowIdx = (int)pair.getKey();
            int colIdx = (int)pair.getValue();

            if(rowIdx >= 0 && rowIdx < seats.length &&
            colIdx >= 0 && colIdx < seats[0].length){

                if(seats[rowIdx][colIdx] == '#'){
                    count++;
                }
            }
        }

        return count;
    }


    int countOfDirectionVisibleSeats(int rowIndex, int columnIndex){
        List<Pair> listOfAdjacents = Arrays.asList(
                new Pair(-1, -1),
                new Pair(-1, 0),
                new Pair(-1, 1),
                new Pair(0, -1),
                new Pair(0, 1),
                new Pair(1, -1),
                new Pair(1, 0),
                new Pair(1, 1)
        );
        int count = 0;

        for (Pair pair: listOfAdjacents){
            int rowIdxAdd = (int)pair.getKey();
            int colIdxAdd = (int)pair.getValue();

            int i=0;
            int j=0;

            for(i = rowIndex+rowIdxAdd, j = columnIndex+colIdxAdd;
                i >= 0 && i < seats.length && j >= 0 && j < seats[0].length;
                i+=rowIdxAdd, j+=colIdxAdd)
            {
                if(seats[i][j] == '#') {
                    count++;
                    break;
                }
                else if(seats[i][j] == 'L'){
                    break;
                }

            }
        }
        return count;
    }


    private int countOfOccupiedSeats(){
        int count = 0;

        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[0].length; j++) {

                if(seats[i][j] == '#'){
                    count++;
                }
            }
        }
        return count;
    }


    private void becomeOccupied(){
        List<Pair> seatToChange = new ArrayList<>();

        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[0].length; j++) {

                if(ifSeatCanBeChangedToOccupied(i,j)){
                    seatToChange.add(new Pair(i,j));
                }
            }
        }

        seatToChange.forEach(p -> {
            seats[(int)p.getKey()][(int)p.getValue()] = '#';
        });
    }

    private void becomeEmpty(){
        List<Pair> seatToChange = new ArrayList<>();

        for(int i = 0; i < seats.length; i++) {
            for(int j = 0; j < seats[0].length; j++) {

                if(ifSeatCanBeChangedToEmpty(i,j)){
                    seatToChange.add(new Pair(i,j));
                }
            }
        }

        seatToChange.forEach(p -> {
            seats[(int)p.getKey()][(int)p.getValue()] = 'L';
        });
    }

}
