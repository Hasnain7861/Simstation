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
        this.fitness = 0;
        this.partnerCheated = false;
        this.strategy = strategy;
    }

    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Prisoner opponent = (Prisoner)(mySimulation.getNeighbor(this, 10));
        if(opponent != null) {
            boolean cooperated = strategy.cooperate();
            boolean enemyCooperated = opponent.getStrategy().cooperate();

            if (cooperated && enemyCooperated) {
                updateFitness(3);
                opponent.updateFitness(3);
            } else if (cooperated && !enemyCooperated) {
                updateFitness(0);
                opponent.updateFitness(5);
            } else if (!cooperated && enemyCooperated) {
                updateFitness(5);
                opponent.updateFitness(0);
            } else if (!cooperated && !enemyCooperated) {
                updateFitness(1);
                opponent.updateFitness(1);
            }
        }
    }

    public void setStrategy(Strategy s) {
        strategy = s;
    }

    public Strategy getStrategy() { return strategy; }

    public void updateFitness(int amount) {

        this.fitness += amount;
    }

    public boolean hasPartnerCheated() {
        return partnerCheated;
    }

    public int getFitness() {
        return fitness;
    }
}
