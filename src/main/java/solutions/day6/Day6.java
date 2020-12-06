package solutions.day6;

import reader.Reader;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {

    private final String FILENAME = "day6.txt";
    private String file;


    public Day6(){
        prepareData();
    }

    private void prepareData(){
        this.file = Reader.readFile(FILENAME);
    }

    public long task1 (){
        List<List<String>> groupsAnswers = getGroupsAnswers();

        long sum = groupsAnswers.stream()
                .mapToInt(group -> getAllGroupAnswers(group))
                .sum();

        return sum;
    }

    public long task2 (){

        List<List<String>> groupsAnswers = getGroupsAnswers();

        long sum = groupsAnswers.stream()
                .mapToLong(group -> getSameGroupAnswers(group))
                .sum();

        return sum;
    }

    private List<List<String>> getGroupsAnswers(){
        List<String> groups = Arrays.asList(file.split("(?m)^\\s*$"));

        List<List<String>> collect = groups.stream()
                .map(p ->
                    {
                        if(p.startsWith("\r\n")){
                            p = p.substring(2);
                        }
                        return Arrays.asList(p.split("\r\n"));
                    })
                .collect(Collectors.toList());

        return collect;
    }

    private int getAllGroupAnswers(List<String> groupList){
        Set<Character> set = new HashSet<>();
        groupList.stream()
                .forEach(
                       g -> {
                           for(Character ch: g.toCharArray()){
                               set.add(ch);
                           }
                       }
                );

        return set.size();
    }

    private long getSameGroupAnswers(List<String> answersOfGroup){

        List<Character> allAvaliableAnswers = answersOfGroup.get(0).chars()
                .mapToObj(c ->(char) c)
                .collect(Collectors.toList());

        long count = allAvaliableAnswers.stream()
                .filter(answer ->
                        answersOfGroup.stream()
                                .allMatch(answersList -> answersList.contains(answer.toString())))
                .count();

        return count;
    }

}
