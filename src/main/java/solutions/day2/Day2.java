package solutions.day2;

import reader.Reader;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

    private final String FILENAME = "day2.txt";
    private List<PasswordPolicy> passwordPolicyList;


    public Day2(){
        prepareData();
    }

    public long task1 (){
        return countOfValidPasswordByCharCount();
    }

    public long task2 (){
        return countOfValidPasswordByCharPosition();
    }


    private void prepareData(){
        List<String> passwords = Reader.readFileLineByLine(FILENAME);

        List<PasswordPolicy> passwordPolicies = passwords.stream()
                .map(s -> {
                    String[] s1 = s.split(" ");

                    String[] numbersOfTimes = s1[0].split("-");
                    int lowestNumberOfTime = Integer.valueOf(numbersOfTimes[0]);
                    int higestNumberOfTime = Integer.valueOf(numbersOfTimes[1]);

                    char letter = s1[1].charAt(0);

                    String password = s1[2];

                    return new PasswordPolicy(lowestNumberOfTime, higestNumberOfTime, letter, password);
                })
                .collect(Collectors.toList());

        this.passwordPolicyList = passwordPolicies;
    }


    private long countOfValidPasswordByCharCount(){
        long count = passwordPolicyList.stream()
                .filter(p -> p.isValidCountOfLetter())
                .count();

        return count;
    }

    private long countOfValidPasswordByCharPosition(){
        long count = passwordPolicyList.stream()
                .filter(p -> p.isValidLetterPosition())
                .count();

        return count;
    }
}
