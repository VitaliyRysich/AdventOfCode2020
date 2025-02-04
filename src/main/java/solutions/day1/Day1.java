package solutions.day1;

import reader.Reader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day1 {

    private final String FILENAME = "day1.txt";
    private final int SUM = 2020;
    private List<Integer> entries;

    public Day1(){
        this.entries = convertToListInteger(
                Reader.readFileLineByLine(FILENAME)
        );
    }

    private List<Integer> convertToListInteger(List<String> list){
        List<Integer> integerList = list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return integerList;
    }

    public int task1 (){
        int result = getMultiplyOfTwoEntries(this.entries, this.SUM);
        return result;
    }

    public int task2 (){
        int result = getMultiplyOfThreeEntries(this.entries, this.SUM);
        return result;
    }


    private int getMultiplyOfThreeEntries (List<Integer> entries, int sum){
        int result = 0;
        
        for(int val: entries){
            int remmant = sum - val;
            int multiplyOfRemmant = getMultiplyOfTwoEntries(entries, remmant);
            if(multiplyOfRemmant != 0){
                result = multiplyOfRemmant * val;
                break;
            }
        }

        return result;
    }


    private int getMultiplyOfTwoEntries (List<Integer> entries, int sum) {
        AtomicInteger result = new AtomicInteger();

        entries.stream()
                .forEach(val -> {

                    int val2 = sum - val;
                    if (entries.contains(val2)){
                        result.set(val * val2);
                    }

                });
        return result.get();
    }
}
