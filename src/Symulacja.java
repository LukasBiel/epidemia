import java.util.ArrayList;
import java.util.List;

public abstract class Symulacja {

    public static double MOBILITY = 1;
    public static double ANIMAL_ILLNESS_PROPABILITY = 0.8;
    public static double HUMAN_ILLNESS_PROBABLITY = 0.8;
    public static double HEALTHY_ANIMAL_PROPABILITY = 0.99;
    public static double PROPABILTY_OF_DEATH = 0.05;

    public static int TIME_OF_ILLNESS = 10000;
    public static int TIME_OF_MOVEMENT = 100;
    public static int TIME_OF_A_DAY = 1000;
    public static int TIME_OF_EATING = 5000;
    public static int TIME_OF_HEALING = 20000;

    public static int Day = 0;
    public static int argument_wykres = 0;
    public static double HealthyHumans = EpidemicSimulation.NUM_HUMANS;
    public static int IllHumans = 0;
    public static double DeadHumans = 0;


    static List<Animal> healthyAnimals = new ArrayList<>();
    static List<Animal> illAnimals = new ArrayList<>();
    static List<Animal> deadAnimals = new ArrayList<>();
    static List<Integer> illHumans = new ArrayList<>();

}
