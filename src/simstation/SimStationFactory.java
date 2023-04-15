package simstation;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class SimStationFactory implements AppFactory{

    public Model makeModel() { return new Simulation(); }

    public View makeView(Model m)
    {
        return new SimulationView( (Simulation) m);
    }

    public String getTitle()
    {
        return "SimStation Project";
    }

    public String[] getEditCommands()
    {
        return new String[] {"Start", "Resume", "Stats", "Suspend", "Stop"};
    }

    public Command makeEditCommand(Model model, String type, Object source)
    {
        if(type.equals("Start"))
        {
            return new StartCommand(model);
        }

        if(type.equals("Resume"))
        {
            return new ResumeCommand(model);
        }

        if(type.equals("Stats"))
        {
            return new StatsCommand(model);
        }

        if(type.equals("Suspend"))
        {
            return new SuspendCommand(model);
        }

        if(type.equals("Stop"))
        {
            return new StopCommand(model);
        }
        return null;
    }

    public String[] getHelp()
    {
        return new String[] {" \"Start\" begins the simulation" + "\n \"Suspend\" to pause " + " \n \"Resume\" will continue " + "\n  \"Stop\" the end the simulation" + " \n \"Stats\" will show the statistics of the simulation. "};
    }

    public String about()
    {
        return "CS151-02 SimStation Project by Team 1";
    }
}
