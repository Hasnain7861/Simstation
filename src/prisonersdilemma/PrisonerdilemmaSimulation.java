package prisonersdilemma;

import mvc.*;
import simstation.*;

import java.awt.*;


public class PrisonerdilemmaSimulation extends Simulation{

    public int cheaters = 10;
    public int cooperators = 10;
    public int randomcooperators = 10;
    public int tit4tatters = 10;

    public void populate()
    {
        for(int i = 0; i < cheaters; i++) {
            Prisoner cheater = new Prisoner(new Cheat());
            cheater.setStrategy(new Cheat());
            addAgent(cheater);
        }
        for(int i = 0; i < cooperators; i++) {
            Agent cheat = new Prisoner(new Cooperate());
            cheat.setColor(Color.red);
            addAgent(cheat);
        }
        for(int i = 0; i < randomcooperators; i++) {
            Agent randomcooperator = new Prisoner(new RandomlyCooperate());
            randomcooperator.setColor(Color.green);
            addAgent(randomcooperator);
        }
        for(int i = 0; i < tit4tatters; i++) {
            Agent tit4tat = new Prisoner(new Tit4Tat());
            tit4tat.setColor(Color.yellow);
            addAgent(tit4tat);
        }
    }

    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new PrisonerdilemmaFactory());
        panel.display();
    }
}
