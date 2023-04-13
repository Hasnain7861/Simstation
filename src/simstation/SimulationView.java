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
        int height = this.getHeight();
        int width = this.getWidth();

        g2d.setColor(new Color(100, 150, 250));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(80, 100, 60));
        for (Agent a : agents) {
            gc.setColor(Color.WHITE);
            gc.fillOval(a.xc, a.yc,10 ,10 );
        }
    }
}