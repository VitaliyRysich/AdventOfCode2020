package solutions.day15;

import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day15 {

    private final String FILENAME = "day15.txt";
    private List<Integer> numbersList;

    private final int requiredNumberTask1 = 2020;
    private final int requiredNumberTask2 = 30000000;

    private Map<Integer, Integer> lastIndexOfoccurrence = new HashMap();
    private Map<Integer, Integer> lastByOneIndexOfoccurrence = new HashMap();


    private int lastValue;
    private int lastindex;



    public Day15() {
        prepareData();
    }

    private void prepareData() {
        List<String> file = Reader.readFileLineByLine(FILENAME);

        numbersList = Arrays.asList(file.get(0).split(","))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        for(int i = 0; i < numbersList.size()-1; i++){
            lastIndexOfoccurrence.put(numbersList.get(i), i);
            lastByOneIndexOfoccurrence.put(numbersList.get(i), i);
        }


    }


    public long task1() {

        int lastIndex = numbersList.size()-1;
        int lastNum = numbersList.get(numbersList.size()-1);

        while(lastIndex + 1 < requiredNumberTask1){


            Integer lastByOneIdx = lastIndexOfoccurrence.get(lastNum);

            int nextNumber = -1;
            if(lastByOneIdx != null) {
                nextNumber = lastIndex - lastByOneIdx;
            }
            else {
                nextNumber = 0;
            }

            lastIndexOfoccurrence.put(lastNum, lastIndex);

            lastIndex++;
            lastNum = nextNumber;

        }
        return lastNum;
    }


    public long task2() {
        int lastIndex = numbersList.size()-1;
        int lastNum = numbersList.get(numbersList.size()-1);

        while(lastIndex + 1 < requiredNumberTask2){


            Integer lastByOneIdx = lastByOneIndexOfoccurrence.get(lastNum);

            int nextNumber = -1;
            if(lastByOneIdx != null) {
                nextNumber = lastIndex - lastByOneIdx;
            }
            else {
                nextNumber = 0;
            }

            lastByOneIndexOfoccurrence.put(lastNum, lastIndex);

            lastIndex++;
            lastNum = nextNumber;

        }

        return lastNum;
    }

}
