package flocking;

import mvc.*;
import simstation.*;

public class FlockingSimulation extends Simulation {

    public void populate() {
        for(int i = 0; i < 30; i++)
            addAgent(new Bird());
    }
    @Override
    public String[] getStats()
    {
        int speed1 = 0;
        int speed2 = 0;
        int speed3 = 0;
        int speed4 = 0;
        int speed5 = 0;

        Bird temp = new Bird();
        for(int i = 0; i<getAgents().size(); i++)
        {
            temp = (Bird)(getAgents().get(i));
            if(temp.speed == 1)
            {
                speed1++;
            }
            else if(temp.speed == 2)
            {
                speed2++;
            }
            else if(temp.speed == 3)
            {
                speed3++;
            }
            else if(temp.speed == 4)
            {
                speed4++;
            }
            else if(temp.speed == 5)
            {
                speed5++;
            }
        }

        String[] stats = new String[]{"#Agents = " + super.getAgents().size() , "clock = " + super.getClock(), "#birds at speed 1 = " + speed1 , "#birds at speed 2 = " + speed2 ,
                "#birds at speed 3 = " + speed3 , "#birds at speed 4 = " + speed4 , "#birds at speed 5 = " + speed5};
        return stats;
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new FlockingFactory());
        panel.display();
    }

}

