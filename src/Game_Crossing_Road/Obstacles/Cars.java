package Game_Crossing_Road.Obstacles;

import Game_Crossing_Road.CrossingTiles.TerrainObjects;
import Game_Crossing_Road.Misc.RandomImages;
import Game_Crossing_Road.Player.Character;
import Game_Crossing_Road.Screens.Scene;

import javax.swing.*;
import java.awt.*;

public class Cars extends Obstacles {
    private int x;
    private int y;
    private ImageIcon carImageFromRight;
    private ImageIcon carImageFromLeft;
    private Boolean fromLeft;
    public static int carLength = TerrainObjects.TILE_WIDTH / Scene.amountOfTiles * 2;
    public static final int CAR_HEIGHT = TerrainObjects.TILE_HEIGHT ;
    public static final int CAR_SPEED = 2;


    public Cars(int y, boolean fromLeft) {
        this.y = y;
        this.fromLeft = fromLeft;
        carImageFromRight = new ImageIcon(RandomImages.carFromRight());
        carImageFromLeft = new ImageIcon(RandomImages.carFromLeft());
        initializeCar();
    }

    private void initializeCar() {
        if (fromLeft) {
            this.x = TerrainObjects.TILE_X;
        } else {
            this.x = TerrainObjects.TILE_WIDTH;
        }
    }

    public void move() {
        if (fromLeft) {
            this.x+= CAR_SPEED;
            if(this.x > TerrainObjects.TILE_WIDTH){
                this.x = TerrainObjects.TILE_X - carLength;
            }
        } else {
            this.x-= CAR_SPEED;
            if(this.x < TerrainObjects.TILE_X - carLength){
                this.x = TerrainObjects.TILE_WIDTH;
            }
        }
    }

    public void paint(Graphics g) {
        if(fromLeft){
            g.drawImage(carImageFromLeft.getImage(),this.x,this.y, carLength, CAR_HEIGHT, null);
        }else {
            g.drawImage(carImageFromRight.getImage(), this.x, this.y, carLength, CAR_HEIGHT, null);
        }
    }

    public Rectangle body() {
        int padding = Character.CHARACTER_SIZE / 4; // Adjust padding to 1/4 of the character size
        return new Rectangle(this.x + padding, this.y + padding, carLength - 2 * padding, CAR_HEIGHT - 2 * padding);
    }


    public boolean collision(Rectangle rect2) {
        return body().intersects(rect2);
    }
    public void updateY(int newY) {
        this.y = newY;
    }
    public void setX(int x){
        this.x = x;
    }
}