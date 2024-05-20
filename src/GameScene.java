import javax.swing.*;
import java.awt.*;

public class GameScene extends JPanel {
    double DeltaTime = 0;
    long oldTime = System.nanoTime();
    long newTime = oldTime;
    
    public GameScene(){

        mainGameLoop();
    }
    public void mainGameLoop(){
        new Thread(()->{
            while (true){
                newTime = System.nanoTime();
                DeltaTime = (newTime - oldTime) / Math.pow(10,9);
                oldTime = newTime;
                System.out.println(newTime);
                System.out.println(DeltaTime);



                repaint();
            }
        }).start();
    }

    public void paintComponent(Graphics graphics){

        super.paintComponent(graphics);

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,1920,1080);

        graphics.setFont(new Font(null, Font.PLAIN, 100));
        graphics.setColor(Color.white);
        graphics.drawString("Call Of Duty", 500,540);

        graphics.setFont(new Font(null, Font.PLAIN,50));
        graphics.setColor(Color.green);
        graphics.drawString("FPS: " + (int)(1/DeltaTime),50,50);
    }
}
