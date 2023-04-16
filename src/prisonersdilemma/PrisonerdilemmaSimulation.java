package prisonersdilemma;

import flocking.Bird;
import mvc.*;
import simstation.*;

import java.awt.*;


public class PrisonerdilemmaSimulation extends Simulation{

    public int cheaters = 10;
    public int cooperators = 10;
    public int randomcooperators = 10;
    public int tit4tatters = 10;

    @Override
    public void populate()
    {

        for(int i = 0; i < cheaters; i++) {
            Prisoner cheater = new Prisoner(new Cheat());
            addAgent(cheater);
        }
        for(int i = 0; i < cooperators; i++) {
            Prisoner cheat = new Prisoner(new Cooperate());
            cheat.myColor = Color.red;
            addAgent(cheat);
        }
        for(int i = 0; i < randomcooperators; i++) {
            Prisoner randomcooperator = new Prisoner(new RandomlyCooperate());
            randomcooperator.myColor = Color.green;
            addAgent(randomcooperator);
        }
        for(int i = 0; i < tit4tatters; i++) {
            Prisoner tit4tat = new Prisoner(new Tit4Tat());
            tit4tat.myColor = Color.yellow;
            addAgent(tit4tat);
        }
    }

    public String[] getStats() {
        double averageCheat = 0;
        double averageCooperate = 0;
        double averageRandCooperate = 0;
        double averageTit4Tat = 0;

        double cheatCount = 0;
        double cooperateCount = 0;
        double randomCount = 0;
        double tit4TatCount = 0;

        Prisoner temp = new Prisoner();
        for(int i = 0; i < getAgents().size(); i++)
        {
            temp = (Prisoner)(getAgents().get(i));
            String type = temp.getStrategy().strategy;

            if(type.equalsIgnoreCase("Cooperate"))
            {
                averageCooperate += temp.getFitness();
                cooperateCount += 1;
            }

            else if(type.equalsIgnoreCase("RandCooperate"))
            {
                averageRandCooperate += temp.getFitness();
                randomCount += 1;
            }

            else if(type.equalsIgnoreCase("Cheat"))
            {
                averageCheat += temp.getFitness();
                cheatCount += 1;
            }

            else
            {
                averageTit4Tat += temp.getFitness();
                tit4TatCount += 1;
            }
        }
        averageCheat = averageCheat / cheatCount;
        averageCooperate = averageCooperate / cooperateCount;
        averageRandCooperate = averageRandCooperate / randomCount;
        averageTit4Tat = averageTit4Tat / tit4TatCount;

        String[] stats = new String[]{"Average score for cheaters = " + averageCheat , "Average score for cooperators = " + averageCooperate ,
                "#Average score for random cooperators = " + averageRandCooperate , "Average score for tit4tatters = " + averageTit4Tat};
        return stats;
    }


    public static void main(String[] args)
    {
        AppPanel panel = new SimulationPanel(new PrisonerdilemmaFactory());
        panel.display();
    }
}
