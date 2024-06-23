package Game_Crossing_Road.CrossingTiles;

import Game_Crossing_Road.Obstacles.Cars;
import Game_Crossing_Road.Obstacles.Obstacles;
import Game_Crossing_Road.Player.Character;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Street extends TerrainObjects {
    private static final int LINE_LENGTH = TILE_WIDTH / 15;
    private static final int LINE_WIDTH = TILE_HEIGHT / 30;
    private static final int GAP_SIZE = (TILE_HEIGHT / 2) - LINE_WIDTH;
    private ArrayList<Obstacles> cars = new ArrayList<>();
    private boolean carsFromLeft;

    public Street(int y) {
        super(y);
        carsFromLeft = new Random().nextBoolean();
        createCars();
    }

    private void createCars() {
        int amountOfCars = 2 + new Random().nextInt(2);
        for (int i = 0; i < amountOfCars; i++) {
            Cars tempCar;
            do {
                tempCar = generateRandomCar();
            } while (isOverlapping(tempCar) || !hasEnoughGap(tempCar));
            cars.add(tempCar);
        }
    }

    private boolean hasEnoughGap(Cars newCar) {
        int gap = Character.CHARACTER_SIZE / 2;
        Rectangle newCarBody = newCar.body();
        for (Obstacles car : cars) {
            if (car != null) {
                Rectangle carBody = ((Cars) car).body();
                carBody.grow(gap, 0);
                if (newCarBody.intersects(carBody)) {
                    return false;
                }
            }
        }
        return true;
    }


    private Cars generateRandomCar() {
        int randomX = new Random().nextInt(TILE_WIDTH - Cars.carLength);
        Cars tempCar = new Cars(y, carsFromLeft);
        tempCar.setX(randomX);
        return tempCar;
    }

    private boolean isOverlapping(Cars newCar) {
        Rectangle newCarBody = newCar.body();
        for (Obstacles car : cars) {
            if (car != null && newCarBody.intersects(((Cars) car).body())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Obstacles> getObstacles() {
        return this.cars;
    }

    @Override
    public void deleteObstacles() {
        cars.clear();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(TILE_X, y, TILE_WIDTH, TILE_HEIGHT);
        g.setColor(Color.WHITE);
        drawSideLines(g);

        synchronized (cars) {
            for (Obstacles obstacle : cars) {
                if (obstacle != null && obstacle instanceof Cars) {
                    Cars car = (Cars) obstacle;
                    car.paint(g);
                        car.move();
                    car.updateY(y);
                }
            }
        }
    }

    private void drawSideLines(Graphics g) {
        for (int x = 0; x < TILE_WIDTH; x += LINE_LENGTH + GAP_SIZE) {
            g.fillRect(x, y, LINE_LENGTH, LINE_WIDTH);
            g.fillRect(x, y + (TILE_HEIGHT - LINE_WIDTH), LINE_LENGTH, LINE_WIDTH);
        }
    }
}