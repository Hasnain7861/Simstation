package simstation;

import java.io.Serializable;

public class Agent implements Serializable, Runnable {

    protected String name;
    protected Heading heading;
    protected int xc;
    protected int yc;
    private boolean suspended;
    private boolean stopped;
    protected Thread myThread;
    protected Simulation mySimulation;

    public Agent(String name) {
        this.name = name;
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public Agent() {
        this.name = null;
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public void onStart() {

    }

    public void onInterrupted() {

    }

    public void onExit() {

    }


    public void run() {
        myThread = Thread.currentThread();
        onStart();

        while (!isStopped()) {
            try {
                update();
                Thread.sleep(1000);
                checkSuspended();
            } catch(InterruptedException e) {
                onInterrupted();
                System.out.println(e.getMessage());
            }
        }
        onExit();
        System.out.println(name + " stopped");
    }

    public synchronized void start() {

    }

    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update() {}

    public synchronized void move(int steps) {
        switch(heading) {

            case NORTH: {
                yc -= steps;
                if (yc < 0) {
                    yc = (yc % 500) + 500;
                }

                break;
            }

            case EAST: {
                xc += steps;
                if (xc > 500) {
                    xc = (xc % 500);
                }

                break;
            }

            case SOUTH: {
                yc += steps;
                if (yc > 500) {
                    yc = (yc % 500);
                }

                break;
            }

            case WEST: {
                xc -= steps;
                if (xc < 0) {
                    xc = (xc % 500) + 500;
                }

                break;
            }


        }
    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        notify();
    }

    public synchronized void stop() { stopped = true; }

    public void setSimulation(Simulation s) {
        mySimulation = s;
    }

    public boolean isStopped() {
        return stopped;
    }

    public synchronized boolean isSuspended() { return suspended;  }

    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (suspended) result += " (suspended)";
        else result += " (running)";
        return result;
    }
}
