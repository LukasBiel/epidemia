import java.util.ArrayList;
import java.util.List;

public abstract class Symulacja {

    public static double MOBILITY = 0.007;
    public static double ANIMAL_ILLNESS_PROPABILITY = 0.8;
    public static double HUMAN_ILLNESS_PROBABLITY = 0.8;
    public static double HEALTHY_ANIMAL_PROPABILITY = 0.99;

    static List<Animal> healthyAnimals = new ArrayList<>();
    static List<Animal> illAnimals = new ArrayList<>();
    static List<Animal> deadAnimals = new ArrayList<>();

}
