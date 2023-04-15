package prisonersdilemma;

import mvc.*;
import simstation.*;
public class Prisoner extends Agent {
    private int fitness = 0;
    private boolean partnerCheated = false;
    Strategy strategy;

    public Prisoner(Strategy strategy)
    {
        this.fitness = 0;
        this.partnerCheated = false;
        this.strategy = strategy;
    }

    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Prisoner opponent = (Prisoner)(world.getNeighbor(this, 10));
        if(opponent != null) {
            boolean cooperated = strategy.cooperate();
            boolean enemyCooperated = opponent.cooperate();

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

    public void updateFitness(int amount) {

        this.fitness += amount;
    }
}
