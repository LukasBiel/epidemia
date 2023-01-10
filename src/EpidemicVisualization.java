

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class EpidemicVisualization extends JPanel {
    private static final DecimalFormat df = new DecimalFormat("0.0");
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private Human[] humans;
    private Animal[] animals;

    public EpidemicVisualization(Human[] humans, Animal[] animals) {
        this.humans = humans;
        this.animals = animals;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the humans
        for (Human human : humans) {
            int x = (int) (human.getX() * WIDTH);
            int y = (int) (human.getY() * HEIGHT);
            Color color;
            switch (human.getHealthStatus()) {
                case HEALTHY:
                    color = Color.GREEN;
                    break;
                case ILL:
                    color = Color.RED;
                    break;
                case CONVALESCENT:
                    color = Color.YELLOW;
                    break;
                case DEAD:
                    color = Color.BLACK;
                    break;
                default:
                    throw new IllegalStateException("Invalid health status");
            }
            g.setColor(color);
            g.fillOval(x, y, 5, 5);
        }

        // Draw the animals
        for (Animal animal : animals) {
            int x = (int) (animal.getX() * WIDTH);
            int y = (int) (animal.getY() * HEIGHT);
            Color color;
            switch (animal.getHealthStatus()) {
                case HEALTHY:
                    color = Color.GREEN;
                    break;
                case ILL:
                    color = Color.RED;
                    break;
                case CONVALESCENT:
                    color = Color.YELLOW;
                    break;
                case DEAD:
                    color = Color.BLACK;
                    break;
                default:
                    throw new IllegalStateException("Invalid health status");
            }
            g.setColor(color);
            g.fillRect(x, y, 5, 5);
        }
        g.setColor(Color.BLUE);
        g.fillRect(620,10,90,35);
        double x = (EpidemicSimulation.HealthyHumans/EpidemicSimulation.NUM_HUMANS)*100;
        double x2 = EpidemicSimulation.DeadHumans/EpidemicSimulation.NUM_HUMANS*100;
        double x3 = EpidemicSimulation.IllHumans/EpidemicSimulation.NUM_HUMANS*100;
        String value = "Zdrowi ludzie: "+df.format(x)+"%";
        g.drawString(value, 620, 70);
        String value2 = "Zmarli: "+df.format(x2)+"%";
        g.drawString(value2, 620, 110);
        String value3 = "Chorzy ludzie: "+df.format(x3)+"%";
        g.drawString(value3, 620, 90);
        String value4 = "Dzień "+EpidemicSimulation.Day;
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.black);
        g.drawString(value4, 630, 32);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (int i = 0; i<=EpidemicSimulation.x; i++){
            g2d.drawRect(WIDTH+i/4, HEIGHT-EpidemicSimulation.illHumans.get(i) , 1,EpidemicSimulation.illHumans.get(i) );
        }

    }
}

