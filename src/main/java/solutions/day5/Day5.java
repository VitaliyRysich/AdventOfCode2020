package solutions.day5;

import reader.Reader;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day5 {

    private final String FILENAME = "day5.txt";
    private final int ROWS = 128;
    private final int COLUMNS = 8;
    private List<String> seats;


    public Day5(){
        prepareData();
    }

    private void prepareData(){
        this.seats = Reader.readFileLineByLine(FILENAME);
    }

    public long task1 (){
        Optional<Integer> max = seats.stream()
                .map(this::getSeatID)
                .max(Integer::compareTo);

        return max.isPresent() ? max.get() : 0;
    }

    public long task2 (){
        List<Integer> listOfSeats = seats.stream()
                .map(this::getSeatID)
                .sorted(Integer::compareTo)
                .collect(Collectors.toList());

        for(Integer seatId : listOfSeats){
            if(!listOfSeats.contains(seatId+1)){
                return seatId+1;
            }
        }
        return 0;
    }

    private int getSeatID (String boardingPass){

        String rowCode = boardingPass.substring(0,7);
        String columnCode = boardingPass.substring(7);

        int rowNumber = getNumber(rowCode, 0, ROWS, 'F', 'B');
        int columnNumber = getNumber(columnCode, 0, COLUMNS, 'L', 'R');

        int seatID = rowNumber * 8 + columnNumber;

        return seatID;
    }


    private int getNumber(String rowChars, int lowerRange, int upperRange, char lowerChar, char upperChar){

        if(rowChars.length() != 0) {
            char firstChar = rowChars.charAt(0);
            String restStr = rowChars.substring(1);

            if (firstChar == lowerChar) {
                int newUpperRange = lowerRange + (upperRange-lowerRange)/2;
                return getNumber(restStr, lowerRange, newUpperRange, lowerChar, upperChar);
            }
            else if(firstChar == upperChar){
                int newLowerRange = lowerRange + (upperRange-lowerRange)/2;
                return getNumber(restStr, newLowerRange , upperRange, lowerChar, upperChar);
            }
        }
        return upperRange-1;
    }
}
