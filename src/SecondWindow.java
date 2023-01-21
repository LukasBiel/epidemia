import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class SecondWindow extends JFrame {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public SecondWindow(){
        this.setSize(600,  200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setName("Wynik szybkiej symulacji");
        this.setVisible(true);
    }

    public void displayResults(double avgDays ,double Days, double dead_percentage, JPanel wyniki, double maxes, GridBagConstraints c){

        wyniki.setName("Okienko");
        this.add(wyniki);

        JLabel num_of_days = new JLabel("Liczba dni: " + Days);
        c.gridx = 0;
        c.gridy = 0;
        wyniki.add(num_of_days,c);

        JLabel num_of_h = new JLabel("Początkowa liczba ludzi: " + EpidemicSimulation.NUM_HUMANS );
        c.gridx = 0;
        c.gridy = 1;
        wyniki.add(num_of_h,c);

        JLabel num_of_a = new JLabel("Początkowa liczba zwierząt: " + EpidemicSimulation.NUM_ANIMALS);
        c.gridx = 1;
        c.gridy = 1;
        wyniki.add(num_of_a,c);

        JLabel averDays = new JLabel("Średni dzień zakończenia epidemii: " + avgDays);
        c.gridx = 0;
        c.gridy = 3;
        wyniki.add(averDays,c);

        JLabel procOfDead = new JLabel("Średni procent zmarłych: " + df.format(dead_percentage)+"%");
        c.gridx = 0;
        c.gridy = 4;
        wyniki.add(procOfDead,c);

        JLabel Maxes = new JLabel("Średnia maksymalna ilość zakażeń: " + maxes);
        c.gridx = 0;
        c.gridy = 5;
        wyniki.add(Maxes,c);


    }
}

