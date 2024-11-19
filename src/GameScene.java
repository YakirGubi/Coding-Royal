import javax.swing.*;
import java.awt.*;

public class GameScene extends JPanel {
    double DeltaTime = 0;
    long oldTime = System.nanoTime();
    long newTime = oldTime;
    int winner  = -1;

    final int numberOfPlayers = 8;
    TankG[] tankGS = new TankG[numberOfPlayers];
    TankS[] tankSS = new TankS[numberOfPlayers];
    Inputs[] inputs = new Inputs[numberOfPlayers];
    NextMoves nextMoves = new NextMoves();

    Zone zone = new Zone();

    public GameScene(){
        for(double i = 0 ; i < numberOfPlayers ; i++){
            int diameter = 300;
            int distanceFromTheWall = 400;
            double[] location = {distanceFromTheWall + Math.sin(-Math.PI/2 + Math.PI*(i/4)) * diameter, distanceFromTheWall + Math.cos(-Math.PI/2 + Math.PI*((i)/4)) * diameter};
            double degree = -i/numberOfPlayers * 2*Math.PI;
            tankSS[(int) i] = new TankS(location, degree);
            tankGS[(int) i] = tankSS[(int) i];
        }

        mainGameLoop();
    }
    public void mainGameLoop(){
        new Thread(()->{
            while (winner == -1){
                newTime = System.nanoTime();
                DeltaTime = (newTime - oldTime) / Math.pow(10,9);
                oldTime = newTime;

                zone.move_zone(DeltaTime);
                playersInput(nextMoves, tankSS);

                int counter = 0;
                int playerNumber = -1;
                for(int i = 0 ; i < numberOfPlayers ; i++){
                    if(tankSS[i].HP > 0){
                        playerNumber = i;
                    }else{
                        counter++;
                    }
                }
                tankGS = tankSS;
                if(counter == numberOfPlayers){
                    winner = -2;
                }else if(counter == numberOfPlayers - 1){
                    winner = playerNumber;
                }

                for(int i = 0 ; i < numberOfPlayers ; i++){
                    for(int k = 0 ; k < tankSS[i].bullets.size() ; k++){
                        for(int j = 0 ; j < numberOfPlayers ; j++){
                            if(i == j || tankSS[j].HP <= 0) continue;
                            double distance = Math.sqrt(Math.pow(tankSS[j].locationV[0] - tankSS[i].bullets.get(k).getLocation()[0], 2) + Math.pow(tankSS[j].locationV[1] - tankSS[i].bullets.get(k).getLocation()[1], 2));
                            if(distance < (tankSS[j].radius + tankSS[i].bullets.get(k).getRadius())){
                                tankSS[j].took_a_hit(10);
                                System.out.println("Hit");
                                tankSS[i].bullets.remove(k);
                                k--;
                                break;
                            }
                        }
                    }
                    double distance = Math.sqrt(Math.pow(tankSS[i].locationV[0] - zone.location[0], 2) + Math.pow(tankSS[i].locationV[1] - zone.location[1], 2)) + tankSS[i].radius;
                    if(distance > zone.radius && tankSS[i].HP > 0){
                        tankSS[i].took_a_hit(10*DeltaTime);
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

                graphics.setColor(Color.RED);
                for(Bullet bullet : tankS.bullets){
                    graphics.fillOval((int)bullet.getLocation()[0] - bullet.getRadius(), (int)bullet.getLocation()[1] - bullet.getRadius(), bullet.getRadius()*2, bullet.getRadius()*2);
                }

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
        }

        graphics.setFont(new Font(null, Font.PLAIN,50));
        graphics.setColor(Color.GREEN);
        graphics.drawString("FPS: " + (int)(1/DeltaTime),50,50);

        if(winner == -2) graphics.drawString("Tie", 400, 400);
        else if(winner != -1) graphics.drawString("Winner: " + winner, 400, 400);
    }
    public void playersInput(NextMoves nextMoves, TankS[] tankS){
        if(tankS[0].HP > 0) tankS[0].doMove(nextMoves.firstTank(), DeltaTime);
        else tankS[0].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[1].HP > 0) tankS[1].doMove(nextMoves.secondTank(), DeltaTime);
        else tankS[1].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[2].HP > 0) tankS[2].doMove(nextMoves.thirdTank(), DeltaTime);
        else tankS[2].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[3].HP > 0) tankS[3].doMove(nextMoves.fourthTank(), DeltaTime);
        else tankS[3].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[4].HP > 0) tankS[4].doMove(nextMoves.fifthTank(), DeltaTime);
        else tankS[4].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[5].HP > 0) tankS[5].doMove(nextMoves.sixthTank(), DeltaTime);
        else tankS[5].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[6].HP > 0) tankS[6].doMove(nextMoves.seventhTank(), DeltaTime);
        else tankS[6].doMove(new Inputs(0,0,false,false), DeltaTime);
        if(tankS[7].HP > 0) tankS[7].doMove(nextMoves.eighthTank(), DeltaTime);
        else tankS[7].doMove(new Inputs(0,0,false,false), DeltaTime);
    }
}