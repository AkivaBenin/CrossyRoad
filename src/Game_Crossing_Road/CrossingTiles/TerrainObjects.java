package Game_Crossing_Road.CrossingTiles;

import Game_Crossing_Road.Obstacles.Obstacles;
import Game_Crossing_Road.Screens.Scene;
import Game_Crossing_Road.Screens.Window;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class TerrainObjects extends JPanel {
    private static int tileOutOfScreen = 100;
    public static final int TILE_HEIGHT = (Window.FRAME_HEIGHT / Scene.amountOfTiles) - 2;
    public static final int TILE_WIDTH = Window.FRAME_WIDTH + tileOutOfScreen;
    public static final int TILE_X = -(tileOutOfScreen / 2);
    protected int y;

    public abstract void paint(Graphics g);
    public abstract void deleteObstacles();

    public TerrainObjects (int y){
        this.y = y;
        setBounds(TILE_X,y,TILE_WIDTH,TILE_HEIGHT);
    }

    public void moveDown(){
        this.y ++;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paint(g);
    }

    public int getY(){
        return this.y;
    }

    public abstract ArrayList<Obstacles> getObstacles();

}
