package plague;

import mvc.*;
import simstation.*;

import java.awt.*;

public class Person extends Agent {

    public boolean infected;

    public Person() {
        super();
        heading = Heading.random();
        myColor = Color.GREEN;
    }

    public void update() {
        if(infected) myColor = Color.RED;
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Person neighbor = (Person)(mySimulation.getNeighbor(this, 20));
        if (neighbor != null) {
            if (neighbor.infected) {
                int virulenceCheck = Utilities.rng.nextInt(100) + 1;
                if (virulenceCheck <= ((PlagueSimulation)super.mySimulation).VIRULENCE) {
                    int resistanceCheck = Utilities.rng.nextInt(100) + 1;
                    if (resistanceCheck > ((PlagueSimulation)super.mySimulation).RESISTANCE) {
                        infected = true;
                    }
                }
            }
        }

    }

}
