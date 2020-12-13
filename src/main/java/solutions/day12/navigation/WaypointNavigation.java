package solutions.day12.navigation;

public class WaypointNavigation extends Navigation{

    private int waypointEast = 10;
    private int waypointWest;
    private int waypointNorth = 1;
    private int waypointSouth;


    @Override
    public void actionN(int val) {
        if(waypointSouth != 0){
            if(waypointSouth > val){
                waypointSouth = waypointSouth - val;
            }
            else {
                waypointNorth += val - waypointSouth;
                waypointSouth = 0;
            }
        }
        else {
            waypointNorth += val;
        }
    }

    @Override
    public void actionS(int val) {
        if(waypointNorth != 0){
            if(waypointNorth > val){
                waypointNorth = waypointNorth - val;
            }
            else {
                waypointSouth += val - waypointNorth;
                waypointNorth = 0;
            }
        }
        else {
            waypointSouth += val;
        }
    }

    @Override
    public void actionE(int val) {
        if(waypointWest != 0){
            if(waypointWest > val){
                waypointWest = waypointWest - val;
            }
            else {
                waypointEast += val - waypointWest;
                waypointWest = 0;
            }
        }
        else {
            waypointEast += val;
        }
    }

    @Override
    public void actionW(int val) {
        if(waypointEast != 0){
            if(waypointEast > val){
                waypointEast = waypointEast - val;
            }
            else {
                waypointWest += val - waypointEast;
                waypointEast = 0;
            }
        }
        else {
            waypointWest += val;
        }
    }

    @Override
    public void actionL(int val) {
        for (int i=0; i < val/90; i++) {
            int tempE = waypointEast,
                    tempW = waypointWest,
                    tempN = waypointNorth,
                    tempS = waypointSouth;

            waypointEast = tempS;
            waypointSouth = tempW;
            waypointWest = tempN;
            waypointNorth = tempE;
        }
    }

    @Override
    public void actionR(int val) {

        for (int i=0; i < val/90; i++) {
            int tempE = waypointEast,
                    tempW = waypointWest,
                    tempN = waypointNorth,
                    tempS = waypointSouth;

            waypointEast = tempN;
            waypointSouth = tempE;
            waypointWest = tempS;
            waypointNorth = tempW;
        }
    }

    @Override
    public void actionF(int val) {
        if(waypointEast != 0) eastWestPos += val * waypointEast;
        if(waypointWest != 0) eastWestPos -= val * waypointWest;
        if(waypointNorth != 0) northSouthPos += val * waypointNorth;
        if(waypointSouth != 0) northSouthPos -= val * waypointSouth;
    }

    public int getWaypointEast() {
        return waypointEast;
    }

    public void setWaypointEast(int waypointEast) {
        this.waypointEast = waypointEast;
    }

    public int getWaypointWest() {
        return waypointWest;
    }

    public void setWaypointWest(int waypointWest) {
        this.waypointWest = waypointWest;
    }

    public int getWaypointNorth() {
        return waypointNorth;
    }

    public void setWaypointNorth(int waypointNorth) {
        this.waypointNorth = waypointNorth;
    }

    public int getWaypointSouth() {
        return waypointSouth;
    }

    public void setWaypointSouth(int waypointSouth) {
        this.waypointSouth = waypointSouth;
    }
}
