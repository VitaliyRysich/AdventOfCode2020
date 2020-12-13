package solutions.day12.navigation;

import solutions.day12.Direction;

public class SimpleNavigation extends Navigation{

    private Direction direction = Direction.East;

    private void toLeft() {
        direction = direction.previous();
    }

    private void toRight() {
        direction = direction.next();
    }

    @Override
    public void actionN(int val) {
        northSouthPos += val;
    }

    @Override
    public void actionS(int val) {
        northSouthPos -= val;
    }

    @Override
    public void actionE(int val) {
        eastWestPos += val;
    }

    @Override
    public void actionW(int val) {
        eastWestPos -= val;
    }

    @Override
    public void actionL(int val) {
        for (int i=0; i < val/90; i++) {
            toLeft();
        }
    }

    @Override
    public void actionR(int val) {
        for (int i=0; i < val/90; i++) {
            toRight();
        }
    }

    @Override
    public void actionF(int val) {
        goForward(val);
    }

    public void goForward(int value){
        switch (direction){
            case North: northSouthPos += value; break;
            case South: northSouthPos -= value; break;
            case East: eastWestPos += value; break;
            case West: eastWestPos -= value; break;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
