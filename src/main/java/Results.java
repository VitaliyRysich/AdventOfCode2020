import solutions.day1.Day1;
import solutions.day2.Day2;
import solutions.day3.Day3;
import solutions.day4.Day4;
import solutions.day5.Day5;

public class Results {

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        Day2 day2 = new Day2();
        Day3 day3 = new Day3();
        Day4 day4 = new Day4();
        Day5 day5 = new Day5();

        System.out.println(String.format("Day1 {Task1: %d, Task2: %d}", day1.task1(), day1.task2()));
        System.out.println(String.format("Day2 {Task1: %d, Task2: %d}", day2.task1(), day2.task2()));
        System.out.println(String.format("Day3 {Task1: %d, Task2: %d}", day3.task1(), day3.task2()));
        System.out.println(String.format("Day4 {Task1: %d, Task2: %d}", day4.task1(), day4.task2()));
        System.out.println(String.format("Day5 {Task1: %d, Task2: %d}", day5.task1(), day5.task2()));
    }
}
