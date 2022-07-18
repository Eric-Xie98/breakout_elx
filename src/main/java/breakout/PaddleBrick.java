package breakout;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * @author Eric L. Xie
 *
 * The PaddleBrick subclass contains the methods pertaining to the Paddle Powerup Brick and the
 * constructor for creating these kinds of bricks. This class extends the Brick superclass and inherits
 * its instance variables and methods.
 *
 * It automatically has 3 hits before breaking, and the class adds a red circle ball icon label in the middle
 * of the brick, indicating it's a powerup.
 *
 * We call this class constructor when we're building PaddleBricks.
 *
 * Its method holds the code to trigger the powerup of increasing the paddle size.
 *
 */

public class PaddleBrick extends Brick{


  public static int paddleBrickHitsLeft = 3;
  public static final double PADDLE_BALL_ICON_SIZE = 5;
  private static final Paint PADDLE_BALL_ICON_COLOR = Color.RED;

  public final Circle ballIcon;

  /**
   *
   *The PaddleBrick constructor creates PaddleBricks and is used when we're reading the file and creating
   * bricks off of it. It creates a brick using the super() method and assigns the 3HP instance variable to
   * its hitsLeft. Furthermore, it creates the ballIcon circle used as an icon to show that the brick is
   * a powerup.
   *
   * We then add the newly created PaddleBrick to the root.
   *
   * @param x the x coordinate where you want to place the Brick
   * @param y the y coordinate where you want to place the Brick
   * @param root the Group that nodes/objects are added and removed from and displayed on the scene
   *
   */

  public PaddleBrick(int x, int y, Group root) {
    super(x, y, paddleBrickHitsLeft, root);

    ballIcon = new Circle(x, y + BRICK_HEIGHT / 2, PADDLE_BALL_ICON_SIZE);
    ballIcon.setFill(PADDLE_BALL_ICON_COLOR);
    root.getChildren().add(ballIcon);

  }

  /**
   *
   * This method holds the code that executes whenever a PaddleBrick is broken, which causes the
   * power up to instantly occur to the paddle. That's why the argument takes in the paddle object
   * and alters its width and shifts the right paddle for symmetry.
   *
   * @param paddle the paddle the player controls to be powered up
   *
   */

  public static void expandPaddle(Paddle paddle){

    paddle.middlePaddle.setWidth(Paddle.PADDLE_SIZE + 20);
    paddle.rightPaddle.setX(paddle.rightPaddle.getX() + 20);

  }



}
