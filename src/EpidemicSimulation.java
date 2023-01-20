


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

        }

        //if() albo animacja albo szybka symulacja (zależy od użytkownika)
        //Animation animacja = new Animation(humans, animals);
        //animacja.animacja(panel);

        //FastSimualation fastsimulation = new FastSimulation(humans, animals)
        //fastsimaltion.simulation()
    }
}


