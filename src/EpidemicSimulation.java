


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
        EntryScreen okno = new EntryScreen();
        okno.Entry_Screen();

        Animation animacja = new Animation(EpidemicSimulation.humans, EpidemicSimulation.animals);
        animacja.animacja(okno.panel);








        //if() albo animacja albo szybka symulacja (zależy od użytkownika)
        //Animation animacja = new Animation(humans, animals);
        //animacja.animacja(panel);

        //FastSimualation fastsimulation = new FastSimulation(humans, animals)
        //fastsimaltion.simulation()
    }
}


