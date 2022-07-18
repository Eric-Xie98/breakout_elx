package breakout;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;



public class Paddle {

  public static final Paint PADDLE_COLOR = Color.PLUM;
  public static final double PADDLE_SIZE = 20;
  private static final double PADDLE_HEIGHT = PADDLE_SIZE / 4;


  public final Rectangle leftPaddle;
  public final Rectangle middlePaddle;
  public final Rectangle rightPaddle;
  private int MOVER_VELOCITY = 0;
  private boolean LEFT_KEY_PRESSED = false;
  private boolean RIGHT_KEY_PRESSED = false;
  private final int originX;
  private final int originY;

  /**
   *
   * @author Eric L. Xie
   *
   * The Paddle class holds the constructor for creating the player-controlled Paddle and the
   * instance variables that are on each paddle. This paddle is made of 3 rectangles, which allows
   * us to control what happens when the ball hits each, and they are named accordingly: left,
   * middle, and right paddle. This class also contains the methods for moving the paddle and toggles
   * that allow the paddle to move smoother than it would normally.
   *
   * Used to create Paddle objects for players to move and bounce the ball back
   *
   * @param x the x coordinate that you want the paddle to be placed at
   * @param y the y coordinate that you want the paddle to be placed at
   * @param root the Group where you place and remove objects from the scene
   *
   */

  public Paddle(int x, int y, Group root) {

    leftPaddle = new Rectangle(x - 3 * PADDLE_SIZE / 2, y, PADDLE_SIZE, PADDLE_HEIGHT);
    middlePaddle = new Rectangle(x - PADDLE_SIZE / 2, y, PADDLE_SIZE, PADDLE_HEIGHT);
    rightPaddle = new Rectangle(x + PADDLE_SIZE / 2, y, PADDLE_SIZE, PADDLE_HEIGHT);


    originX = x;
    originY = y;


    leftPaddle.setFill(PADDLE_COLOR);
    middlePaddle.setFill(PADDLE_COLOR);
    rightPaddle.setFill(PADDLE_COLOR);

    root.getChildren().add(leftPaddle);
    root.getChildren().add(middlePaddle);
    root.getChildren().add(rightPaddle);

  }

  /**
   *
   * public method that moves the paddle if the left and right arrow keys are pressed down and
   * alters the paddle_velocity and toggles
   *
   * used to move the paddle
   *
   * @param code the key inputted by the player, will be checked with the left and right arrows
   *
   */

  public void onKeyInput(KeyCode code) {

    if (code == KeyCode.RIGHT) {
      RIGHT_KEY_PRESSED = true;
      MOVER_VELOCITY = 8;
    }
    if (code == KeyCode.LEFT) {
      LEFT_KEY_PRESSED = true;
      MOVER_VELOCITY = -8;
    }

  }

  /**
   *
   * public method that stops the paddle if the left and right arrow keys are released and
   * alters the paddle_velocity and toggles accordingly
   *
   * used to stop the paddle
   *
   * @param code the key inputted by the player, will be checked with the left and right arrows
   *
   */

  public void onKeyRelease(KeyCode code) {

    if (code == KeyCode.RIGHT) {
      RIGHT_KEY_PRESSED = false;
    }
    if (code == KeyCode.LEFT) {
      LEFT_KEY_PRESSED = false;
    }

  }

  /**
   *
   * the move function of the paddle that controls the instance variables of MOVER_VELOCITY and
   * control statements for certain key combos being pressed
   *
   * for example, if the left and right arrow keys are pressed down at the same time, the paddle
   * does not move anywhere
   *
   * this also moves all the paddle components (left, middle, and right) whenever a key is pressed
   *
   * the method is used in the step function as the key inputs are checked every iteration
   *
   */

  public void move() {

    if (LEFT_KEY_PRESSED && RIGHT_KEY_PRESSED) {

      MOVER_VELOCITY = 0;

    }

    if (LEFT_KEY_PRESSED || RIGHT_KEY_PRESSED) {
      leftPaddle.setX(leftPaddle.getX() + MOVER_VELOCITY);
      middlePaddle.setX(middlePaddle.getX() + MOVER_VELOCITY);
      rightPaddle.setX(rightPaddle.getX() + MOVER_VELOCITY);
    }
  }

  /**
   *
   * the method resets the paddle to the original position that it started in the first level
   *
   * sets all combinations of the paddle (left, middle, and right) to their respective positions
   *
   * the purpose of this is for use in resetting the level and for the cheat key reset
   *
   */

  public void resetPaddle(){

    leftPaddle.setX(originX - 3 * PADDLE_SIZE / 2);
    leftPaddle.setY(originY);
    middlePaddle.setX(originX - PADDLE_SIZE / 2);
    middlePaddle.setY(originY);
    rightPaddle.setX(originX + PADDLE_SIZE / 2);
    rightPaddle.setY(originY);

  }

}
