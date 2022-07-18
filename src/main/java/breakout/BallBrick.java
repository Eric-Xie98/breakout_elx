package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * @author Eric L. Xie
 *
 * The BallBrick subclass contains the methods pertaining to the Ball Powerup Brick and the
 * constructor for creating these kinds of bricks. This class extends the Brick superclass and inherits
 * its instance variables and methods.
 *
 * It automatically has 3 hits before breaking, and the class adds a white circle ball icon label in the middle
 * of the brick, indicating it's a powerup.
 *
 * We call this class constructor when we're building BallBricks.
 *
 * Its method holds the code to trigger the powerup of increasing the ball.
 *
 */

public class BallBrick extends Brick {

  public static int ballBrickHitsLeft = 3;
  public static final double BRICK_BALL_ICON_SIZE = 5;
  private static final Paint BRICK_BALL_ICON_COLOR = Color.WHITE;


  public final Circle ballIcon;

  /**
   *
   *The BallBrick constructor creates BallBricks and is used when we're reading the file and creating
   * bricks off of it. It creates a brick using the super() method and assigns the 3HP instance variable to
   * its hitsLeft. Furthermore, it creates the ballIcon circle used as an icon to show that the brick is
   * a powerup.
   *
   * We then add the newly created BallBrick to the root.
   *
   * @param x the x coordinate where you want to place the Brick
   * @param y the y coordinate where you want to place the Brick
   * @param root the Group that nodes/objects are added and removed from and displayed on the scene
   *
   */

  public BallBrick(int x, int y, Group root) {
    super(x, y, ballBrickHitsLeft, root);

    ballIcon = new Circle(x, y + BRICK_HEIGHT / 2, BRICK_BALL_ICON_SIZE);
    ballIcon.setFill(BRICK_BALL_ICON_COLOR);
    root.getChildren().add(ballIcon);

  }

  /**
   *
   * this method takes the ball that broke the certain SpeedBrick and speeds it up, and this method
   * is used after the detection that the broken brick was a SpeedBrick
   *
   * @param ball the Bouncer ball that broke the SpeedBrick
   *
   */

  public static void expandBall(Circle ball){

    ball.setRadius(ball.getRadius() * 2);

  }


}
