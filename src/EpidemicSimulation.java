import javax.swing.*;
import java.awt.*;

public class EpidemicSimulation{
    //ilość ludzi i zwierząt
    public static int NUM_HUMANS = 200;
    public static int NUM_ANIMALS = 100;

    public static Human[] humans;
    public static Animal[] animals;

    public static int x = 0;


    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");


        // Tworzymy populacje (na bazie danych od użytkownika) - kolejność do zmiany pierw powinien być ekran


        // Tworzymy panel który od razu wykonuje metode paintComponent
        EntryScreen okno = new EntryScreen();
        okno.Entry_Screen();

        while (x==0){
            System.out.print("");
            if (EntryScreen.i==1){
                EpidemicVisualization panel = new EpidemicVisualization(EpidemicSimulation.humans, EpidemicSimulation.animals);
                Animation animacja = new Animation(humans, animals);
                EntryScreen.frame.add(panel);
                animacja.animacja(panel);
                x=1;}

            if (EntryScreen.i == 2){

                FastSimulation fastSimulation = new FastSimulation(EpidemicSimulation.humans, EpidemicSimulation.animals);
                SecondWindow sw = new SecondWindow();
                JPanel wyniki = new JPanel(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();

                JLabel ladowanie = new JLabel("Wyniki się ładują...");
                c.gridx = 0;
                c.gridy = 0;

                sw.setLocationRelativeTo(null);
                wyniki.add(ladowanie,c);
                sw.add(wyniki);

                for (int i = 0; i < 100; i++) {
                    fastSimulation.fastsimulation();

                }
                wyniki.remove(ladowanie);
                wyniki.revalidate();
                wyniki.repaint();
                double avgDays = Symulacja.Day / 100.0;
                sw.setName("Może to okienko?");
                sw.displayResults(avgDays, wyniki, c);
                sw.setVisible(true);
                x=1;}


            }

        }













        //if() albo animacja albo szybka symulacja (zależy od użytkownika)
        //Animation animacja = new Animation(humans, animals);
        //animacja.animacja(panel);

        //FastSimualation fastsimulation = new FastSimulation(humans, animals)
        //fastsimaltion.simulation()
    }



