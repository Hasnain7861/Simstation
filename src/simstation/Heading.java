package simstation;

public enum Heading {

    N, NE, NW, E, S, SE, SW, W;

    public static Heading random() {
        int number = (int) (Math.random() * 8);
        if (number == 0) return N;
        if (number == 1) return S;
        if (number == 2) return E;
        if (number == 3) return W;
        if (number == 4) return NE;
        if (number == 5) return NW;
        if (number == 6) return SW;
        return SE;
    }


}
