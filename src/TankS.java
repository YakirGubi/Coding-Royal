public class TankS extends TankG{

    public TankS(double[] locationV, double[] directionV) {
        this.locationV = locationV;
        this.directionV = directionV;
        this.HP = this.maxHP;
        this.bullet_amount = this.magazine_capacity;
        this.speed = 0;
        this.time_in_reload = 0;
        this.is_reload = false;
    }

    public void setLocationV(double[] locationV) {
        this.locationV = locationV;
    }

    public void setDirectionV(double[] directionV) {
        this.directionV = directionV;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setBullet_amount(int bullet_amount) {
        this.bullet_amount = bullet_amount;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setTime_in_reload(double time_in_reload) {
        this.time_in_reload = time_in_reload;
    }

    public void setIs_reload(boolean is_reload) {
        if (!this.is_reload) this.is_reload = is_reload;
    }
}
