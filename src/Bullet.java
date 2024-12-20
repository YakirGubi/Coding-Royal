import java.util.Arrays;

public class Bullet {

    protected double[] location;
    protected double[] direction;
    final int radius = 5;
    final double speed = 100;

    Bullet(double[] location, double[] direction) {

        this.location = new double[location.length];
        this.location[0] = location[0];
        this.location[1] = location[1];
        //this.location = Arrays.copyOf(location, location.length);
        this.direction = Arrays.copyOf(direction, direction.length);
    }

    public void move(double deltaTime) {

        this.location[0] = this.location[0] + (speed * deltaTime * this.direction[0]);
        this.location[1] = this.location[1] + (speed * deltaTime * this.direction[1]);
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public double[] getDirection() {
        return direction;
    }

    public int getRadius() {
        return radius;
    }

    public double getSpeed() {
        return speed;
    }
}
