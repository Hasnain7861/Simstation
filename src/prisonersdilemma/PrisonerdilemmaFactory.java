package prisonersdilemma;

import mvc.*;
import simstation.*;

public class PrisonerdilemmaFactory extends SimStationFactory
{
    public Model makeModel() { return new Simulation(); }
    public String getTitle() { return "PrisonersDilemma";}

    public String about() {
        return "A simulation that tests the different possible strategies that could be taken and shows each strategy's average score.";
    }
}
