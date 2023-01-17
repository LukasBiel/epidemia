

import javax.swing.*;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

// klasa wizualizacji
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
        // Udział procentowy i dzień
        g.setColor(Color.BLUE);
        g.fillRect(620,10,90,35);
        double x = Animation.HealthyHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        double x2 = Animation.DeadHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        double x3 = Animation.IllHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        String value = "Zdrowi ludzie: "+df.format(x)+"%";
        g.drawString(value, 620, 70);
        String value2 = "Zmarli: "+df.format(x2)+"%";
        g.drawString(value2, 620, 110);
        String value3 = "Chorzy ludzie: "+df.format(x3)+"%";
        g.drawString(value3, 620, 90);
        String value4 = "Dzień "+ Animation.Day;
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.black);
        g.drawString(value4, 630, 32);

        // wykres
        if (Animation.illHumans.size()>0){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        for (int i = 0; i< Animation.argument_wykres; i++){
            g2d.drawRect(WIDTH+i/4, HEIGHT-Animation.illHumans.get(i) , 1,Animation.illHumans.get(i) );
        }
        }
        // tutaj trzeba dodać legendę

    }
}

