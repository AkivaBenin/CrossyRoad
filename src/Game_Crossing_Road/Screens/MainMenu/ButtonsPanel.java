package Game_Crossing_Road.Screens.MainMenu;

import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Screens.Window;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ButtonsPanel extends JPanel {
    private Window window;
    private int x, y;

    public ButtonsPanel(Window window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;
        setOpaque(false);
        setLayout(new GridLayout(2, 1, 20, 20)); // 20px gap between buttons
        setBorder(BorderFactory.createEmptyBorder(y, 50, 100, 50));

        JButton startButton = createButton("Start Game");
        startButton.addActionListener(e -> {
            Sounds.buttonPressed();
            window.startGame();
        });
        add(startButton);

        JButton controlsButton = createButton("Controls");
        controlsButton.addActionListener(e -> {
            Sounds.buttonPressed();
            new ControlsWindow(window).setVisible(true);
        });
        add(controlsButton);
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
                super.paintComponent(g2d);
                g2d.dispose();
            }
        };
        button.setFont(new Font("Serif", Font.PLAIN, 20));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBackground(new Color(255, 255, 255, 180));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setBorderPainted(false); // Ensure border painting is turned off
        return button;
    }
}
