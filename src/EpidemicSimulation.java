import javax.swing.*;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class EpidemicSimulation {
    //ilość ludzi i zwierząt
    public static final int NUM_HUMANS2=200;
    public static final double NUM_HUMANS = 200;
    public static final int NUM_ANIMALS = 100;
    //mobilność ludzi i zwierząt
    public static final double MOBILITY = 0.01;
    // prawdopodobieństwa zarażenia i wyboru zdrowego zwierzęcia
    public static final double ANIMAL_ILLNESS_PROPABILITY = 0.8;
    public static final double HUMAN_ILLNESS_PROPABLITY = 0.8;
    public static final double HEALTHY_ANIMAL_PROPABILITY = 0.99;
    // czas updatowania animacji oraz średni czas trwania choroby
    public static final int ITERATION_DURATION_MILLISECONDS = 70;
    public static final int TIME_OF_ILLNESS = 10000;

    // tworzymy listy osobników w danych stanach aby na bieżąco sprawdzać ich ilość
    static List<Animal> healthyAnimals = new ArrayList<>();
    static List<Animal> illAnimals = new ArrayList<>();
    static List<Animal> deadAnimals = new ArrayList<>();

    static List<Integer> illHumans = new ArrayList<>();

    public static double HealthyHumans = NUM_HUMANS;
    public static double DeadHumans = 0;
    public static int IllHumans = 0;
    public static int Day = 0;
    public static int x = 0;


    public static void main(String[] args) {

        System.setProperty("sun.java2d.opengl", "true");
        Random rand = new Random();

        // Tworzymy i uzupełniamy tablice ludzi i zwierząt (losowe lokazlizacje)
        Human[] humans = new Human[NUM_HUMANS2];
        Animal[] animals = new Animal[NUM_ANIMALS];
        for (int i = 0; i < NUM_HUMANS; i++) {
            humans[i] = new Human(rand.nextDouble(), rand.nextDouble());
        }
        for (int i = 0; i < NUM_ANIMALS; i++) {
            animals[i] = new Animal(rand.nextDouble(), rand.nextDouble());
        }



        // jeden człowiek i jedno zwierzę jest na początku zakażone
        humans[0].setHealthStatus(HealthStatus.ILL);
        humans[0].got_ill(TIME_OF_ILLNESS);
        animals[0].setHealthStatus(HealthStatus.ILL);
        animals[0].got_ill(TIME_OF_ILLNESS);

        Random random = new Random();
        illHumans.add(IllHumans);

        // Create the visualization panel
        EpidemicVisualization panel = new EpidemicVisualization(humans, animals);

        // Create the frame and add the panel
        JFrame frame = new JFrame();
        frame.setSize(EpidemicVisualization.WIDTH+145, EpidemicVisualization.HEIGHT+45);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        // Symulacja poruszania się obiektów i zarażania, aktualizacja co chwilę
        Timer timer = new Timer(ITERATION_DURATION_MILLISECONDS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x++;
                illHumans.add(IllHumans);
                panel.repaint();
                //poruszanie się
                for (Human human : humans) {
                    if (human.getHealthStatus()!= HealthStatus.DEAD){
                        double dx = random.nextDouble() * 2 - 1;
                        double dy = random.nextDouble() * 2 - 1;
                        human.setX(human.getX() + dx * MOBILITY);
                        human.setY(human.getY() + dy * MOBILITY);}
                }
                for (Animal animal : animals) {
                    if (animal.getHealthStatus()!= HealthStatus.DEAD){
                        double dx = random.nextDouble() * 2 - 1;
                        double dy = random.nextDouble() * 2 - 1;
                        animal.setX(animal.getX() + dx * MOBILITY);
                        animal.setY(animal.getY() + dy * MOBILITY);}
                }
                // zarażanie się ludzi
                for (int i = 0; i < NUM_HUMANS; i++) {
                    Human human1 = humans[i];
                    for (int j = i + 1; j < NUM_HUMANS; j++) {
                        Human human2 = humans[j];
                        double distance = Math.sqrt(Math.pow(human1.getX() - human2.getX(), 2) +
                                Math.pow(human1.getY() - human2.getY(), 2));

                        if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROPABLITY && human2.getHealthStatus()==HealthStatus.ILL && human1.getHealthStatus()==HealthStatus.HEALTHY) {
                            human1.setHealthStatus(HealthStatus.ILL);
                            human1.got_ill(TIME_OF_ILLNESS);
                        }
                        if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROPABLITY && human1.getHealthStatus()==HealthStatus.ILL && human2.getHealthStatus()==HealthStatus.HEALTHY) {
                            human2.setHealthStatus(HealthStatus.ILL);
                            human2.got_ill(TIME_OF_ILLNESS);
                        }
                    }
                }
                // zarażanie się zwierząt
                for (int i = 0; i < NUM_ANIMALS; i++) {
                    Animal animal1 = animals[i];
                    for (int j = i + 1; j < NUM_ANIMALS; j++) {
                        Animal animal2 = animals[j];
                        double distance = Math.sqrt(Math.pow(animal1.getX() - animal2.getX(), 2) +
                                Math.pow(animal1.getY() - animal2.getY(), 2));
                        if (distance < 0.05 && random.nextDouble() <= ANIMAL_ILLNESS_PROPABILITY && animal2.getHealthStatus()==HealthStatus.ILL && animal1.getHealthStatus()==HealthStatus.HEALTHY) {
                            animal1.setHealthStatus(HealthStatus.ILL);
                            animal1.got_ill(TIME_OF_ILLNESS);
                        }
                        if (distance < 0.05 && random.nextDouble() <= ANIMAL_ILLNESS_PROPABILITY && animal1.getHealthStatus()==HealthStatus.ILL && animal2.getHealthStatus()==HealthStatus.HEALTHY) {
                            animal2.setHealthStatus(HealthStatus.ILL);
                            animal2.got_ill(TIME_OF_ILLNESS);
                        }
                    }
                }


            }
        });
        timer.start();
        Timer Dni = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Day++;
            }
        });
        Dni.start();
        //Co jakiś czas ludzie jedzą 1/5 zwierząt

        for (int i = 0;i<1000;i++){

            //dzieje się to co 5 sekund
            try
            {
                Thread.sleep(5000);
            }
            catch(InterruptedException ex)
            {
                ex.printStackTrace();
            }

            // Sprawdzam ile zwierząt jest chorych i ile zdrowych
            for (int j = 0; j < NUM_ANIMALS; j++) {
                if (animals[j].getHealthStatus()==HealthStatus.HEALTHY || animals[j].getHealthStatus()==HealthStatus.CONVALESCENT){
                    healthyAnimals.add(animals[j]);}
                else if (animals[j].getHealthStatus()==HealthStatus.ILL) {
                    illAnimals.add(animals[j]);
                }
                else if (animals[j].getHealthStatus()==HealthStatus.DEAD) {
                    deadAnimals.add(animals[j]);
                }
            }


            int numEaten = (int) ((healthyAnimals.size()+illAnimals.size()) / 5.0);
            if (numEaten>0) {
                // Wybieramy 1/5 zwierząt do zjedzenia, przy czym ludzie wybierają zdrowe z danym prawdopodobieństwem
                Animal[] animals_to_eat = new Animal[numEaten];
                for (int j = 0; j < numEaten; j++) {
                    double x = random.nextDouble();
                    if ((x < HEALTHY_ANIMAL_PROPABILITY && healthyAnimals.size() > 0) || illAnimals.size() == 0) {
                        animals_to_eat[j] = healthyAnimals.get(random.nextInt(healthyAnimals.size()));
                    } else {
                        animals_to_eat[j] = illAnimals.get(random.nextInt(illAnimals.size()));
                    }
                }
                //Ludzie jedzą zwierzęta i mogą się zarazić
                for (Human human : humans) {
                    if (human.getHealthStatus() == HealthStatus.HEALTHY) {
                        if (animals_to_eat[random.nextInt(numEaten)].getHealthStatus() == HealthStatus.ILL) {
                            human.setHealthStatus(HealthStatus.ILL);
                            human.got_ill(TIME_OF_ILLNESS);
                        }
                    }
                }
                // Zjedzone zwierzęta umierają
                for (Animal animal : animals_to_eat) {
                    animal.setHealthStatus(HealthStatus.DEAD);
                }
            }
            //wyświetlałem w konsoli ile jest chorych a ile zdrowych, może się przydać jeszcze
            //System.out.println("Zdrowe zwierzęta:");
            //System.out.println(healthyAnimals.size());
            //System.out.println("Martwe zwierzęta");
            //System.out.println(deadAnimals.size());

            healthyAnimals.clear();
            illAnimals.clear();
            deadAnimals.clear();

        }
    }
}


