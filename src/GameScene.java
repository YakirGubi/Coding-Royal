import javax.swing.*;
import java.awt.*;

public class GameScene extends JPanel {
    double DeltaTime = 0;
    long oldTime = System.nanoTime();
    long newTime = oldTime;

    final int numberOfPlayers = 8;
    TankG[] tankGS = new TankG[numberOfPlayers];
    TankS[] tankSS = new TankS[numberOfPlayers];
    Inputs[] inputs = new Inputs[numberOfPlayers];

    Zone zone = new Zone();

    public GameScene(){
        for(double i = 0 ; i < numberOfPlayers ; i++){
            int diameter = 300;
            int distanceFromTheWall = 400;
            double[] location = {distanceFromTheWall + Math.sin(-Math.PI/2 + Math.PI*(i/4)) * diameter, distanceFromTheWall + Math.cos(-Math.PI/2 + Math.PI*((i)/4)) * diameter};
            double degree = -i/numberOfPlayers * 2*Math.PI;
            tankSS[(int) i] = new TankS(location, degree);
            tankGS[(int) i] = tankSS[(int) i];
            inputs[(int) i] = new Inputs(1,1,false, true);
        }

        mainGameLoop();
    }
    public void mainGameLoop(){
        new Thread(()->{
            while (true){
                newTime = System.nanoTime();
                DeltaTime = (newTime - oldTime) / Math.pow(10,9);
                oldTime = newTime;

                zone.move_zone(DeltaTime);

                for(int i = 0 ; i < numberOfPlayers ; i++){
                    tankSS[i].doMove(inputs[i], DeltaTime);
                }
                tankGS = tankSS;

                for(int i = 0 ; i < numberOfPlayers ; i++){
                    for(Bullet bullet : tankSS[i].bullets){
                        for(int j = 0 ; j < numberOfPlayers ; j++){
                            if(i == j) continue;
                            double distance = Math.sqrt(Math.pow(tankSS[j].locationV[0] - bullet.getLocation()[0], 2) + Math.pow(tankSS[j].locationV[1] - bullet.getLocation()[1], 2));
                            if(distance < (tankSS[j].radius + bullet.getRadius())){
                                tankSS[j].took_a_hit(10);
                                System.out.println("Hit");
                            }
                        }
                    }
                    double distanceX = Math.abs(tankSS[i].getLocationV()[0] - zone.location[0]);
                    double distanceY = Math.abs(tankSS[i].getLocationV()[1] - zone.location[1]);
                    if(distanceX > zone.radius || distanceY > zone.radius ){
                        tankSS[i].took_a_hit(1*DeltaTime);
                    }
                }

                repaint();
            }
        }).start();
    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setColor(Color.PINK);
        graphics.fillRect(0,0,1920,1080);

        graphics.setColor(Color.BLACK);
        graphics.fillOval((int)(zone.location[0] - zone.radius), (int)(zone.location[1] - zone.radius), (int)zone.radius*2, (int)zone.radius*2);

        for(TankS tankS : tankSS){
            if(tankS.getHP() > 0) {
                graphics.setColor(Color.YELLOW);
                graphics.fillOval((int)(tankS.getLocationV()[0] - tankS.radius), (int)(tankS.getLocationV()[1] - tankS.radius), tankS.radius*2, tankS.radius*2);

                graphics.setColor(Color.BLACK);
                double radius = 0.7 * tankS.radius;

                int[] X_Turret = {(int)Math.round(tankS.getLocationV()[0] + Math.cos(tankS.degree + 3*Math.PI/4) * radius),
                                  (int)Math.round(tankS.getLocationV()[0] + Math.cos(tankS.degree + 0.1*Math.PI) * radius),
                                  (int)Math.round(tankS.getLocationV()[0] + Math.cos(tankS.degree - 0.1*Math.PI) * radius),
                                  (int)Math.round(tankS.getLocationV()[0] + Math.cos(tankS.degree - 3*Math.PI/4) * radius)};
                int[] Y_Turret = {(int)Math.round(tankS.getLocationV()[1] + Math.sin(tankS.degree + 3*Math.PI/4) * radius),
                                  (int)Math.round(tankS.getLocationV()[1] + Math.sin(tankS.degree + 0.1*Math.PI) * radius),
                                  (int)Math.round(tankS.getLocationV()[1] + Math.sin(tankS.degree - 0.1*Math.PI) * radius),
                                  (int)Math.round(tankS.getLocationV()[1] + Math.sin(tankS.degree - 3*Math.PI/4) * radius)};
                graphics.fillPolygon(X_Turret, Y_Turret, 4);
            }
            graphics.setColor(Color.RED);
            for(Bullet bullet : tankS.bullets){
                graphics.fillOval((int)bullet.getLocation()[0] - bullet.getRadius(), (int)bullet.getLocation()[1] - bullet.getRadius(), bullet.getRadius()*2, bullet.getRadius()*2);
            }
        }

        graphics.setFont(new Font(null, Font.PLAIN,50));
        graphics.setColor(Color.GREEN);
        graphics.drawString("FPS: " + (int)(1/DeltaTime),50,50);
    }
}