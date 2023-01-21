import javax.swing.*;
import java.awt.*;

public class EntryScreen extends JFrame {
    private final JFrame frame2 = new JFrame("Okno Danych");
    public static final JFrame frame = new JFrame();
    public static int i = 0 ;

    public void Entry_Screen() {

        JPanel menu = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // tworzenie okien i ich rozmiarów

        frame2.setSize(EpidemicVisualization.WIDTH,  200);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationRelativeTo(null);
        frame.setSize(EpidemicVisualization.WIDTH + 350, EpidemicVisualization.HEIGHT + 45);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame2.add(menu);

        JLabel num_of_h = new JLabel("Liczba Ludzi");
        c.gridx = 0;
        c.gridy = 0;

        menu.add(num_of_h,c);

        JTextField num_of_humans = new JTextField("150");
        c.gridx = 0;
        c.gridy = 1;

        menu.add(num_of_humans,c);

        JLabel num_of_a = new JLabel("Liczba Zwierząt");
        c.gridx = 1;
        c.gridy = 0;

        menu.add(num_of_a,c);


        JTextField num_of_animals = new JTextField("150");
        c.gridx = 1;
        c.gridy = 1;

        menu.add(num_of_animals,c);

        JLabel mobi = new JLabel("Mobilność");
        c.gridx = 2;
        c.gridy = 0;

        menu.add(mobi,c);


        JTextField mobility = new JTextField("1");
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

        JLabel is_isolating = new JLabel("Włączyć izolację?");
        c.gridx = 2;
        c.gridy = 4;

        menu.add(is_isolating,c);

        JCheckBox isolation = new JCheckBox();
        c.gridx = 3;
        c.gridy = 4;
        isolation.setSelected(false);
        isolation.addActionListener(e -> {

        });

        menu.add(isolation,c);

        JButton openAnim = new JButton("Uruchom Animację");
        c.gridx = 0;
        c.gridy = 4;
        menu.add(openAnim,c);

        JButton fastSimulation = new JButton("Uruchom szybką symulację");
        c.gridx = 1;
        c.gridy = 4;

        frame2.setVisible(true);

        fastSimulation.addActionListener(e -> {

            EpidemicSimulation.NUM_HUMANS = Integer.parseInt(num_of_humans.getText());
            EpidemicSimulation.NUM_ANIMALS = Integer.parseInt(num_of_animals.getText());

            Symulacja.HEALTHY_ANIMAL_PROPABILITY = Double.parseDouble(animal_eating.getText());
            Symulacja.MOBILITY = Double.parseDouble(mobility.getText());
            Symulacja.HUMAN_ILLNESS_PROBABLITY = Double.parseDouble(human_illness.getText());
            Symulacja.ANIMAL_ILLNESS_PROPABILITY = Double.parseDouble(animal_illness.getText());
            frame2.dispose();
            i=2;
        });

        menu.add(fastSimulation,c);

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

    }
}
