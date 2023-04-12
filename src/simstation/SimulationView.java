package simstation;

import mvc.AppPanel;
import mvc.View;

import java.awt.*;
import java.util.List;

public class SimulationView extends View {

    public SimulationView(Simulation model) {
        super(model);
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation s = (Simulation) model;
        List<Agent> agents = s.getAgents();

        Graphics2D g2d = (Graphics2D) gc;
        int currentDisplayHeight = this.getHeight();
        int currentDisplayWidth = this.getWidth();

        g2d.setColor(new Color(100, 150, 250));
        g2d.fillRect(
                0,
                0,
                currentDisplayWidth,
                currentDisplayHeight);
        g2d.setColor(new Color(80, 100, 60));  //
        for (Agent a : agents) {
            gc.setColor(Color.BLUE);
            //not sure what goes in the other 2 parameters gc.fillOval(a.getXc(), a.getYc(), , );
        }
    }
}