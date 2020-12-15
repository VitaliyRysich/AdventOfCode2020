package solutions.day14;

import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    private final String FILENAME = "day14.txt";
    List<String> file;

    private int bites = 36;



    public Day14(){
        prepareData();
    }

    private void prepareData(){
        file = Reader.readFileLineByLine(FILENAME);
    }


    public long task1 (){

        Map<Integer, Long> memory = new HashMap<>();

        String mask = "";
        for(String line: file){

            String[] s = line.replace(" ", "").split("=");
            if(s[0].contains("mask")){
                mask = s[1];
            }
            else {
                int mem = Integer.parseInt(s[0]
                        .replace("mem", "")
                        .replace("[", "")
                        .replace("]", ""));

                long givenValue = Long.parseLong(s[1]);
                long changedValue = binaryToDecimal(putMaskOnBinary(decimalToBinary(givenValue), mask));

                memory.put(mem, changedValue);
            }
        }

        long sum = memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();

        return sum;
    }


    public long task2 () {

        return 0;
    }


    private String decimalToBinary(long val){
        StringBuilder s = new StringBuilder();
        s.append(Long.toBinaryString(val));
        for(int i=s.length(); i < bites; i++){
            s.insert(0, "0");
        }
        return s.toString();
    }

    private long binaryToDecimal(String val){
        long decimal=Long.parseLong(val,2);
        return decimal;
    }

    private String putMaskOnBinary(String val, String mask){
        char[] valChar = val.toCharArray();
        char[] maskChar = mask.toCharArray();

        for(int i = 0; i < bites; i++){
            if(maskChar[i] != 'X'){
                valChar[i] = maskChar[i];
            }
        }

        return String.valueOf(valChar);
    }


    public static void main(String[] args) {
        Day14 d = new Day14();
        System.out.println("110X1001110110X10100010X0000X010X11X".length());
        System.out.println(d.decimalToBinary(101));
        System.out.println(d.binaryToDecimal(d.putMaskOnBinary(d.decimalToBinary(101), "110X1001110110X10100010X0000X010X11X")));
    }
}
