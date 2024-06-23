package Game_Crossing_Road.Screens.MainMenu;

import Game_Crossing_Road.Misc.Sounds;

import javax.swing.*;
import java.awt.*;

public class ControlsWindow extends JDialog {
    public ControlsWindow(JFrame parent) {
        super(parent, "Controls", true);
        setLayout(new BorderLayout());
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 200));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setOpaque(false);

        JLabel controlsLabel = new JLabel("<html><div style='text-align: center;" +
                "'>Use Arrow Keys to Move<br/><br/>" +
                "(U can use the space bar to jump)<br/><br/>"
                +
                "Avoid Obstacles &<br/>" +
                "Cross the Road Safely</div></html>", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Serif", Font.BOLD, 20));
        controlsLabel.setForeground(Color.WHITE);
        controlsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(controlsLabel);
        contentPane.add(Box.createVerticalStrut(20));
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Serif", Font.PLAIN, 16));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> {
            Sounds.buttonPressed();
            dispose();
        });
        contentPane.add(closeButton);

        add(contentPane, BorderLayout.CENTER);
    }
}
