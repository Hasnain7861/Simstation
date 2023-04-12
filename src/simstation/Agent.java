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

    public Agent() {
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(1000);
                checkSuspended();
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(name + " stopped");
    }

    public synchronized void start() {

    }

    public synchronized void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        notify();
    }

    public synchronized void stop() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(1000);
                checkSuspended();
            } catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(name + " stopped");
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

    }

    public void setSimulation(Simulation simulation) {
        this.mySimulation = simulation;
    }

    public boolean isStopped() {
        return stopped;
    }

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
}
