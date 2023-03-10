import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class EpidemicVisualization extends JPanel {
    private static final DecimalFormat df = new DecimalFormat("0.0");
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private final Human[] humans;
    private final Animal[] animals;

    public EpidemicVisualization(Human[] humans, Animal[] animals) {
        this.humans = humans;
        this.animals = animals;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rysuje ludzi i zwierzęta
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
                    color = Color.BLUE;
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
                    color = Color.BLUE;
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
        g.setColor(Color.ORANGE);
        g.fillRect(620,10,90,35);
        double x = Symulacja.HealthyHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        double x2 = Symulacja.DeadHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        double x3 = Symulacja.IllHumans/((double) EpidemicSimulation.NUM_HUMANS)*100;
        g.setColor(Color.BLUE);
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
        if (Animation.czy_koniec==1){
            String koniec = "Koniec epidemii!";
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(koniec, 620, 135);

        }
        // tutaj trzeba dodać legendę
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.black);
        g.drawString("Legenda", 780, 32);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Ludzie", 750, 50);
        g.drawString("Zwierzęta", 830, 50);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Zdrowi", 750, 70);
        g.drawString("Zdrowe", 830, 70);
        g.drawString("Chorzy", 750, 90);
        g.drawString("Chore", 830, 90);
        g.drawString("Wyzdrowieli", 750, 110);
        g.drawString("Wyzdrowiałe", 830, 110);
        g.drawString("Zmarli", 750, 130);
        g.drawString("Zmarłe", 830, 130);
        g.setColor(Color.GREEN);
        g.fillOval(790, 63, 5, 5);
        g.fillRect(880, 64, 5, 5);
        g.setColor(Color.RED);
        g.fillOval(790, 83, 5, 5);
        g.fillRect(880, 84, 5, 5);
        g.setColor(Color.BLUE);
        g.fillOval(818, 103, 5, 5);
        g.fillRect(905, 104, 5, 5);
        g.setColor(Color.BLACK);
        g.fillOval(790, 123, 5, 5);
        g.fillRect(880, 124, 5, 5);


    }
}