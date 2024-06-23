package Game_Crossing_Road.Screens;

import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Screens.MainMenu.MainMenu;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private ImageIcon image = new ImageIcon("res/Images/Misc/IconImage.jpg");
    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 800;

    public Window(){
        Sounds.intro();
        this.setTitle("CrossAndDontDie");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(image.getImage());
        this.setLayout(new BorderLayout());
        this.add(new MainMenu(this));
        this.setVisible(true);
    }

    public void startGame() {
        this.getContentPane().removeAll();
        this.setLayout(null);
        Scene scene = new Scene(this);
        this.add(scene);
        scene.requestFocusInWindow();
        scene.setFocusable(true);
        this.revalidate();
        this.repaint();
    }
}
