import javax.swing.*;

public class Frame extends JFrame{

    public static final int Width = 1920;
    public static final int Height = 1080;
    GameScene gameScene = new GameScene();
    public Frame(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setSize(Width,Height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(gameScene);
    }
    public void showFrame(){
        this.setVisible(true);
    }
}
