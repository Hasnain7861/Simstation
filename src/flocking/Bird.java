package flocking;

import mvc.*;
import simstation.*;

class Bird extends Agent {

    public int speed;
    public Bird() {
        super();
        heading = Heading.random();
        speed = (int)(Math.random()*5 +1);
    }

    public void update() {
        Bird neighbor = (Bird)(mySimulation.getNeighbor(this, 30));
        if(neighbor != null)
        {
            speed = neighbor.speed;
            heading = neighbor.heading;
        }
        move(speed);
    }

}
