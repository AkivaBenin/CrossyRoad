package Game_Crossing_Road.CrossingTiles;

import Game_Crossing_Road.Obstacles.Obstacles;
import Game_Crossing_Road.Obstacles.Train;
import Game_Crossing_Road.Player.Character;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class TrainRails extends TerrainObjects {
    private static final int RAIL_WIDTH = TILE_HEIGHT / 15;
    private static final int TIE_WIDTH = RAIL_WIDTH * 5;
    private static final int TIE_HEIGHT = RAIL_WIDTH * 2;
    private static final int TIE_GAP = 30;
    private static final int BOLT_SIZE = TIE_WIDTH / 10;
    private ArrayList<Obstacles> trains = new ArrayList<>();
    private boolean trainFromLeft;

    public TrainRails(int y) {
        super(y);
        trainFromLeft = new Random().nextBoolean();
        createTrains();
    }

    public void paint(Graphics g) {
        g.setColor(new Color(139, 69, 19));
        g.fillRect(TILE_X, y, TILE_WIDTH, TILE_HEIGHT);
        drawRails(g);
        drawTies(g);
        drawBolts(g);

        synchronized (trains) {
            for (Obstacles obstacle : trains) {
                if (obstacle != null && obstacle instanceof Train) {
                    Train train = (Train) obstacle;
                    train.paint(g);
                    train.move();
                    train.updateY(y);
                }
            }
        }
    }

    private void createTrains() {
        int amountOfTrains = 2;
        for (int i = 0; i < amountOfTrains; i++) {
            Train tempTrain = new Train(y, trainFromLeft);
            synchronized (trains) {
                trains.add(tempTrain);
            }
        }

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    int trainSpawnDelay = calculateTrainSpawnDelay();
                    Thread.sleep(trainSpawnDelay);

                    Train tempTrain = new Train(y, trainFromLeft);
                    synchronized (trains) {
                        trains.add(tempTrain);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        t1.start();
    }

    private int calculateTrainSpawnDelay() {
        return (Train.trainLength * 1000) / Train.TRAIN_SPEED + new Random().nextInt(3000);
    }

    @Override
    public ArrayList<Obstacles> getObstacles() {
        return this.trains;
    }

    @Override
    public void deleteObstacles() {
        synchronized (trains) {
            trains.clear();
        }
    }

    private void drawRails(Graphics g) {
        int rail1Y = y + TILE_HEIGHT / 4;
        int rail2Y = y + (3 * (TILE_HEIGHT / 4));
        g.setColor(Color.GRAY);
        g.fillRect(TILE_X, rail1Y, TILE_WIDTH, RAIL_WIDTH);
        g.fillRect(TILE_X, rail2Y, TILE_WIDTH, RAIL_WIDTH);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(TILE_X, rail1Y, TILE_WIDTH, RAIL_WIDTH);
        g.drawRect(TILE_X, rail2Y, TILE_WIDTH, RAIL_WIDTH);
    }

    private void drawTies(Graphics g) {
        int rail1Y = y + TILE_HEIGHT / 4;
        int rail2Y = y + (3 * (TILE_HEIGHT / 4));
        for (int x = TILE_X; x < TILE_WIDTH; x += TIE_WIDTH + TIE_GAP) {
            g.setColor(new Color(102, 51, 0));
            g.fillRect(x + 5, rail1Y - TIE_HEIGHT / 2 + 5, TIE_WIDTH, TIE_HEIGHT);
            g.fillRect(x + 5, rail2Y - TIE_HEIGHT / 2 + 5, TIE_WIDTH, TIE_HEIGHT);

            g.setColor(new Color(139, 69, 19));
            g.fillRect(x, rail1Y - TIE_HEIGHT / 2, TIE_WIDTH, TIE_HEIGHT);
            g.fillRect(x, rail2Y - TIE_HEIGHT / 2, TIE_WIDTH, TIE_HEIGHT);

            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, rail1Y - TIE_HEIGHT / 2, TIE_WIDTH, TIE_HEIGHT);
            g.drawRect(x, rail2Y - TIE_HEIGHT / 2, TIE_WIDTH, TIE_HEIGHT);
        }
    }

    private void drawBolts(Graphics g) {
        int rail1Y = y + TILE_HEIGHT / 4;
        int rail2Y = y + (3 * (TILE_HEIGHT / 4));
        for (int x = TILE_X; x < TILE_WIDTH; x += TIE_WIDTH + TIE_GAP) {
            g.setColor(Color.BLACK);
            g.fillOval(x + 13, rail1Y - BOLT_SIZE / 2, BOLT_SIZE, BOLT_SIZE);
            g.fillOval(x + TIE_WIDTH - 13 - BOLT_SIZE, rail2Y - BOLT_SIZE / 2, BOLT_SIZE, BOLT_SIZE);

            g.fillOval(x + 5, rail1Y - BOLT_SIZE / 2, BOLT_SIZE, BOLT_SIZE);
            g.fillOval(x + TIE_WIDTH - 5 - BOLT_SIZE, rail2Y - BOLT_SIZE / 2, BOLT_SIZE, BOLT_SIZE);
        }
    }
}
