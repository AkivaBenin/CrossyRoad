package Game_Crossing_Road.Player;

import Game_Crossing_Road.CrossingTiles.TerrainObjects;
import Game_Crossing_Road.Screens.Scene;
import Game_Crossing_Road.Screens.Window;

import javax.swing.*;
import java.awt.*;

public class Character {
    private int x;
    private int y;
    private ImageIcon characterImage;
    private ImageIcon jumpingGif;
    private boolean isJumping;
    public static final int ANIMATION_TIME = 590;
    public static final int CHARACTER_SIZE = TerrainObjects.TILE_HEIGHT;

    public Character(int x, int y){
        this.y = y;
        this.x = x;
        this.isJumping = false;
            characterImage = new ImageIcon("res/Images/Character/StandingUpC.png");
            jumpingGif = new ImageIcon("res/Images/Character/JumpingFrong.gif");
    }

    public void moveUp() {
        if (this.y > CHARACTER_SIZE) {
            animateJump();
            this.y -= TerrainObjects.TILE_HEIGHT;
        }
    }

    public void moveDown() {
        if(this.y > Window.FRAME_HEIGHT + CHARACTER_SIZE){
            Scene.running = false;
        }
        this.y += CHARACTER_SIZE;
    }

    public void moveRight() {
        if (this.x < Window.FRAME_WIDTH - CHARACTER_SIZE * 2) {
            this.x += CHARACTER_SIZE;
        }
    }

    public void moveLeft() {
        if (this.x > CHARACTER_SIZE) {
            this.x -= CHARACTER_SIZE;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if(isJumping){
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(jumpingGif.getImage(), x, y, CHARACTER_SIZE, CHARACTER_SIZE, null);
        }else{
            g.drawImage(characterImage.getImage(), x, y, CHARACTER_SIZE, CHARACTER_SIZE, null);
        }
    }
    private void animateJump(){
        isJumping = true;
        new javax.swing.Timer(ANIMATION_TIME, e ->{
            isJumping = false;
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }
    public void moveWithScreen(){
        this.y ++;
    }
    public Rectangle body() {
        return new Rectangle(this.x,this.y, CHARACTER_SIZE,CHARACTER_SIZE);
    }
    public int getY(){
        return this.y;
    }
}
