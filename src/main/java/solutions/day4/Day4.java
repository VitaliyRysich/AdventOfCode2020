package solutions.day4;

import reader.Reader;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 {

    private final String FILENAME = "day4.txt";
    private String file;
    List<String> requireAttributes =
            Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");


    public Day4(){
        prepareData();
    }

    private void prepareData(){
        this.file = Reader.readFile(FILENAME);
    }

    public long task1 (){

        List<List<String>> passportAttributes = getPassportAttributes();

        long count = passportAttributes.stream()
                .filter(a -> isValidPassport(a))
                .count();

        return count;
    }

    public long task2 (){
        List<List<String>> passportAttributes = getPassportAttributes();

        long count = passportAttributes.stream()
                .filter(a -> isValidPassport(a))
                .filter(a -> isValidPassportAttributes(a))
                .count();

        return count;
    }

    private List<List<String>> getPassportAttributes(){
        List<String> passports = Arrays.asList(file.split("(?m)^\\s*$"));

        List<List<String>> collect = passports.stream()
                .map(p -> p.replace("\n", " "))
                .map(p -> p.replace("\r", " "))
                .map(p -> Arrays.asList(p.split(" ")))
                .collect(Collectors.toList());

        return collect;
    }

    private boolean isValidPassport(List<String> passportAttributes){

        boolean isValid = passportAttributes.stream()
                .map(p -> p.split(":")[0])
                .collect(Collectors.toList())
                .containsAll(requireAttributes);

        return isValid;
    }

    private boolean isValidPassportAttributes(List<String> passportAttributes){

        List<String[]> attributesPar = passportAttributes.stream()
                .map(a -> a.split(":"))
                .collect(Collectors.toList());

        for(String[] att: attributesPar) {
            if (!att[0].isEmpty()) {
                String key = att[0];
                String val = att[1];

                switch (key) {
                    case "byr":
                        if (!byrValidation(val)) return false;
                        break;
                    case "iyr":
                        if (!iyrValidation(val)) return false;
                        break;
                    case "eyr":
                        if (!eyrValidation(val)) return false;
                        break;
                    case "hgt":
                        if (!hgtValidation(val)) return false;
                        break;
                    case "hcl":
                        if (!hclValidation(val)) return false;
                        break;
                    case "ecl":
                        if (!eclValidation(val)) return false;
                        break;
                    case "pid":
                        if (!pidValidation(val)) return false;
                        break;
                }
            }
        }
        return true;
    }


    private boolean byrValidation(String val){
        int byr = Integer.valueOf(val);
        return byr >= 1920 && byr <= 2002;
    }

    private boolean iyrValidation(String val){
        int iyr = Integer.valueOf(val);
        return iyr >= 2010 && iyr <= 2020;
    }

    private boolean eyrValidation(String val){
        int eyr = Integer.valueOf(val);
        return eyr >= 2020 && eyr <= 2030;
    }

    private boolean hgtValidation(String val){
        try {
            String measure = val.substring(val.length() - 2);
            String value = val.substring(0, val.length() - 2);
            int hgt = Integer.valueOf(value);

            if (measure.equals("cm")) {
                return hgt >= 150 && hgt <= 193;
            }
            else if(measure.equals("in")){
                return hgt >= 59 && hgt <= 76;
            }

            return false;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private boolean hclValidation(String val){
        String regex = "#[0-9|a-f]{6}";
        return val.matches(regex);
    }

    private boolean eclValidation(String val){
        List<String> eyeColors = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
        return eyeColors.contains(val);
    }

    private boolean pidValidation(String val){
        String regex = "[0-9]{9}";
        return val.matches(regex);
    }

}
