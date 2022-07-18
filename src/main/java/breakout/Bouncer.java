package breakout;

import java.util.HashSet;
import java.util.Iterator;
import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Bouncer class contains the constructor to create the Bouncer ball and the instance variables
 * that each Bouncer ball has. For example, each Bouncer has its own direction, which can be toggled
 * depending on whether it bounced off something or not. This class also contains the methods that
 * alter the Bouncer ball and methods that check for intersection with other objects.
 *
 * The Bouncer is made of a Circle shape.
 *
 *
 * @author Eric L. Xie
 *
 */
public class Bouncer {

  public static final int BOUNCER_SIZE = 8;
  public static final int BOUNCER_SPEED = 256;
  public static double SPEED_FACTOR = 1;


  private int dirToggleX;
  private int dirToggleY;
  private final int originX;
  private final int originY;
  private final Circle ball;

  /**
   *
   * The Bouncer constructor constructs the default Bouncer ball which essentially is a Circle
   * Shape, allowing us to utilize methods from the shape and circle class to alter its position,
   * color, size, etc.
   *
   * Used to construct Bouncers / balls that move around the screen
   *
   * @param x the x coordinate of where the ball will be placed
   * @param y the y coordinate of where the ball will be placed
   * @param dx the initial direction of the ball in the x direction
   * @param dy the initial direction of the ball in the y direction
   * @param root the Group where the Bouncer object is added to and shown in the scene
   *
   */

  public Bouncer(int x, int y, int dx, int dy, Group root) {

    ball = new Circle(x, y, BOUNCER_SIZE, Color.WHITE);

    dirToggleX = dx;
    dirToggleY = dy;
    originX = x;
    originY = y;

    root.getChildren().add(ball);
  }

  /**
   * This method checks whether the ball has intersected or hit the screen boundary and accordingly
   * changes the direction of the ball (thus bouncing it) based on which side of the screen it hit
   *
   * However, the bottom of the screen is open and if the ball crosses there, another method takes
   * over and takes away a life.
   *
   * Used to keep ball within screen normally
   *
   * @param width the length of the game screen, used to make calculations of where the ball
   *              should bounce
   *
   */

  public void checkBounce(int width) {

    // if bound detected, flip corresponding toggle, and "bounce" in new direction
    if ((ball.getCenterX() >= width - BOUNCER_SIZE) || (ball.getCenterX() <= BOUNCER_SIZE)) {
      dirToggleX *= -1;
    }
    if (ball.getCenterY() <= BOUNCER_SIZE) {
      dirToggleY *= -1;
    }

  }

  /**
   * This method is the cheat key version of checkBouncer, stopping the ball from bouncing past the
   * bottom part of the screen.
   *
   * Used to keep ball within screen entirely when cheat key is on
   *
   * This prevents the players from losing lives.
   *
   * @param width the length of the game screen
   * @param height how tall the game screen is
   */

  // Looking back at the code, I realized that the checkBounce and bouncerCheat class
  // are incredibly similar, and that I should've combined them into one with an extra boolean parameter
  public void bouncerCheat(int width, int height) {
    // if bound detected, flip corresponding toggle, and "bounce" in new direction
    if ((ball.getCenterX() >= width - BOUNCER_SIZE) || (ball.getCenterX() <= BOUNCER_SIZE)) {
      dirToggleX *= -1;
    }
    if (ball.getCenterY() <= BOUNCER_SIZE || (ball.getCenterY() >= height - BOUNCER_SIZE)) {
      dirToggleY *= -1;
    }

  }

  /**
   * Checks whether the ball has crossed the bottom part of the screen, and if so, the player loses
   * a life and prints a warning of how many lives are remaining
   *
   * The ball is also reset to the middle of the screen and sent flying in a default direction
   * towards the left and upwards
   *
   *
   * @param height how tall the screen is
   * @param livesLeft how many lives the player has left on the level
   *
   */

  public int checkLifeLost(int height, int livesLeft) {
    if(ball.getCenterY() >= height - BOUNCER_SIZE){
      ball.setCenterX(originX);
      ball.setCenterY(originY);
      dirToggleY = -1;
      dirToggleX = -1;
      livesLeft--;
      System.out.println("Just lost a life. " + livesLeft + " remaining.");
      return livesLeft;
    }

    return livesLeft;

  }

  /**
   *
   * This move function does what it says, moving the ball at a constant speed across the screen
   *
   * It also is affected by a number of instance variables including the direction toggle (can
   * change the direction of the ball from bounces) and the SPEED_FACTOR (the powerup that speeds
   * up the ball).
   *
   * @param elapsedTime time variable that factors into how the ball movement is calculated over time
   */

  public void move(double elapsedTime) {

    ball.setCenterX(ball.getCenterX() + BOUNCER_SPEED * elapsedTime * dirToggleX * SPEED_FACTOR);
    ball.setCenterY(ball.getCenterY() + BOUNCER_SPEED * elapsedTime * dirToggleY * SPEED_FACTOR);

  }

  /**
   * Checks whether the ball is intersecting the player controlled paddle and bounces the ball
   * back in a certain way depending on where it hit the paddle
   *
   * If the ball hits the center of the paddle, the ball continues to bounce in the same x direction
   * If the ball hits the left edge, it bounces back in the left direction.
   * If the ball hits the right edge, it bounces back in the right direction.
   *
   *
   *
   * @param paddleArg the paddle the player is using
   */

  public void isIntersectingPaddle(Paddle paddleArg) {
    if (paddleArg.middlePaddle.getBoundsInLocal().intersects(ball.getBoundsInLocal())) {
      dirToggleY *= -1;
    } else if (paddleArg.leftPaddle.getBoundsInLocal().intersects(ball.getBoundsInLocal())) {
      dirToggleY *= -1;
      if (dirToggleX == 0) {
        dirToggleX = -1;
      } else if (dirToggleX > 0) {
        dirToggleX *= -1;
      }
    } else if (paddleArg.rightPaddle.getBoundsInLocal().intersects(ball.getBoundsInLocal())) {
      dirToggleY *= -1;
      if (dirToggleX == 0) {
        dirToggleX = 1;
      } else if (dirToggleX < 0) {
        dirToggleX *= -1;
      }
    }
  }

  /**
   *
   * checks whether the Ball is intersecting a brick in the current list of Brick objects
   *
   * because the brick is created with 4 lines, we can check the intersection of the brick from
   * 4 directions and bounce the ball back accordingly based on which line it intersected
   *
   * furthermore, if the brick is broken by the ball (checks if the brick hits left == 0), breaks
   * the brick accordingly based on its type and removes it from the list of bricks
   *
   * NOTE: Coding over this, I realize that this method is a SUPER method full of code smells, and
   * dividing this up into succinct methods would make it far more readable and less smelly
   *
   * @param bricks HashSet of Brick objects that are currently in the level
   * @param ballArg the Bouncer ball that is being checked
   * @param paddle the paddle the player is controlling
   * @param root the Group that contains the objects displayed in the scene
   *
   */

  public void isIntersectingBrick(HashSet<Brick> bricks, Bouncer ballArg, Paddle paddle, Group root) {

    Iterator<Brick> it = bricks.iterator();

    while (it.hasNext()) {

      Brick brickArg = it.next();

      boolean intersectTop = brickArg.topLine.getBoundsInLocal()
          .intersects(ball.getBoundsInLocal());
      boolean intersectBottom = brickArg.bottomLine.getBoundsInLocal()
          .intersects(ball.getBoundsInLocal());
      boolean intersectLeft = brickArg.leftLine.getBoundsInLocal()
          .intersects(ball.getBoundsInLocal());
      boolean intersectRight = brickArg.rightLine.getBoundsInLocal()
          .intersects(ball.getBoundsInLocal());

      if (intersectTop) {
        dirToggleY *= -1;
        brickArg.hitsLeft--;
      } else if (intersectBottom) {
        dirToggleY *= -1;
        brickArg.hitsLeft--;
      } else if (intersectLeft) {
        dirToggleX *= -1;
        brickArg.hitsLeft--;
      } else if (intersectRight) {
        dirToggleX *= -1;
        brickArg.hitsLeft--;
      }

      if (Brick.brickBroken(brickArg)) {
        // put code here that checks what kind of brick is broken
        if(brickArg instanceof BallBrick){
          BallBrick.expandBall(ball);
        }
        else if(brickArg instanceof PaddleBrick){
          PaddleBrick.expandPaddle(paddle);
        }
        else if(brickArg instanceof SpeedBrick){
          SpeedBrick.speedUp(ballArg);
        }
        Brick.removeBrick(brickArg, root);
        it.remove();
      } else {
        Brick.changeColor(brickArg);
      }

    }

  }

  /**
   *
   * method to reset the ball back to its original position and default direction towards the top left
   *
   * this is used when we reset the level and when the cheat key reset is used to move the ball back
   * to original position
   *
   */

  public void resetBall(){

    ball.setCenterX(originX);
    ball.setCenterY(originY);
    dirToggleY = -1;
    dirToggleX = -1;


  }


}

