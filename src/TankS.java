public class TankS extends TankG{

    public TankS(double[] locationV, double degree) {
        this.locationV = locationV;
        rotate(0);
        this.HP = this.maxHP;
        this.bullet_amount = this.magazine_capacity;
        this.time_in_reload = 0;
        this.is_reload = false;
        this.degree = degree;
    }

    public void reload(double deltaTime, boolean is_reload) {

        if (!this.is_reload) this.is_reload = is_reload;
        
        if (this.is_reload) {
            if (this.time_in_reload < this.reload_time) {
                this.time_in_reload += deltaTime;
            }else {
                this.bullet_amount = this.magazine_capacity;
                this.time_in_reload = 0;
                this.is_reload = false;
            }
        }
    }

    public void took_a_hit() {

        this.HP = this.HP - 10;
    }

    public void rotate(double degree) { // the rotation function

        this.degree += degree * this.rotation_speed;
        this.directionV[0] = Math.cos(this.degree);
        this.directionV[1] = Math.sin(this.degree);
    }

    public void move(double step, double deltaTime) { // the move forward function

        this.locationV[0] = this.locationV[0] + (step * this.speed * deltaTime * this.directionV[0]);
        this.locationV[1] = this.locationV[1] + (step * this.speed * deltaTime * this.directionV[1]);
    }

    public void shot(boolean is_shot) {

        if (is_shot) {
            if (this.bullet_amount > 0) {

                Bullet bullet = new Bullet(this.locationV, this.directionV);
                this.bullets.add(bullet);
                this.bullet_amount--;
            }
        }
    }

    public void doMove(Inputs inputs, double deltaTime) {

        rotate(inputs.rotation_degree);
        move(inputs.step, deltaTime);
        reload(deltaTime, inputs.is_reload);
        shot(inputs.is_shot);

        for (int i = 0; i < this.bullets.size(); i++) {
            this.bullets.get(i).move(deltaTime);
        }
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

    public void setTime_in_reload(double time_in_reload) {
        this.time_in_reload = time_in_reload;
    }
}
