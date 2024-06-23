package Game_Crossing_Road.Obstacles;

import Game_Crossing_Road.CrossingTiles.TerrainObjects;
import Game_Crossing_Road.Misc.RandomImages;
import Game_Crossing_Road.Player.Character;
import Game_Crossing_Road.Screens.Scene;
import javax.swing.*;
import java.awt.*;

public class Train extends Obstacles {
    private int x;
    private int y;
    private ImageIcon trainImage;
    private Boolean fromLeft;
    public static int trainLength;
    public static final int TRAIN_HEIGHT = TerrainObjects.TILE_HEIGHT;
    public static final int TRAIN_SPEED = 2 + Scene.amountOfTiles / 5;

    public Train(int y, boolean fromLeft) {
        this.y = y;
        this.fromLeft = fromLeft;
        trainImage = new ImageIcon(RandomImages.train());
        trainLength = trainImage.getIconWidth();
        initializeTrain();
    }

    private void initializeTrain() {
        if (fromLeft) {
            this.x = TerrainObjects.TILE_X;
        } else {
            this.x = TerrainObjects.TILE_WIDTH - trainLength;
        }
    }

    public void move() {
        if (fromLeft) {
            this.x += TRAIN_SPEED;
            if (this.x > TerrainObjects.TILE_WIDTH) {
                this.x = TerrainObjects.TILE_X - trainLength;
            }
        } else {
            this.x -= TRAIN_SPEED;
            if (this.x < TerrainObjects.TILE_X - trainLength) {
                this.x = TerrainObjects.TILE_WIDTH;
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(trainImage.getImage(), this.x, this.y, trainLength, TRAIN_HEIGHT, null);
    }

    public void updateY(int newY) {
        this.y = newY;
    }

    public Rectangle body() {
        int padding = Character.CHARACTER_SIZE / 2;
        return new Rectangle(this.x + padding, this.y + padding, trainLength - 2 * padding, TRAIN_HEIGHT);
    }

    public boolean collision(Rectangle rect2) {
        return this.body().intersects(rect2);
    }

}
