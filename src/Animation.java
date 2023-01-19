import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animation extends Symulacja {
    private final Human[] humans;
    private final Animal[] animals;
    
    // zmienne tylko do animacji - wykres, numer dnia, udział procentowy
    public static int Day = 0;
    public static int argument_wykres = 0;
    static List<Integer> illHumans = new ArrayList<>();
    public static int IllHumans = 0;
    public static double HealthyHumans = EpidemicSimulation.NUM_HUMANS;
    public static double DeadHumans = 0;
    public static final int TIME_OF_ILLNESS = 10000;
    public static final int TIME_OF_MOVEMENT = 100;
    
    public Animation(Human[] humans, Animal[] animals) {
        this.humans = humans;
        this.animals = animals;
    }
    public void animacja(EpidemicVisualization pan){
        humans[0].got_ill(TIME_OF_ILLNESS);
        animals[0].got_ill(TIME_OF_ILLNESS);
        
        Random random = new Random();
        Timer timer = new Timer(TIME_OF_MOVEMENT, e -> {
            argument_wykres++;
            illHumans.add(IllHumans);
            pan.revalidate();
            pan.repaint();
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
            for (int i = 0; i < EpidemicSimulation.NUM_HUMANS; i++) {
                Human human1 = humans[i];
                for (int j = i + 1; j < EpidemicSimulation.NUM_HUMANS; j++) {
                    Human human2 = humans[j];
                    double distance = Math.sqrt(Math.pow(human1.getX() - human2.getX(), 2) +
                            Math.pow(human1.getY() - human2.getY(), 2));

                    if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROBABLITY && human2.getHealthStatus()==HealthStatus.ILL && human1.getHealthStatus()==HealthStatus.HEALTHY) {
                        human1.setHealthStatus(HealthStatus.ILL);
                        human1.got_ill(TIME_OF_ILLNESS);
                    }
                    if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROBABLITY && human1.getHealthStatus()==HealthStatus.ILL && human2.getHealthStatus()==HealthStatus.HEALTHY) {
                        human2.setHealthStatus(HealthStatus.ILL);
                        human2.got_ill(TIME_OF_ILLNESS);
                    }
                }
            }
            // zarażanie się zwierząt
            for (int i = 0; i < EpidemicSimulation.NUM_ANIMALS; i++) {
                Animal animal1 = animals[i];
                for (int j = i + 1; j < EpidemicSimulation.NUM_ANIMALS; j++) {
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


        });
        timer.start();
        Timer Dni = new Timer(1000, e -> Day++);
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
            for (int j = 0; j < EpidemicSimulation.NUM_ANIMALS; j++) {
                if (animals[j].getHealthStatus()==HealthStatus.HEALTHY || animals[j].getHealthStatus()==HealthStatus.CONVALESCENT){
                    healthyAnimals.add(animals[j]);}
                else if (animals[j].getHealthStatus()==HealthStatus.ILL) {
                    illAnimals.add(animals[j]);
                }
                else if (animals[j].getHealthStatus()==HealthStatus.DEAD) {
                    deadAnimals.add(animals[j]);
                }
            }

            // operacja jedzenia zwierząt
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
            healthyAnimals.clear();
            illAnimals.clear();
            deadAnimals.clear();
            //koniec jedzenia
    }
}
}

