public class TankG {

    final int width = 50;
    final int height = 50;
    double[] locationV; // location of the tank (vector of x,y)
    double[] directionV; // the direction vector of the tank (vector of x,y)
    int HP;
    final int maxHP = 100;
    final int magazine_capacity = 10;
    int bullet_amount;
    double speed;
    final double reload_time = 1;
    double time_in_reload;
    boolean is_reload;

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
}
