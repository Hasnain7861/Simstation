package randomwalk;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class RandomWalkFactory extends SimStationFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}

