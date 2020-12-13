package solutions.day12.navigation;

import solutions.day12.Direction;

public abstract class Navigation {

    protected int eastWestPos = 0;
    protected int northSouthPos = 0;


    public void doCommand(String command) {
        char direction = command.charAt(0);
        int value = Integer.parseInt(command.substring(1));

        switch (direction){
            case 'N': actionN(value); break;
            case 'S': actionS(value); break;
            case 'E': actionE(value); break;
            case 'W': actionW(value); break;
            case 'L': actionL(value); break;
            case 'R': actionR(value); break;
            case 'F': actionF(value); break;
        }
    }

    protected abstract void actionN(int val);
    protected abstract void actionS(int val);
    protected abstract void actionE(int val);
    protected abstract void actionW(int val);
    protected abstract void actionL(int val);
    protected abstract void actionR(int val);
    protected abstract void actionF(int val);


    public int getEastWestPos() {
        return eastWestPos;
    }

    public int getNorthSouthPos() {
        return northSouthPos;
    }


}
