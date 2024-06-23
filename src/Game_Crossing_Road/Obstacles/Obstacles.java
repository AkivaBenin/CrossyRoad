package Game_Crossing_Road.Obstacles;

import Game_Crossing_Road.CrossingTiles.TerrainObjects;

import java.awt.*;

public abstract class Obstacles {
    int FROM_LEFT = TerrainObjects.TILE_X;
    int FROM_RIGHT = TerrainObjects.TILE_X + TerrainObjects.TILE_WIDTH;
    public abstract Rectangle body();
    public abstract boolean collision(Rectangle rect2);
    public abstract void updateY(int newY);
}
