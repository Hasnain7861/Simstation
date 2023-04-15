package plague;

import mvc.*;
import simstation.*;
import java.text.DecimalFormat;

public class PlagueSimulation extends Simulation {

    public static int VIRULENCE = 30; // % chance of infection
    public static int RESISTANCE = 70; // % chance of resisting infection
    public static int INFECTED = 1; // starting # of hosts (infected)

    public void populate() {
        int count = 0;
        for (int i = 0; i < 50; i++) {
            Person p = new Person();
            if (count < INFECTED) {
                p.infected = true;
                count++;
            }
            addAgent(p);
        }
    }

    @Override
    public String[] getStats() {
        double hosts = 0;
        double count = 0;
        Person temp;
        for (Agent a: getAgents()) {
            count++;
            temp = (Person)a;
            if (temp.infected) {
                hosts++;
            }
        }

        DecimalFormat df = new DecimalFormat("#.##"); // Format to two decimal places
        String formatted = df.format((hosts/count)*100);
        return new String[]{"#Agents = " + super.getAgents().size() , "clock = " + super.getClock(), "% infected = " + formatted + "%"};
    }

    public static void main(String[] args) {
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }


}