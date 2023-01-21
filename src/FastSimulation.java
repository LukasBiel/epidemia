import java.util.Random;
import java.util.Collections;
public class FastSimulation extends Symulacja {
    private final Human[] humans;
    private final Animal[] animals;
    private boolean koniec = false;
    private int iteracja = 0;
    public static int max;


    public FastSimulation(Human[] humans, Animal[] animals) {
        this.humans = humans;
        this.animals = animals;
    }


    public void fastsimulation() {

        humans[0].setHealthStatus(HealthStatus.ILL);
        animals[0].setHealthStatus(HealthStatus.ILL);
        humans[0].iteracja = iteracja + 100;
        animals[0].iteracja = iteracja + 100;
        IllHumans = 1;

        Random random = new Random();

        while (!koniec) {


            //aktualizacja stanów zdrowia
            for (Human human : humans) {
                human.got_ill2(iteracja);
                human.got_convalescent2(iteracja);
            }
            for (Animal animal : animals) {
                animal.got_ill2(iteracja);
                animal.got_convalescent2(iteracja);
            }
            illHumans.add(IllHumans);
            iteracja++;

            if (iteracja % 10 == 0) {
                Day++;
            }
            for (Human human : humans) {
                if (human.getHealthStatus() != HealthStatus.DEAD) {
                    double dx = random.nextDouble() * 2 - 1;
                    double dy = random.nextDouble() * 2 - 1;
                    human.setX(human.getX() + dx * MOBILITY / 100);
                    human.setY(human.getY() + dy * MOBILITY / 100);
                }
            }
            for (Animal animal : animals) {
                if (animal.getHealthStatus() != HealthStatus.DEAD) {
                    double dx = random.nextDouble() * 2 - 1;
                    double dy = random.nextDouble() * 2 - 1;
                    animal.setX(animal.getX() + dx * MOBILITY / 100);
                    animal.setY(animal.getY() + dy * MOBILITY / 100);
                }
            }
            // zarażanie się ludzi
            for (int i = 0; i < EpidemicSimulation.NUM_HUMANS; i++) {
                Human human1 = humans[i];
                for (int j = i + 1; j < EpidemicSimulation.NUM_HUMANS; j++) {
                    Human human2 = humans[j];
                    double distance = Math.sqrt(Math.pow(human1.getX() - human2.getX(), 2) +
                            Math.pow(human1.getY() - human2.getY(), 2));

                    if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROBABLITY && human2.getHealthStatus() == HealthStatus.ILL && human1.getHealthStatus() == HealthStatus.HEALTHY) {
                        human1.setHealthStatus(HealthStatus.ILL);
                        Symulacja.IllHumans++;
                        human1.iteracja = iteracja + 100;
                    }
                    if (distance < 0.05 && random.nextDouble() <= HUMAN_ILLNESS_PROBABLITY && human1.getHealthStatus() == HealthStatus.ILL && human2.getHealthStatus() == HealthStatus.HEALTHY) {
                        human2.setHealthStatus(HealthStatus.ILL);
                        Symulacja.IllHumans++;
                        human2.iteracja = iteracja + 100;
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
                    if (distance < 0.05 && random.nextDouble() <= ANIMAL_ILLNESS_PROPABILITY && animal2.getHealthStatus() == HealthStatus.ILL && animal1.getHealthStatus() == HealthStatus.HEALTHY) {
                        animal1.setHealthStatus(HealthStatus.ILL);
                        animal1.iteracja = iteracja + 100;
                    }
                    if (distance < 0.05 && random.nextDouble() <= ANIMAL_ILLNESS_PROPABILITY && animal1.getHealthStatus() == HealthStatus.ILL && animal2.getHealthStatus() == HealthStatus.HEALTHY) {
                        animal2.setHealthStatus(HealthStatus.ILL);
                        animal2.iteracja = iteracja + 100;
                    }
                }
            }
            //Co jakiś czas ludzie jedzą 1/5 zwierząt
            if (iteracja % 50 == 0) {
                for (int j = 0; j < EpidemicSimulation.NUM_ANIMALS; j++) {
                    if (animals[j].getHealthStatus() == HealthStatus.HEALTHY || animals[j].getHealthStatus() == HealthStatus.CONVALESCENT) {
                        healthyAnimals.add(animals[j]);
                    } else if (animals[j].getHealthStatus() == HealthStatus.ILL) {
                        illAnimals.add(animals[j]);
                    } else if (animals[j].getHealthStatus() == HealthStatus.DEAD) {
                        deadAnimals.add(animals[j]);
                    }
                }

                // operacja jedzenia zwierząt
                int numEaten = (int) ((healthyAnimals.size() + illAnimals.size()) / 5.0);
                if (numEaten > 0) {
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
                                Symulacja.IllHumans++;
                                human.iteracja = iteracja + 100;
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
            if (IllHumans == 0) {
                max = Collections.max(illHumans);
                koniec = true;
            }

        }
    }
}


