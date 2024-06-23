package Game_Crossing_Road.Screens.MainMenu;

import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Screens.Window;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private Window window;
    public static final int x = 0;
    public static final int y = 50;

    public MainMenu(Window window) {
        this.window = window;
        setLayout(new BorderLayout());

        Sounds.intro();
        JLabel background = new JLabel(new ImageIcon("res/Images/Misc/BackGroundImage.png"));
        background.setLayout(new GridBagLayout());
        add(background, BorderLayout.CENTER);
        ButtonsPanel buttonsPanel = new ButtonsPanel(window, x, y);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(y, 0, 0, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        background.add(buttonsPanel, gbc);
    }
}
