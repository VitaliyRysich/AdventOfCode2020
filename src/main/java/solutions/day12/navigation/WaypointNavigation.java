package solutions.day12.navigation;

public class WaypointNavigation extends Navigation{

    private int waypointEastWest = 10;
    private int waypointNorthSouth = 1;


    @Override
    public void actionN(int val) {
        waypointNorthSouth +=val;
    }

    @Override
    public void actionS(int val) {
        waypointNorthSouth -=val;
    }

    @Override
    public void actionE(int val) {
        waypointEastWest += val;
    }

    @Override
    public void actionW(int val) {
        waypointEastWest -=val;
    }

    @Override
    public void actionL(int val) {
        for (int i=0; i < val/90; i++) {
            int tempEW = waypointEastWest;

            waypointEastWest = -1 * waypointNorthSouth;
            waypointNorthSouth = tempEW;
        }
    }

    @Override
    public void actionR(int val) {

        for (int i=0; i < val/90; i++) {
            int tempEW = waypointEastWest;

            waypointEastWest = waypointNorthSouth;
            waypointNorthSouth = -1 * tempEW;
        }
    }

    @Override
    public void actionF(int val) {
        eastWestPos += val * waypointEastWest;
        northSouthPos += val * waypointNorthSouth;
    }

    public int getWaypointEastWest() {
        return waypointEastWest;
    }

    public void setWaypointEastWest(int waypointEastWest) {
        this.waypointEastWest = waypointEastWest;
    }

    public int getWaypointNorthSouth() {
        return waypointNorthSouth;
    }

    public void setWaypointNorthSouth(int waypointNorthSouth) {
        this.waypointNorthSouth = waypointNorthSouth;
    }
}
