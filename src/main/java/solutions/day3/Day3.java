package solutions.day3;

import reader.Reader;

import java.util.List;

public class Day3 {

    private final char TREE='#';

    private final String FILENAME = "day3.txt";
    private List<String> map;


    public Day3(){
        prepareData();
    }

    private void prepareData(){
        this.map = Reader.readFileLineByLine(FILENAME);
    }

    public long task1 (){
        return countOfTrees(3, 1);
    }

    public long task2 (){
        long slope1 = countOfTrees(1, 1);
        long slope2 = countOfTrees(3, 1);
        long slope3 = countOfTrees(5, 1);
        long slope4 = countOfTrees(7, 1);
        long slope5 = countOfTrees(1, 2);

        return slope1 * slope2 * slope3 * slope4 * slope5;
    }


    private int countOfTrees(int stepRight, int stepDown){
        int counter = 0;
        int positionIndex = stepRight;
        int rowIndex = stepDown;

        while(rowIndex < map.size()){
            String rowValue = map.get(rowIndex);
            int rowLength = rowValue.length();

            if(positionIndex >= rowLength) {
                positionIndex = positionIndex - rowLength;
            }

            if(rowValue.charAt(positionIndex) == TREE ){
                counter++;
            }

            positionIndex += stepRight;
            rowIndex += stepDown;
        }

        return counter;
    }

}
