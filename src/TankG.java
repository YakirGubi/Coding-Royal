import java.util.ArrayList;

public class TankG {

    final int width = 50;
    final int height = 50;
    protected double[] locationV; // location of the tank (vector of x,y)
    protected double[] directionV; // the direction vector of the tank (vector of x,y)
    protected int HP;
    final int maxHP = 100;
    final int magazine_capacity = 10;
    protected int bullet_amount;
    final double speed = 20; // change later
    final double reload_time = 1;
    protected double time_in_reload;
    protected boolean is_reload;
    final double rotation_speed = (Math.PI / 180);
    protected double degree;
    protected ArrayList<Bullet> bullets = new ArrayList<>();


    public double[] getLocationV() {
        return locationV;
    }

    public double[] getDirectionV() {
        return directionV;
    }

    public int getHP() {
        return HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMagazine_capacity() {
        return magazine_capacity;
    }

    public int getBullet_amount() {
        return bullet_amount;
    }

    public double getSpeed() {
        return speed;
    }

    public double getReload_time() {
        return reload_time;
    }

    public double getTime_in_reload() {
        return time_in_reload;
    }

    public boolean isIs_reload() {
        return is_reload;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public double getDegree() {
        return degree;
    }
}
