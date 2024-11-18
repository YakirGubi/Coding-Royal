import java.util.ArrayList;

public class TankG {

    final int radius = 30;
    protected double[] locationV; // location of the tank (vector of x,y)
    protected double[] directionV; // the direction vector of the tank (vector of x,y)
    protected double HP;
    final int maxHP = 100;
    final int magazine_capacity = 10;
    protected int bullet_amount;
    final double speed = 20; // change later
    final double reload_time = 1;
    protected double time_in_reload;
    protected boolean is_reload;
    final double rotation_speed = (Math.PI / 180) * 100;
    protected double degree;
    protected ArrayList<Bullet> bullets = new ArrayList<>();
    protected int kills;


    public double[] getLocationV() {
        return locationV;
    }

    public double[] getDirectionV() {
        return directionV;
    }

    public double getHP() {
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

    public int getRadius() {return radius;}
    public double getDegree() {
        return degree;
    }
}
