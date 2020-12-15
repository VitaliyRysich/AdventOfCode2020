package solutions.day14;

import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day14 {

    private final String FILENAME = "day14.txt";
    List<String> file;

    private int bites = 36;
    private Map<Long, Long> memory = new HashMap<>();


    public Day14(){
        prepareData();
    }

    private void prepareData(){
        file = Reader.readFileLineByLine(FILENAME);
    }


    public long task1 (){

        String mask = "";
        for(String line: file){

            String[] s = line.replace(" ", "").split("=");
            if(s[0].contains("mask")){
                mask = s[1];
            }
            else {
                long mem = getMemoryValueFromStr(s[0]);

                long givenValue = Long.parseLong(s[1]);
                long changedValue = binaryToDecimal(putMaskOnBinary(decimalToBinary(givenValue), mask));

                memory.put(mem, changedValue);
            }
        }
        return getMemorySum();
    }


    public long task2 () {
        memory.clear();

        String mask = "";
        for(String line: file){

            String[] s = line.replace(" ", "").split("=");
            if(s[0].contains("mask")){
                mask = s[1];
            }
            else {
                long mem = getMemoryValueFromStr(s[0]);

                long givenValue = Long.parseLong(s[1]);
                String memoryWithMask = putMaskOnMemory(decimalToBinary(mem), mask);

                addAllMemoryCombinations(memoryWithMask, givenValue);
            }
        }
        return getMemorySum();
    }


    private long getMemorySum(){
        return memory.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }


    private long getMemoryValueFromStr(String str){
        return Long.parseLong(str
                .replace("mem", "")
                .replace("[", "")
                .replace("]", ""));
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
        return Long.parseLong(val,2);

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

    private String putMaskOnMemory(String val, String mask){
        char[] valChar = val.toCharArray();
        char[] maskChar = mask.toCharArray();

        for(int i = 0; i < bites; i++){
            if(maskChar[i] != '0'){
                valChar[i] = maskChar[i];
            }
        }

        return String.valueOf(valChar);
    }

    private void addAllMemoryCombinations(String mem, Long val){
        if(mem.contains("X")){
            int idx = mem.indexOf("X");
            String combination1 = mem.substring(0,idx)+'0'+mem.substring(idx+1);
            String combination2 = mem.substring(0,idx)+'1'+mem.substring(idx+1);

            addAllMemoryCombinations(combination1, val);
            addAllMemoryCombinations(combination2, val);
        }
        else {
            memory.put(binaryToDecimal(mem), val);
        }
    }
}
