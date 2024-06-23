package Game_Crossing_Road.Screens;

import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Screens.MainMenu.ButtonsPanel;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GameOverWindow extends JDialog {
    private int highScore;
    private Window window;
    private int currentScore;

    public GameOverWindow(JFrame parent, int currentScore, Window window) {
        super(parent, "Game Over", true);
        this.window = window;
        this.currentScore = currentScore;
        this.highScore = loadHighScore();

        if (currentScore > highScore) {
            saveHighScore(currentScore);
            highScore = currentScore;
        }

        setLayout(new BorderLayout());
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 160));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setOpaque(false);

        contentPane.add(Box.createVerticalStrut(20));

        JLabel scoreLabel = new JLabel("Score: " + currentScore, SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 30));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(scoreLabel);

        contentPane.add(Box.createVerticalStrut(10));

        JLabel highScoreLabel = new JLabel("High Score: " + highScore, SwingConstants.CENTER);
        highScoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        highScoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(highScoreLabel);
        contentPane.add(Box.createVerticalStrut(20));

        ButtonsPanel buttonsPanel = new ButtonsPanel(window, 0, 0);
        JButton restartButton = buttonsPanel.createButton("Restart Game");
        restartButton.addActionListener(e -> {
            Sounds.buttonPressed();
            dispose();
            window.startGame();
        });
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(restartButton);
        contentPane.add(Box.createVerticalStrut(10));
        JButton quitButton = buttonsPanel.createButton("Quit Game");
        quitButton.addActionListener(e -> {
            Sounds.buttonPressed();
            dispose();
            System.exit(0);
        });

        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPane.add(quitButton);
        contentPane.add(Box.createVerticalStrut(10));
        add(contentPane, BorderLayout.CENTER);
    }

    private int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }

    private void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
