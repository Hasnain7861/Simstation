package prisonersdilemma;

import mvc.*;
import simstation.*;
public class Prisoner extends Agent {

    private int fitness = 0;
    private boolean partnerCheated = false;
    protected Strategy strategy;

    public Prisoner(Strategy strategy)
    {
        super();
        heading = Heading.random();
        this.fitness = 0;
        this.partnerCheated = false;
        this.strategy = strategy;
        strategy.myprisoner = this;
    }

    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Prisoner opponent = (Prisoner)(mySimulation.getNeighbor(this, 10));
        if(opponent != null) {
            boolean cooperated = cooperate();
            boolean enemyCooperated = opponent.cooperate();

            if (cooperated && enemyCooperated) {
                updateFitness(3);
                opponent.updateFitness(3);
            } else if (cooperated) {
                updateFitness(0);
                opponent.updateFitness(5);
            } else if (enemyCooperated) {
                updateFitness(5);
                opponent.updateFitness(0);
            } else {
                updateFitness(1);
                opponent.updateFitness(1);
            }
        }
    }

    public boolean cooperate() {
        return strategy.cooperate();
    }

    public void setStrategy(Strategy s) {
        strategy = s;
    }

    public Strategy getStrategy() { return strategy; }

    public void updateFitness(int amount) {

        this.fitness += amount;
    }

    public int getFitness() {
        return fitness;
    }

    public boolean hasPartnerCheated() {
        return partnerCheated;
    }
}
