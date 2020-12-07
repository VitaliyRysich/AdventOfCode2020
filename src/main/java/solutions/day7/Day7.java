package solutions.day7;

import reader.Reader;

import java.util.*;

public class Day7 {

    private final String FILENAME = "day7.txt";
    private final String wantedBag = "shiny gold";
    private List<String> fileLines;


    public Day7(){
        prepareData();
    }

    private void prepareData(){
        this.fileLines = Reader.readFileLineByLine(FILENAME);
    }

    public long task1 (){
        Set<String> bagsWhichContainWantedBag = getAllBagsWhichContainWanted(wantedBag);
        long count = countOfBBagsWhichContainAnother(bagsWhichContainWantedBag);
        return count;
    }

    public long task2 (){

        long sum = getAllBagsCount(wantedBag);

        return sum;
    }

    private Set<String> getAllBagsWhichContainWanted(String bag){
        Set<String> bags = new HashSet<>();

        for(String rule: fileLines){

            String newBag = getBagFromRuleIfItContains(rule, bag);

            if (newBag != null){
                bags.addAll(getAllBagsWhichContainWanted(newBag));
                bags.add(newBag);
            }
        }

        bags.add(bag);
        return bags;
    }

    private String getBagFromRuleIfItContains(String rule, String bag){
        int index = rule.indexOf(" bags contain ");
        String rule2 = rule.substring(index);
        if(rule2.contains(bag)){
           return rule.substring(0,index);
        }

        return null;
    }


    private long countOfBBagsWhichContainAnother(Set<String> bags){
        long count = fileLines.stream()
                .filter(r -> {
                    int index = r.indexOf("contain");
                    String rule = r.substring(index);

                    boolean anyMatch = bags.stream()
                            .anyMatch(b -> rule.contains(b));

                    return anyMatch;
                } )
                .count();
        return count;
    }

    //task2
    private Long getAllBagsCount(String bag){

        long sum = 0;

        for(String line: fileLines){

            if(!line.contains("no other bags")) {

                int index = line.indexOf("contain");
                String b = line.substring(0, index);

                if (b.contains(bag)) {
                    String endOfLine = line.substring(index + 7);

                    List<String> bags = Arrays.asList(endOfLine.split(","));

                    Map<String, Integer> bagNameCount = getMapFromList(bags);

                    sum = bagNameCount.entrySet()
                            .stream().map(bb ->
                                    bb.getValue() + bb.getValue() * getAllBagsCount(
                                            bb.getKey())).mapToLong(value -> value).sum();
                }
            }
        }

        return sum;
    }

    private Map<String,Integer> getMapFromList (List<String> bags){

        Map<String, Integer> bagNameCount = new HashMap<>();

        for(String bagline: bags){
            bagline.replace(".", "");

            while(bagline.startsWith(" ")){
                bagline = bagline.substring(1);
            }
            if(bagline.endsWith(".")){
                bagline = bagline.substring(0,bagline.length()-1);
            }
            bagline = bagline.replace(" bags", "").replace(" bag", "");

            int whiteSpaceIndex = bagline.indexOf(" ");

            int num = Integer.valueOf(bagline.substring(0, whiteSpaceIndex));
            String newBag = bagline.substring(whiteSpaceIndex+1);

            bagNameCount.put(newBag, num);
        }

        return bagNameCount;

    }


}
