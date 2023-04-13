package simstation;

public enum Heading {

    NORTH, SOUTH, EAST, WEST;

    public static Heading random() {
        int number = (int) (Math.random() * 4);
        if (number == 0) return NORTH;
        if (number == 1) return SOUTH;
        if (number == 2) return EAST;
        return WEST;
    }


}
