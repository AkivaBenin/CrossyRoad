package Game_Crossing_Road.Player;
import Game_Crossing_Road.Misc.Sounds;
import Game_Crossing_Road.Screens.Scene;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private Character character;
    private Scene scene;
    private boolean canJump = true;

    public Controller(Character character, Scene scene) {
        this.character = character;
        this.scene = scene;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (Scene.running && Scene.gameStarted) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP , KeyEvent.VK_SPACE:
                    if (canJump) {
                        jumpUp();
                        Sounds.jumping();
                        Scene.score.increaseScore(1);
                        updateScore();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    character.moveDown();
                    Scene.score.decreaseScore(1);
                    break;
                case KeyEvent.VK_LEFT:
                    character.moveLeft();
                    break;
                case KeyEvent.VK_RIGHT:
                    character.moveRight();
                    break;
                default:
                    System.out.println("Wrong button");
            }
            this.scene.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void jumpUp() {
        character.moveUp();
        canJump = false;
        new javax.swing.Timer(Character.ANIMATION_TIME, e -> {
            canJump = true;
            ((javax.swing.Timer) e.getSource()).stop();
        }).start();
    }

    private void updateScore() {
        Scene.score.updateScore();
        Scene.scoreLabel.setText("Score: " + Scene.score.getScore());
    }
}

