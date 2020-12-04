import solutions.day1.Day1;
import solutions.day2.Day2;

public class Results {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        Day2 day2 = new Day2();

        System.out.println(String.format("Day1 {Task1: %d, Task2: %d}", day1.task1(), day1.task2()));
        System.out.println(String.format("Day2 {Task1: %d, Task2: %d}", day2.task1(), day2.task2()));
    }
}
