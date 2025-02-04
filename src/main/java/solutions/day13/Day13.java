package solutions.day13;

import javafx.util.Pair;
import jdk.nashorn.internal.ir.LiteralNode;
import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day13 {

    private final String FILENAME = "day13.txt";
    private int time;
    private List<String> busses;



    public Day13(){
        prepareData();
    }

    private void prepareData(){
        List<String> file = Reader.readFileLineByLine(FILENAME);

        time = Integer.valueOf(file.get(0));

        busses = Arrays.asList(file.get(1).split(","));
    }


    public long task1 (){

        Map<Integer, Integer> busTimestamps  = new TreeMap<>();

        List<Integer> bussesId = busses.stream()
                .filter(e -> !e.equals("x"))
                .map(e -> Integer.valueOf(e))
                .collect(Collectors.toList());

        bussesId.stream()
                .forEach(b -> {
                    for(int i = time;  i < time+b; i++){
                        if(i%b == 0) busTimestamps.put(b, i);
                    }
                });

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(busTimestamps.entrySet());
        list.sort(Map.Entry.comparingByValue());

        int waitingMinutes = list.get(0).getValue() - time;
        int busId = list.get(0).getKey();

        return busId * waitingMinutes;
    }


    public long task2 () {

        Map<Integer, Integer> bussesMap = new HashMap<>();
        for(int i = 0; i < busses.size(); i++){
            if(!busses.get(i).equals("x"))
                bussesMap.put(Integer.valueOf(busses.get(i)), i);
        }

        long step = 1;
        long timestamp = 1;

        for(Map.Entry<Integer, Integer> bus: bussesMap.entrySet()) {

            while ((timestamp + bus.getValue()) % bus.getKey() != 0){
                timestamp +=step;
            }
            step *= bus.getKey();
        }

        return timestamp;
    }
}
