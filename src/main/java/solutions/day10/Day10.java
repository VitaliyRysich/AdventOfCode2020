package solutions.day10;

import reader.Reader;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day10 {

    private final String FILENAME = "day10.txt";
    private List<Integer> file;

    private Map<Integer, Long> combinationsHash = new HashMap<>();


    public Day10(){
        prepareData();
    }

    private void prepareData(){
        List<String> file = Reader.readFileLineByLine(FILENAME);

        this.file = file.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }


    public long task1 (){
        return getMultiplyOfOneAndThreeDifferences();
    }

    public long task2 (){

        List<Integer> input = new ArrayList<>(file);
        input.add(0);
        input.sort(Integer::compareTo);

        return getAllCombiantions(input);
    }




    private int getMultiplyOfOneAndThreeDifferences (){
        List<Integer> listOfAdapters = new ArrayList<>(file);
        listOfAdapters.add(0);
        listOfAdapters.sort(Integer::compareTo);

        AtomicInteger count1 = new AtomicInteger();
        AtomicInteger count3 = new AtomicInteger();

        count3.set(1);

        listOfAdapters.forEach(
                        a -> {
                            int nextIndex = listOfAdapters.indexOf(a) + 1;

                            if(nextIndex < listOfAdapters.size()){

                                int nextVal = listOfAdapters.get(nextIndex);

                                if(nextVal - a == 1){
                                    count1.set(count1.get() + 1);
                                }
                                else if(nextVal - a == 3){
                                    count3.set(count3.get() + 1);
                                }
                            }
                        }
                );
        return count1.get() * count3.get();
    }



    private long getAllCombiantions (List<Integer> listOfAdapters){

        long count = 0;

        int countOfNextCombination = countOfFirstAvailableCombination(listOfAdapters);

        switch (countOfNextCombination){
            case 0: {
                count += 1;
                break;
            }
            case 1:{
                count += getCombinationsOfSublist(listOfAdapters, 1);
                break;
            }
            case 2: {
                count += getCombinationsOfSublist(listOfAdapters, 1);
                count += getCombinationsOfSublist(listOfAdapters, 2);
                break;
            }
            case 3: {
                count += getCombinationsOfSublist(listOfAdapters, 1);
                count += getCombinationsOfSublist(listOfAdapters, 2);
                count += getCombinationsOfSublist(listOfAdapters, 3);
                break;
            }
        }


        return count;

    }

    private long getCombinationsOfSublist(List<Integer> listOfAdapters, int indefFrom) {
        List<Integer> sublist = listOfAdapters.subList(indefFrom, listOfAdapters.size());
        int hash = sublist.hashCode();
        long combinations = combinationsHash.getOrDefault(hash, -1l);

        if (combinations < 0) {
            combinations = getAllCombiantions(sublist);
            combinationsHash.putIfAbsent(hash, combinations);
        }

        return combinations;
    }


    private int countOfFirstAvailableCombination(List<Integer> list){

        int count = 0;
        int val = list.get(0);

        for(int i = 1; i<=3; i++){
            if(list.size() > i && list.get(i) - val <= 3) {
                count++;
            }
        }
        return count;
    }

}
