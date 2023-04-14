package flocking;

import mvc.*;
import simstation.*;
import java.awt.*;
import java.util.Iterator;

class FlockingFactory extends SimStationFactory {
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
}

