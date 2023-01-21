import javax.swing.*;
import java.awt.*;

public class EpidemicSimulation{
    public static int NUM_HUMANS = 150;
    public static int NUM_ANIMALS = 150;

    public static Human[] humans;
    public static Animal[] animals;
    private static boolean koniec = false ;


    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");

        EntryScreen okno = new EntryScreen();
        okno.Entry_Screen();

        while (!koniec){
            System.out.print("");

            if (EntryScreen.i == 1){
                EpidemicVisualization panel = new EpidemicVisualization(EpidemicSimulation.humans, EpidemicSimulation.animals);
                Animation animacja = new Animation(humans, animals);
                EntryScreen.frame.add(panel);
                animacja.animacja(panel);
                koniec = true;}

            if (EntryScreen.i == 2){

                SecondWindow sw = new SecondWindow();
                JPanel wyniki = new JPanel(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                JLabel ladowanie = new JLabel("Wyniki się ładują...");
                c.gridx = 0;
                c.gridy = 0;

                sw.setLocationRelativeTo(null);
                wyniki.add(ladowanie,c);
                sw.add(wyniki);

                Populacja populacja = new Populacja();

                double avgPercOfDead = 0;
                double Days = 0 ;
                double maxes = 0;

                FastSimulation[] simulations = new FastSimulation[50];
                for (int i = 0; i < 50; i++) {
                    populacja.Tworzenie_populacji(NUM_HUMANS,NUM_ANIMALS);
                    simulations[i] = new FastSimulation(humans, animals);
                }
                System.out.println("Poczatek_symulacji");

                for (FastSimulation simulation : simulations) {

                    simulation.fastsimulation();

                    avgPercOfDead+=Symulacja.DeadHumans/NUM_HUMANS*100;
                    Days+=Symulacja.Day;
                    maxes+=FastSimulation.max;

                    Symulacja.DeadHumans = 0;
                    Symulacja.IllHumans = 1;
                    Symulacja.Day = 0;
                    Symulacja.illHumans.clear();

                }
                wyniki.remove(ladowanie);
                wyniki.revalidate();
                wyniki.repaint();

                avgPercOfDead = avgPercOfDead/50.0;
                double avgDays = Days/50.0;
                maxes = maxes/50;

                sw.setName("Może to okienko?");
                sw.displayResults(avgDays,Days,avgPercOfDead, wyniki,maxes, c);
                sw.setVisible(true);
                koniec = true;}

            }

        }
    }



