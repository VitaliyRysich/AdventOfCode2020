package solutions.day8;

public class Command {

    private CommandName commandName;
    private int value;

    public Command(CommandName commandName, int value) {
        this.commandName = commandName;
        this.value = value;
    }

    public Command(String command){
        if(command.startsWith("acc")){
            this.commandName = CommandName.acc;
        }
        else if(command.startsWith("nop")){
            this.commandName = CommandName.nop;
        }
        else {
            this.commandName = CommandName.jmp;
        }

        this.value = Integer.valueOf(command
                .replace("+", "")
                .split(" ")[1]);
    }

    public CommandName getCommandName() {
        return commandName;
    }

    public void setCommandName(CommandName commandName) {
        this.commandName = commandName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandName=" + commandName +
                ", value=" + value +
                '}';
    }
}
