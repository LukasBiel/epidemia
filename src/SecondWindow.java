import javax.swing.*;
import java.awt.*;

public class SecondWindow extends JFrame {
    private int days;
    private int dead_percentage;
    public SecondWindow(){
        this.setSize(EpidemicVisualization.WIDTH + 145, EpidemicVisualization.HEIGHT + 45);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Wynik szybkiej symulacji");

    }
    public void runSimulation(){
        // put here code for running the simulation and updating the variables
        Timer Dni = new Timer(10, e -> Symulacja.Day++);
        Dni.start();
    }
    public int getDays(){
        return Symulacja.Day ;
    }
    public void displayResults(double avgDays){
        JPanel wyniki = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        wyniki.setName("Okienko");
        this.add(wyniki);
        JLabel num_of_days = new JLabel("Liczba dni:" + getDays());

        c.gridx = 0;
        c.gridy = 0;
        wyniki.add(num_of_days,c);

        JLabel num_of_h = new JLabel("Początkowa liczba ludzi:" + EpidemicSimulation.NUM_HUMANS );
        c.gridx = 0;
        c.gridy = 1;
        wyniki.add(num_of_h,c);

        JLabel num_of_a = new JLabel("Początkowa liczba zwierząt:" + EpidemicSimulation.NUM_ANIMALS);
        c.gridx = 1;
        c.gridy = 1;
        wyniki.add(num_of_a,c);

        JLabel averDays = new JLabel("Średni dzień zakończenia epidemii: " + avgDays);
        c.gridx = 0;
        c.gridy = 2;
        wyniki.add(averDays,c);

        JLabel procOfDead = new JLabel("procent zmarłych: " + dead_percentage);
        c.gridx = 0;
        c.gridy = 2;
        wyniki.add(averDays,c);


        // put here code for displaying the final variables and avg days on the second window
    }
}

