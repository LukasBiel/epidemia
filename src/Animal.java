import javax.swing.*;
import java.util.Random;

public class Animal {
    private HealthStatus health;
    private double x;
    private double y;

    public Animal(double x, double y) {
        this.health = HealthStatus.HEALTHY;
        this.x = x;
        this.y = y;
    }

    public HealthStatus getHealthStatus() {
        return health;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.health = healthStatus;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x > 1) {
            this.x = 1;
        } else if (x < 0) {
            this.x = 0;

        } else {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y > 1) {
            this.y = 1;
        } else if (y < 0) {
            this.y = 0;

        } else {
            this.y = y;
        }
    }
    public void got_ill(int time) {
        Random random = new Random();
        javax.swing.Timer timer2 = new Timer(time, e -> {
            double y = random.nextDouble();
            if (health == HealthStatus.ILL && y <= 0.95) {
                health = HealthStatus.CONVALESCENT;
                got_convalescent(Symulacja.time_of_healing);
            }

            if (health == HealthStatus.ILL && y > 0.95) {
                health = HealthStatus.DEAD;}
        });
        timer2.start();
        timer2.setRepeats(false);

    }
    public void got_convalescent(int time){
        Timer timer3 = new Timer(time, e -> {
            if (health==HealthStatus.CONVALESCENT)
                health = HealthStatus.HEALTHY;
        });
        timer3.start();
        timer3.setRepeats(false);

    }
}