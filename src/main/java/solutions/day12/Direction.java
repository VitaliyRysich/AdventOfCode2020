package solutions.day12;

public enum Direction {
    North, East, South, West;

    private final static Direction[] vals = values();

    public Direction next() {
        return vals[(this.ordinal()+1) % vals.length];
    }
    public Direction previous() {
        return vals[this.ordinal() == 0 ? vals.length -1 : this.ordinal()-1];
    }
}
