public class Bullet {

    protected double[] location;
    protected double[] direction;
    final int wight = 10;
    final int height = 10;
    final double speed = 30;

    Bullet(double[] location, double[] direction) {

        this.location = location;
        this.direction = direction;
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

    public int getWight() {
        return wight;
    }

    public int getHeight() {
        return height;
    }

    public double getSpeed() {
        return speed;
    }
}
