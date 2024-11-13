public class Zone {

    protected double[] location;
    protected double radius;
    protected boolean isShrinking;
    protected double step;
    protected double zoneTime;
    protected double waitingZoneTime;
    protected double endOfZonTime;
    protected double endOfWaitingZone;

    Zone() {
        location = new double[2];
        location[0] = 770;
        location[1] = 430;
        step = 20;
        radius = 1000;
        isShrinking = true;
        endOfZonTime = 5;
        zoneTime = 0;
        endOfWaitingZone = 4;
        waitingZoneTime = 0;
    }
    public void move_zone(double deltaTime) {

        if (zoneTime < endOfZonTime) {
            isShrinking = true;
            zoneTime += deltaTime;
        } else if (waitingZoneTime < endOfWaitingZone) {
            isShrinking = false;
            waitingZoneTime += deltaTime;
        } else {
            //isShrinking = false;
            zoneTime = 0;
            waitingZoneTime = 0;
        }

        if (isShrinking) {
            radius -= (step * deltaTime);
        }
    }
}
