package Game_Crossing_Road.CrossingTiles;

import Game_Crossing_Road.Obstacles.Obstacles;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Forest extends TerrainObjects {
    private static final int GRASS_BLADE_COUNT = 50;
    private static final int GRASS_BLADE_WIDTH = 2;
    private static final int GRASS_BLADE_HEIGHT = 10;

    private final List<GrassBlade> grassBlades;

    public Forest(int y) {
        super(y);
        grassBlades = new ArrayList<>();
        generateGrassBlades();
    }

    @Override
    public ArrayList<Obstacles> getObstacles() {
        return null;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(34, 139, 34));
        g.fillRect(TILE_X, y, TILE_WIDTH, TILE_HEIGHT);
        drawGrassTexture(g);
    }

    @Override
    public void deleteObstacles() {

    }

    private void generateGrassBlades() {
        Random rand = new Random();
        for (int i = 0; i < GRASS_BLADE_COUNT; i++) {
            int x = TILE_X + rand.nextInt(TILE_WIDTH);
            int y = rand.nextInt(TILE_HEIGHT);
            int height = (int) (GRASS_BLADE_HEIGHT * (0.5 + Math.random() * 0.5));
            Color color = new Color(0, 100 + (int) (Math.random() * 155), 0);
            grassBlades.add(new GrassBlade(x, y, height, color));
        }
    }

    private void drawGrassBlade(Graphics g, GrassBlade blade) {
        g.setColor(blade.color);
        g.fillRect(blade.x, y + blade.y, GRASS_BLADE_WIDTH, blade.height);
    }

    private void drawGrassTexture(Graphics g) {
        for (GrassBlade blade : grassBlades) {
            drawGrassBlade(g, blade);
        }
    }

    private static class GrassBlade {
        int x, y, height;
        Color color;

        GrassBlade(int x, int y, int height, Color color) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.color = color;
        }
    }
}
