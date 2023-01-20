import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EntryScreen extends JFrame {
    private final JFrame frame2 = new JFrame("Okno Danych");
    public static final JFrame frame = new JFrame();

    private final JFrame frame3 = new JFrame();


    public static int i = 0 ;

    public void Entry_Screen() {

        JPanel menu = new JPanel(new GridBagLayout());
        // tworzenie okien i ich rozmiarów
        GridBagConstraints c = new GridBagConstraints();
        frame2.setSize(EpidemicVisualization.WIDTH + 145, EpidemicVisualization.HEIGHT + 45);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(EpidemicVisualization.WIDTH + 145, EpidemicVisualization.HEIGHT + 45);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame2.add(menu);

        JLabel num_of_h = new JLabel("Liczba Ludzi");
        c.gridx = 0;
        c.gridy = 0;

        menu.add(num_of_h,c);

        JTextField num_of_humans = new JTextField("100");
        c.gridx = 0;
        c.gridy = 1;

        menu.add(num_of_humans,c);

        JLabel num_of_a = new JLabel("Liczba Zwierząt");
        c.gridx = 1;
        c.gridy = 0;

        menu.add(num_of_a,c);


        JTextField num_of_animals = new JTextField("100");
        c.gridx = 1;
        c.gridy = 1;

        menu.add(num_of_animals,c);

        JLabel mobi = new JLabel("Mobilność");
        c.gridx = 2;
        c.gridy = 0;

        menu.add(mobi,c);


        JTextField mobility = new JTextField("0.01");
        c.gridx = 2;
        c.gridy = 1;

        menu.add(mobility,c);

        JLabel hum_ill = new JLabel("Chorowanie Ludzi");
        c.gridx = 0;
        c.gridy = 2;

        menu.add(hum_ill,c);

        JTextField human_illness = new JTextField("0.8");
        c.gridx = 0;
        c.gridy = 3;

        menu.add(human_illness,c);

        JLabel ani_ill = new JLabel("Chorowanie Zwierząt");
        c.gridx = 1;
        c.gridy = 2;

        menu.add(ani_ill,c);

        JTextField animal_illness = new JTextField("0.8");
        c.gridx = 1;
        c.gridy = 3;

        menu.add(animal_illness,c);

        JLabel eat_ill = new JLabel("Chorowanie przez zjedzenie");
        c.gridx = 2;
        c.gridy = 2;

        menu.add(eat_ill,c);

        JTextField animal_eating = new JTextField("0.99");
        c.gridx = 2;
        c.gridy = 3;

        menu.add(animal_eating,c);

        JButton openAnim = new JButton("Uruchom Animację");
        c.gridx = 0;
        c.gridy = 4;
        menu.add(openAnim,c);

        JButton fastSimulation = new JButton("Uruchom szybką symulację");
        c.gridx = 1;
        c.gridy = 4;
        menu.add(fastSimulation,c);

        frame2.setVisible(true);

        openAnim.addActionListener(ae -> {


            EpidemicSimulation.NUM_HUMANS = Integer.parseInt(num_of_humans.getText());
            EpidemicSimulation.NUM_ANIMALS = Integer.parseInt(num_of_animals.getText());

            Populacja populacja = new Populacja();
            populacja.Tworzenie_populacji(EpidemicSimulation.NUM_HUMANS, EpidemicSimulation.NUM_ANIMALS);

            Symulacja.HEALTHY_ANIMAL_PROPABILITY = Double.parseDouble(animal_eating.getText());
            Symulacja.MOBILITY = Double.parseDouble(mobility.getText());
            Symulacja.HUMAN_ILLNESS_PROBABLITY = Double.parseDouble(human_illness.getText());
            Symulacja.ANIMAL_ILLNESS_PROPABILITY = Double.parseDouble(animal_illness.getText());

            frame2.dispose();

            frame.setVisible(true);
            i=1;


        });



        fastSimulation.addActionListener(ae -> {
            frame2.dispose();
            frame3.setVisible(true);});
    }
}
