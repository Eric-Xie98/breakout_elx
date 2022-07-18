package breakout;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

/**
 * @author Eric L. Xie
 *
 * The Brick superclass contains the instance variables and methods for every kind brick and their
 * powerup counterparts. It contains the final variables for the colors as well as the brick dimensions.
 *
 * The purpose of this class to create the Brick object that is broken by the Bouncer ball in the game.
 * This class extends to the different powerup classes.
 *
 * This class also contains methods that check if the brick is broken and removes the brick
 * accordingly if so.
 *
 */

public class Brick {

  private static final Paint STRONG_BRICK_COLOR = Color.TEAL;
  private static final Paint MEDIUM_BRICK_COLOR = Color.MEDIUMTURQUOISE;
  private static final Paint WEAK_BRICK_COLOR = Color.LIGHTCYAN;
  public static final double BRICK_LENGTH = 60;
  public static final double BRICK_HEIGHT = BRICK_LENGTH / 2.0;

  private final Rectangle thisBrick;
  public final Line topLine;
  public final Line leftLine;
  public final Line rightLine;
  public final Line bottomLine;

  public int hitsLeft;

  private final double brickLeftX;
  private final double brickRightX;
  private final double brickTopY;
  private final double brickBottomY;

  /**
   *
   * The Brick constructor's purpose is to create Bricks for all different kinds from normal bricks
   * with varying strengths to powerup Bricks such as BallBrick, PaddleBrick, and SpeedBrick. This class
   * contains the essentials to making a Brick, which is a filled painted Rectangle with 4 lines serving as
   * its edges.
   *
   * In the constructor, the lines are set based on where the rectangle is created, and the color of
   * the brick is assigned based on the brick health. The Brick object is then added to the root.
   *
   * @param x the x coordinate where you want to place the Brick
   * @param y the y coordinate where you want to place the Brick
   * @param brickHealth how many hits the bricks can take by the ball before breaking
   * @param root the Group that nodes/objects are added and removed from and displayed on the scene
   *
   *
   */

  public Brick(int x, int y, int brickHealth, Group root) {

    thisBrick = new Rectangle(x - BRICK_HEIGHT, y, BRICK_LENGTH, BRICK_HEIGHT);
    brickLeftX = thisBrick.getX();
    brickRightX = thisBrick.getX() + BRICK_LENGTH;
    brickTopY = thisBrick.getY();
    brickBottomY = thisBrick.getY() + BRICK_HEIGHT;

    topLine = new Line();
    topLine.setStartY(brickTopY);
    topLine.setEndY(brickTopY);
    topLine.setStartX(brickLeftX);
    topLine.setEndX(brickRightX);

    leftLine = new Line();
    leftLine.setStartY(brickTopY);
    leftLine.setEndY(brickBottomY);
    leftLine.setStartX(brickLeftX);
    leftLine.setEndX(brickLeftX);

    rightLine = new Line();
    rightLine.setStartY(brickTopY);
    rightLine.setEndY(brickBottomY);
    rightLine.setStartX(brickRightX);
    rightLine.setEndX(brickRightX);

    bottomLine = new Line();
    bottomLine.setStartY(brickBottomY);
    bottomLine.setEndY(brickBottomY);
    bottomLine.setStartX(brickLeftX);
    bottomLine.setEndX(brickRightX);

    if(brickHealth == 3){
      thisBrick.setFill(STRONG_BRICK_COLOR);
    }
    else if(brickHealth == 2){
      thisBrick.setFill(MEDIUM_BRICK_COLOR);
    }
    else if(brickHealth == 1){
      thisBrick.setFill(WEAK_BRICK_COLOR);
    }

    root.getChildren().add(thisBrick);
    root.getChildren().add(topLine);
    root.getChildren().add(leftLine);
    root.getChildren().add(rightLine);
    root.getChildren().add(bottomLine);

    hitsLeft = brickHealth;

  }

  /**
   *
   * checks if the brick argument passed to brickBroken method has 0 hits left and returns
   * a boolean true if so
   *
   * the purpose of this method is to be used in checking whether a brick is broken or not
   *
   * @param brickArg the Brick we're checking to see if it's broken or not
   *
   */

  public static boolean brickBroken(Brick brickArg){
    return brickArg.hitsLeft == 0;
  }

  /**
   *
   * this method chanegs the color of the brick as its health changes after being hit by the Bouncer
   * ball
   *
   * the colors correspond to the instance variables we assigned in the class from STRONG, MEDIUM, to
   * WEAK
   *
   * the purpose of this class is to change the color of the brick as the step() function moves through
   * checking whether a brick was hit
   *
   * @param brickArg the brick we're changing the color of once it has been hit
   *
   */

  public static void changeColor(Brick brickArg){

    if(brickArg.hitsLeft == 2){
      brickArg.thisBrick.setFill(MEDIUM_BRICK_COLOR);
    }
    else if(brickArg.hitsLeft == 1){
      brickArg.thisBrick.setFill(WEAK_BRICK_COLOR);
    }


  }

  /**
   *
   * this method is used to remove the Brick and all the elements that comprise it (Rectangle and
   * the 4 lines) from the root and from the scene after the brick is broken
   *
   * furthermore, it checks the kind of brick it is (if it's a powerup) and executes the poewrup once
   * that powerup brick is broken
   *
   * @param brickArg the brick we're passing to the method to be removed and its powerup executed
   * @param root the Group that nodes/objects are added and removed from and displayed on the scene
   *
   */

  public static void removeBrick(Brick brickArg, Group root){

    root.getChildren().remove(brickArg.thisBrick);
    root.getChildren().remove(brickArg.topLine);
    root.getChildren().remove(brickArg.leftLine);
    root.getChildren().remove(brickArg.rightLine);
    root.getChildren().remove(brickArg.bottomLine);

    if(brickArg instanceof BallBrick){
      root.getChildren().remove(((BallBrick) brickArg).ballIcon);
    }
    else if(brickArg instanceof PaddleBrick){
      root.getChildren().remove(((PaddleBrick) brickArg).ballIcon);
    }
    else if(brickArg instanceof SpeedBrick){
      root.getChildren().remove(((SpeedBrick) brickArg).ballIcon);
    }

  }

}
