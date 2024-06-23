package Game_Crossing_Road.Obstacles;

import java.awt.*;

public class Trees extends Obstacles {
    public Rectangle body (){
        return new Rectangle();
    }

    @Override
    public boolean collision(Rectangle rect2) {
        return false;
    }

    @Override
    public void updateY(int newY) {

    }
}
