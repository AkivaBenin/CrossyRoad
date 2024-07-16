package Game_Crossing_Road.Screens;

import Game_Crossing_Road.CrossingTiles.Forest;
import Game_Crossing_Road.CrossingTiles.Street;
import Game_Crossing_Road.CrossingTiles.TerrainObjects;
import Game_Crossing_Road.CrossingTiles.TrainRails;
import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Obstacles.Obstacles;
import Game_Crossing_Road.Player.Character;
import Game_Crossing_Road.Player.Controller;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene extends JPanel implements Runnable {
    private Window parent;
    private List<TerrainObjects> tiles = new CopyOnWriteArrayList<>();
    private Character character;
    public static Score score;
    public static JLabel scoreLabel;
    public static boolean gameStarted;
    public static Thread gameThread;
    public static boolean running;
    public static final int amountOfTiles = 15;

    public Scene(Window parent) {
        Sounds.stopLoop();
        this.parent = parent;
        this.setBounds(0, 0, Window.FRAME_WIDTH, Window.FRAME_HEIGHT);
        initializeScore();
        this.setFocusable(true);
        this.requestFocusInWindow();
        Sounds.inGameSound();
        startGame();
        this.addKeyListener(new Controller(this.character));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (TerrainObjects index : tiles) {
            index.paint(g);
        }
        character.paint(g);
    }

    public void initializeScore(){
        score = new Score();
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setBounds(10, 10, 100, 30);
        this.setLayout(null);
        this.add(scoreLabel);
    }

    public TerrainObjects createRandomTile(int y) {
        switch (new Random().nextInt(3)) {
            case 0:
                return new Forest(y);
            case 1:
                return new Street(y);
            case 2:
                return new TrainRails(y);
            default:
                return new Forest(y);
        }
    }

    public void createStartingTiles() {
        int ranForestStart = new Random().nextInt(3, 5);
        int ranStreetStart = new Random().nextInt(4, 7);
        int totalFixedTiles = ranForestStart + ranStreetStart;

        tiles.add(createRandomTile(-TerrainObjects.TILE_HEIGHT));

        for (int i = 0; i < amountOfTiles - totalFixedTiles; i++) {
            TerrainObjects tile = createRandomTile(i * TerrainObjects.TILE_HEIGHT);
            tiles.add(tile);
        }
        for (int i = amountOfTiles - totalFixedTiles; i < amountOfTiles - ranForestStart; i++) {
            tiles.add(new Street(i * TerrainObjects.TILE_HEIGHT));
        }
        for (int i = amountOfTiles - ranForestStart; i < amountOfTiles; i++) {
            tiles.add(new Forest(i * TerrainObjects.TILE_HEIGHT));
        }
    }

    public void moveTilesDown() {
        this.character.moveWithScreen();
        for (TerrainObjects index : tiles) {
            index.moveDown();
        }
        if (tiles.get(tiles.size() - 1).getY() >= Window.FRAME_HEIGHT) {
            tiles.get(tiles.size() - 1).deleteObstacles();
            tiles.remove(tiles.size() - 1);
        }
        if (tiles.get(0).getY() >= 0) {
            tiles.add(0, createRandomTile(-TerrainObjects.TILE_HEIGHT));
        }
    }

    public void checkCollisions() {
        if (isOutOfScreen()) {
            Sounds.gotLost();
            endGame(parent);
            return;
        }
        for (TerrainObjects tile : tiles) {
            if (character.getY() >= tile.getY() && character.getY() < tile.getY() + TerrainObjects.TILE_HEIGHT) {
                ArrayList<Obstacles> obstacles = tile.getObstacles();
                if (obstacles != null) {
                    for (Obstacles obstacle : obstacles) {
                        if (obstacle != null && obstacle.collision(this.character.body())) {
                            if (tile instanceof Street || tile instanceof TrainRails) {
                                Sounds.gotHit();
                                endGame(parent);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void endGame(Window parent) {
        Sounds.stopLoop();
        running = false;
        gameStarted = false;
        SwingUtilities.invokeLater(() -> new GameOverWindow(parent, score.getScore(), parent).setVisible(true));
    }

    public boolean isOutOfScreen(){
        return this.character.getY() < 0 || this.character.getY() > Window.FRAME_HEIGHT - Character.CHARACTER_SIZE;
    }

    @Override
    public void run() {
            while (running) {
                    if(gameStarted) {
                    moveTilesDown();
                    }
                    checkCollisions();
                    repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
    }

    public void startGame() {
        createStartingTiles();
        int initialX = (Window.FRAME_WIDTH / 2) - (Character.CHARACTER_SIZE / 2);
        int initialY = tiles.get(tiles.size() - 3).getY();
        this.character = new Character(initialX, initialY);
        createCountdown();
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void createCountdown() {
        Sounds.countDown();
        JLabel countdownLabel = new JLabel("", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                super.paintComponent(g);
            }
        };
        countdownLabel.setFont(new Font("Serif", Font.BOLD, 48));
        countdownLabel.setForeground(Color.RED);
        countdownLabel.setBounds(Window.FRAME_WIDTH / 2 - 100, 100, 200, 100);

        this.setLayout(null);
        this.add(countdownLabel);
        this.revalidate();
        this.repaint();

        new Thread(() -> {
            try {
                for (int i = 3; i > 0; i--) {
                    countdownLabel.setText(String.valueOf(i));
                    Thread.sleep(1000);
                }
                countdownLabel.setText("Go!");
                Thread.sleep(1000);
                this.remove(countdownLabel);
                gameStarted = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            this.revalidate();
            this.repaint();
        }).start();
    }
}