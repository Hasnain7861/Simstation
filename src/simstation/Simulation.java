package simstation;

import java.util.*;
import mvc.*;

public class Simulation extends Model {

    private Timer timer;
    private int clock;
    private List<Agent> agents;

    public Simulation()
    {
        clock = 0;
        agents = new ArrayList<Agent>();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }
    public List<Agent> getAgents()
    {
        return agents;
    }

    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }
    public void addAgent(Agent a)
    {
        agents.add(a);
        a.setSimulation(this);
    }

    public void start()
    {
        for(Agent a: agents) {
            Thread thread = new Thread(a);
            thread.start();
        }
        startTimer();
    }
    public void stop()
    {
        for(Agent a: agents) { a.stop(); }
        stopTimer();
    }
    public void suspend()
    {
        for(Agent a: agents) { a.suspend();}
    }
    public void resume()
    {
        for(Agent a: agents) { a.resume(); }
    }
    public void stats()
    {
        Utilities.inform("#Agents = " + agents.size() + "\nclock = " + clock);

    }



    public Agent getNeighbor(Agent a, double radius)
    {
        int i = (int)(Math.random()*agents.size());
        int startingPoint = i;
        double distance = 0;
        double xDist = 0;
        double yDist = 0;
        do{
            if(agents.get(i) != a)
            {
                xDist = Math.abs(agents.get(i).xc - a.xc);
                yDist = Math.abs(agents.get(i).yc - a.yc);
                distance = xDist*xDist + yDist*yDist;
                distance = Math.sqrt(distance);
                if(distance <= radius)
                {
                    return agents.get(i);
                }
            }
            if(i < agents.size() - 1)
                i++;
            else
                i = 0;
        }while(i != startingPoint);
        return null;
    }

    public void populate()
    {
        for(int i = 0; i < 50; i++)
            addAgent(new Agent());
    }

    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
            //changed();
        }
    }

    // etc.

}