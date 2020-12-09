package solutions.day9;

import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 {

    private final String FILENAME = "day9.txt";
    private final int PREVIOUS_ELEMENTS = 25;

    private List<Long> file;
    private long invalidNumber;


    public Day9(){
        prepareData();
    }

    private void prepareData(){
        List<String> file = Reader.readFileLineByLine(FILENAME);

        this.file = file.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    public long task1 (){
        return findFirstElement(file);
    }

    public long task2 (){

        List<Long> contiguousSet = getContiguousSet();

        Long max = contiguousSet.stream()
                .max(Long::compareTo)
                .get();

        Long min = contiguousSet.stream()
                .min(Long::compareTo)
                .get();

        return max + min;
    }


    private long findFirstElement (List<Long> list){

        for(int i = PREVIOUS_ELEMENTS; i < list.size(); i++) {

            if(!findValidValue(i)){
                invalidNumber = list.get(i);
                return list.get(i);
            }
        }
        return 0;
    }

    private boolean findValidValue(int index) {

        Set<Long> previousValues = new HashSet<>();

        for(int i = index-PREVIOUS_ELEMENTS; i<index; i++){
            previousValues.add(file.get(i));
        }

        long sum = file.get(index);

        boolean anyMatch = previousValues.stream()
                .anyMatch(e -> {
                    long foundingValue = sum - e;
                    return previousValues.contains(foundingValue)
                            && foundingValue * 2 != sum;

                });

        return anyMatch;
    }


    private List<Long> getContiguousSet(){

        for( int i = 0; i < file.size(); i++ ){

            List<Long> sumList = new ArrayList<>();
            long sum = 0;

            for( int j = i; sum < invalidNumber; j++){

                long val = file.get(j);
                sum +=val;
                sumList.add(val);

                if(sum == invalidNumber){
                    return sumList;
                }
            }
        }
        return null;
    }
}
