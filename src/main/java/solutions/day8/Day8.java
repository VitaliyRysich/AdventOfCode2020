package solutions.day8;

import reader.Reader;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public class Day8 {

    private final String FILENAME = "day8.txt";
    private List<Command> commands = new ArrayList<>();


    public Day8(){
        prepareData();
    }

    private void prepareData(){
        List<String> stringCommands = Reader.readFileLineByLine(FILENAME);
        for(String c: stringCommands){
            this.commands.add(new Command(c));
        }
    }

    public long task1 (){
        return runWithCondition(commands,
                (commandSet, idx) -> !commandSet.contains(idx));
    }

    public long task2 (){
        int accumulator = 0;
        for(int i=0; i < commands.size(); i++){
            if(commands.get(i).getCommandName() != CommandName.acc){
                List<Command> newList = changeList(commands, i);

                if(!isLoopInfinite(newList)){
                    accumulator = runWithCondition(newList, (c, idx) -> idx != commands.size()-1);
                }
            }
        }
        return accumulator;
    }


    private int runWithCondition(List<Command> commands, BiPredicate<Set<Integer>, Integer> condition){
        int accumulator = 0;
        Set<Integer> executedCommands = new HashSet<>();
        int currentIndex = 0;

        while (condition.test(executedCommands, currentIndex)) {

            executedCommands.add(currentIndex);
            Command command = commands.get(currentIndex);

            switch (command.getCommandName()){
                case acc: {
                    accumulator += command.getValue();
                    currentIndex++;
                    break;
                }
                case jmp: {
                    currentIndex += command.getValue();
                    break;
                }
                case nop: {
                    currentIndex++;
                    break;
                }
            }
        }
        return accumulator;
    }

    private boolean isLoopInfinite(List<Command> commands){
        Set<Integer> executedCommands = new HashSet<>();
        int currentIndex = 0;

        while (!executedCommands.contains(currentIndex)) {
            if(executedCommands.contains(commands.size()-1)){
                return false;
            }
            executedCommands.add(currentIndex);
            Command command = commands.get(currentIndex);

            currentIndex +=
                    command.getCommandName() == CommandName.jmp
                    ? command.getValue()
                    : 1;
        }
        return true;
    }


    private List<Command> changeList(List<Command> commands, int index){
        List<Command> newList = new ArrayList<>();
        newList.addAll(commands);
        Command oldCommand = newList.get(index);
        CommandName commandName = oldCommand.getCommandName() == CommandName.jmp
                ? CommandName.nop
                : CommandName.jmp;
        int val = oldCommand.getValue();

        newList.set(index, new Command(commandName, val));

        return newList;
    }

    public static void main(String[] args) {
        new Day8().task2();
    }





}
