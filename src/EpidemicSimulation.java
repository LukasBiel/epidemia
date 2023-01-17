import javax.swing.*;


public class EpidemicSimulation{
    //ilość ludzi i zwierząt
    public static final int NUM_HUMANS = 200;
    public static final int NUM_ANIMALS = 100;

    public static Human[] humans;
    public static Animal[] animals;


    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        // Tworzymy populacje (na bazie danych od użytkownika) - kolejność do zmiany pierw powinien być ekran
        Populacja populacja = new Populacja();
        populacja.Tworzenie_populacji(NUM_HUMANS, NUM_ANIMALS);

        // Tworzymy panel który od razu wykonuje metode paintComponent
        EpidemicVisualization panel = new EpidemicVisualization(humans, animals);

        // Tworzymy ramkę gdzie się będą wyświetlać rzeczy
        JFrame frame = new JFrame();
        frame.setSize(EpidemicVisualization.WIDTH+145, EpidemicVisualization.HEIGHT+45);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Dodajemy do ramki nasz panel, na początku powinien wyświetlać główny interfejs
        frame.add(panel);
        frame.setVisible(true);



        //if() albo animacja albo szybka symulacja (zależy od użytkownika)
        Animation animacja = new Animation(humans, animals);
        animacja.animacja(panel);

        //FastSimualation fastsimulation = new FastSimulation(humans, animals)
        //fastsimaltion.simulation()
    }
}


