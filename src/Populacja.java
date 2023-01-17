import java.util.Random;

public class Populacja{


    public void Tworzenie_populacji(int NUM_HUMANS,int NUM_ANIMALS){
        Random rand = new Random();
        EpidemicSimulation.humans = new Human[NUM_HUMANS];
        EpidemicSimulation.animals = new Animal[NUM_ANIMALS];

        for (int i = 0; i < NUM_HUMANS; i++) {
            EpidemicSimulation.humans[i] = new Human(rand.nextDouble(), rand.nextDouble());
        }
        for (int i = 0; i < NUM_ANIMALS; i++) {
            EpidemicSimulation.animals[i] = new Animal(rand.nextDouble(), rand.nextDouble());
        }

        // jeden człowiek i jedno zwierzę jest na początku zakażone
        EpidemicSimulation.humans[0].setHealthStatus(HealthStatus.ILL);
        EpidemicSimulation.animals[0].setHealthStatus(HealthStatus.ILL);

    }

}
