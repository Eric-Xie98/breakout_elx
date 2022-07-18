package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * @author Eric L. Xie
 *
 * The SpeedBrick subclass contains the methods pertaining to the Ball Speed Powerup Brick and the
 * constructor for creating these kinds of bricks. This class extends the Brick superclass and inherits
 * its instance variables and methods.
 *
 * It automatically has 3 hits before breaking, and the class adds a yellow circle ball icon label in the middle
 * of the brick, indicating it's a powerup.
 *
 * We call this class constructor when we're building SpeedBricks.
 *
 * Its method holds the code to trigger the negative powerup of speeding up the ball.
 */

public class SpeedBrick extends Brick{

  public static int speedBrickHitsLeft = 3;
  public static final double SPEED_BALL_ICON_SIZE = 5;
  private static final Paint SPEED_BALL_ICON_COLOR = Color.YELLOW;

  public final Circle ballIcon;

  /**
   *
   *The SpeedBrick constructor creates SpeedBricks and is used when we're reading the file and creating
   * bricks off of it. It creates a brick using the super() method and assigns the 3HP instance variable to
   * its hitsLeft. Furthermore, it creates the ballIcon circle used as an icon to show that the brick is
   * a powerup.
   *
   * We then add the newly created SpeedBrick to the root.
   *
   * @param x the x coordinate where you want to place the Brick
   * @param y the y coordinate where you want to place the Brick
   * @param root the Group that nodes/objects are added and removed from and displayed on the scene
   *
   */

  public SpeedBrick(int x, int y, Group root) {
    super(x, y, speedBrickHitsLeft, root);

    ballIcon = new Circle(x, y + BRICK_HEIGHT / 2, SPEED_BALL_ICON_SIZE);
    ballIcon.setFill(SPEED_BALL_ICON_COLOR);
    root.getChildren().add(ballIcon);

  }

  /**
   *
   * this method takes the Bouncer ball like the powerup method in BallBrick class and alters it
   *
   * however, this method alters the SPEED_FACTOR instance variable of the ball, speeding up all
   * the bouncers in the class by a factor of 1.5.
   *
   * this is used whenever the SpeedBrick is broken and is detected, causing this code to trigger
   *
   * @param ballArg the ball that broke the SpeedBrick
   *
   */


  public static void speedUp(Bouncer ballArg){

    ballArg.SPEED_FACTOR *= 1.5;

  }
}
