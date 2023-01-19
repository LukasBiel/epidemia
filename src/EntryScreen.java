import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EntryScreen extends JFrame {
    private final JFrame frame2 = new JFrame("Okno Danych");
    private final JFrame frame = new JFrame();

    private final JFrame frame3 = new JFrame();
    EpidemicVisualization panel = new EpidemicVisualization(EpidemicSimulation.humans, EpidemicSimulation.animals);

    public void Entry_Screen() {

        JPanel menu = new JPanel(new GridBagLayout());
        // tworzenie okien i ich rozmiarów
        GridBagConstraints c = new GridBagConstraints();
        frame2.setSize(EpidemicVisualization.WIDTH + 145, EpidemicVisualization.HEIGHT + 45);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(EpidemicVisualization.WIDTH + 145, EpidemicVisualization.HEIGHT + 45);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame2.add(menu);
        JTextField num_of_humans = new JTextField("Liczba ludzi");
        c.gridx = 0;
        c.gridy = 0;

        menu.add(num_of_humans,c);

        JTextField num_of_animals = new JTextField("Liczba Zwierząt");
        c.gridx = 1;
        c.gridy = 0;

        menu.add(num_of_animals,c);

        JTextField mobility = new JTextField("Mobilonść");
        c.gridx = 2;
        c.gridy = 0;

        menu.add(mobility,c);

        JTextField human_illness = new JTextField("chujmujdzikiwonsz");
        c.gridx = 0;
        c.gridy = 1;

        menu.add(human_illness,c);

        JTextField animal_illness = new JTextField("Liczba Zwierząt");
        c.gridx = 1;
        c.gridy = 1;

        menu.add(animal_illness,c);

        JButton openAnim = new JButton("Uruchom Animację");
        c.gridx = 0;
        c.gridy = 2;
        menu.add(openAnim,c);

        JButton fastSimulation = new JButton("Uruchom szybką symulację");
        c.gridx = 1;
        c.gridy = 2;
        menu.add(fastSimulation,c);
        frame2.setVisible(true);

        openAnim.addActionListener(ae -> {
            frame2.dispose();
            frame.add(panel);
            frame.setVisible(true);
        });

        fastSimulation.addActionListener(ae -> {
            frame2.dispose();
            frame3.setVisible(true);
        });
    }
}