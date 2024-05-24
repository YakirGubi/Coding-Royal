public class Inputs {

    double rotation_degree;
    double step;
    boolean is_reload;
    boolean is_shot;

    public Inputs(double rotation_degree, double step, boolean is_reload, boolean is_shot) {

        if (rotation_degree <= 1 && rotation_degree >= -1) {
            this.rotation_degree = rotation_degree;
        }else if (rotation_degree > 1) {
            this.rotation_degree = 1;
        }else {
            this.rotation_degree = -1;
        }

        if (step <= 1 && step >= -1) {
            this.step = step;
        }else if (step > 1) {
            this.step = 1;
        }else {
            this.step = -1;
        }

        this.is_reload = is_reload;
        this.is_shot = is_shot;
    }
}
