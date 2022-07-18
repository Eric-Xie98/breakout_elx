package breakout;

import java.io.FileNotFoundException;

import java.util.HashSet;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.io.File;
import javafx.scene.text.Text;



/**
 * The BouncerGame class holds all the main mechanics in the game including the setup and step
 * method that creates the stage/root and controls what happens in each iteration of step. For
 * example, it controls the movement of all the objects including Paddles and Bouncers. It also
 * updates and checks for game conditions such as winning and losing.
 *
 *
 * @author Eric L. Xie
 *
 */
public class BouncerGame {

  private static int layoutWidth;
  private static int layoutHeight;
  private static final String level1 = "/Users/ericx/Downloads/Duke Spring 2022/CS308/Projects/breakout_elx/src/main/resources/levels/level_1.txt";
  private static final String level2 = "/Users/ericx/Downloads/Duke Spring 2022/CS308/Projects/breakout_elx/src/main/resources/levels/level_2.txt";
  private static final String level3 = "/Users/ericx/Downloads/Duke Spring 2022/CS308/Projects/breakout_elx/src/main/resources/levels/level_3.txt";

  // things to keep track of during the game (moving objects)
  private Group root;
  private int currentLevel = 1;
  private int lives = 3;
  private boolean bouncerCheatFlag = false;
  private Text livesCounter;


  private Bouncer myBouncer;
  private Bouncer myBouncer2;
  private Paddle myPaddle;
  private HashSet<Brick> brickList;

  /**
   * Create the game's "scene": what shapes will be in the game and their starting properties.
   * Essentially, it sets up the game in the level 1 state with the default parameters such as only
   * one ball and a certain brick layout read from text files in the resource folder.
   *
   * Furthermore, it contains the KeyEvent handlers that allow us to control the paddle with the
   * arrow keys and utilize keys to be cheat keys for the game, which include extra lives,
   * invincibility, etc.
   *
   * Because of its reliance on files, it throws a FileNotFoundException just in case.
   *
   * @param background The paint I want for the background of the game
   * @param width      The length of the screen
   * @param height     how tall the screen is
   */


  public Scene setupGame(int width, int height, Paint background) throws FileNotFoundException {

    layoutWidth = width;
    layoutHeight = height;

    // create one top level collection to organize the things in the scene
    root = new Group();
    brickList = readFileCreateLevel(level1, root);

    myBouncer = new Bouncer(width / 2, height / 2 + 100, -1, -1, root);
    myPaddle = new Paddle(width / 2, height / 2 + 225, root);

    livesCounter = new Text(10, 500, "Lives: " + lives);
    livesCounter.setFill(Color.WHITE);
    root.getChildren().add(livesCounter);

    // create a place to see the shapes
    Scene scene = new Scene(root, width, height, background);
    scene.setOnKeyPressed(e -> myPaddle.onKeyInput(e.getCode()));
    scene.setOnKeyReleased(e -> myPaddle.onKeyRelease(e.getCode()));
    scene.addEventHandler(KeyEvent.KEY_PRESSED, evt -> checkReset(evt.getCode()));
    scene.addEventHandler(KeyEvent.KEY_PRESSED, evt2 -> addLife(evt2.getCode()));
    scene.addEventHandler(KeyEvent.KEY_PRESSED, evt3 -> {
      try {
        switchToLevel(evt3.getCode());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    });
    scene.addEventHandler(KeyEvent.KEY_PRESSED, evt4 -> bouncerCheat(evt4.getCode()));
    scene.addEventHandler(KeyEvent.KEY_PRESSED,
        evt5 -> setAllBricksWeak(evt5.getCode(), brickList));
    return scene;

  }

  /**
   * Change properties of shapes in small ways to animate them over time Note, there are more
   * sophisticated ways to animate shapes, but these simple ways work fine to start Each step, the
   * methods in step check and account for: intersections, bounces against walls, lives lost from
   * bouncing out of bounds, moving balls and paddles, updating bricks, game conditions (win/lose),
   * etc.
   * <p>
   * This throws the FileNotFoundException because it handles methods that use files to switch
   * levels (switchLevel())
   *
   * @param elapsedTime Used as a factor in the Bouncer class to calculate the movement of the
   *                    Bouncers over time
   */

  public void step(double elapsedTime) throws FileNotFoundException {

    checkIntersection(myBouncer, myPaddle, brickList, root);
    checkBounceCheat(bouncerCheatFlag, myBouncer);
    myBouncer.move(elapsedTime);
    myPaddle.move();

    // Updates the second bouncer only if the current level is the 2nd and above
    if (currentLevel >= 2) {

      checkIntersection(myBouncer2, myPaddle, brickList, root);
      checkBounceCheat(bouncerCheatFlag, myBouncer2);
      myBouncer2.move(elapsedTime);
    }
    livesCounter.setText("Lives: " + lives);

    checkLives(lives);

    if (checkLevelWon(brickList)) {
      switchLevel(root);
    }

  }

  /**
   * The checkLevelWon simply checks the HashSet of bricks to see if it's empty or not If empty,
   * this means that the player has destroyed all the bricks in the level, which returns a boolean
   * true to toggle a control statement to switch to the next level.
   *
   * @param bricks A HashSet of Brick objects that holds all the created and remaining bricks in the
   *               current level
   */

  private boolean checkLevelWon(HashSet<Brick> bricks) {
    return bricks.isEmpty();
  }

  /**
   * Holds the statements that remove the old level nodes/objects on the stage and replace them with
   * whatever new level contains based on a text file. It also resets flags and constants that the
   * previous value may have changed due to powerups or cheats.
   *
   * Depending on the currentLevel value, it chooses a file and different parameters (like an extra
   * ball).
   *
   * Throws FileNotFoundException as it has methods that read the file and create the level
   * (readFileCreateLevel)
   */
  private void switchLevel(Group root) throws FileNotFoundException {
    root.getChildren().clear();
    currentLevel++;
    bouncerCheatFlag = false;
    Bouncer.SPEED_FACTOR = 1;
    System.out.println(currentLevel);
    System.out.println("Finished level. Now on level " + currentLevel + ".");
    lives = 3;
    livesCounter = new Text(10, 500, "Lives: " + lives);
    livesCounter.setFill(Color.WHITE);
    root.getChildren().add(livesCounter);

    myBouncer = new Bouncer(layoutWidth / 2, layoutHeight / 2 + 100, -1, -1, root);
    myPaddle = new Paddle(layoutWidth / 2, layoutHeight / 2 + 225, root);

    // Duvall talked about this in class today, and I understand that this way of deciding on which
    // level to use is not very design friendly; unfortunately, ran out of time.

    if (currentLevel == 1) {
      brickList = readFileCreateLevel(level1, root);
    } else if (currentLevel == 2) {
      brickList = readFileCreateLevel(level2, root);
      myBouncer2 = new Bouncer(layoutWidth / 2, layoutHeight / 2 - 100, -1, -1, root);
      Bouncer.SPEED_FACTOR = 1;
    } else if (currentLevel == 3) {
      brickList = readFileCreateLevel(level3, root);
      myBouncer2 = new Bouncer(layoutWidth / 2, layoutHeight / 2 - 100, -1, -1, root);
      Bouncer.SPEED_FACTOR = 1;
    } else {
      System.out.println("You beat the game, congrats!");
      System.exit(0);
    }

  }


  /**
   * This method does what it says: reads a file and creates a level based on a text file holding
   * the kind of bricks and where
   * <p>
   * Throws FNFE to handle cases where file isn't found
   */

  private HashSet<Brick> readFileCreateLevel(String fileName, Group root)
      throws FileNotFoundException {

    HashSet<Brick> bricks = new HashSet<>();
    File readThis = new File(fileName);
    Scanner sc = new Scanner(readThis);

    // Duvall talked about this in class today, and I understand that this way of deciding on which
    // level to use is not very design friendly; unfortunately, ran out of time.

    // this chunk of code mathematically organizes the bricks on screen and creates them based on
    // their type

    for (int j = 0; j <= layoutHeight / Brick.BRICK_HEIGHT; j++) {
      for (int i = 0; i < layoutWidth / Brick.BRICK_LENGTH; i++) {
        if (sc.hasNext()) {
          String brickType = sc.next();
          if (brickType.equals("B")) {
            bricks.add(new BallBrick(i * (int) Brick.BRICK_LENGTH + (int) Brick.BRICK_LENGTH / 2,
                j * (int) Brick.BRICK_HEIGHT, root));
          } else if (brickType.equals("P")) {
            bricks.add(new PaddleBrick(i * (int) Brick.BRICK_LENGTH + (int) Brick.BRICK_LENGTH / 2,
                j * (int) Brick.BRICK_HEIGHT, root));
          } else if (brickType.equals("S")) {
            bricks.add(new SpeedBrick(i * (int) Brick.BRICK_LENGTH + (int) Brick.BRICK_LENGTH / 2,
                j * (int) Brick.BRICK_HEIGHT, root));
          } else if (!brickType.equals("0")) {
            bricks.add(new Brick(i * (int) Brick.BRICK_LENGTH + (int) Brick.BRICK_LENGTH / 2,
                j * (int) Brick.BRICK_HEIGHT, Integer.parseInt(brickType), root));
          }
        } else {
          break;
        }
      }
    }

    return bricks;

  }


  /**
   * private method that checks the number of lives left if equal to or less than 0, end the game
   * using System.exit(0)
   */


  private void checkLives(int livesLeft) {

    if (livesLeft <= 0) {
      System.out.println("You lost. Better luck next time!");
      System.exit(0);

    }

  }

  /**
   * combined method that checks the intersection of the ball between the paddle and bricks,
   * updating the scene if necessary by removing or adding objects into the root.getChildren()
   *
   * @param ball   the Bouncer class ball
   * @param paddle the player controlled Paddle
   * @param bricks the HashSet of Brick objects
   * @param root   the Group that holds all the nodes to display in scene
   */


  private void checkIntersection(Bouncer ball, Paddle paddle, HashSet<Brick> bricks, Group root) {

    ball.isIntersectingPaddle(paddle);
    ball.isIntersectingBrick(bricks, ball, paddle, root);

  }

  /**
   * CHEAT KEY METHOD
   * <p>
   * switches to a specific level based on what key is pressed 1 to Level 1 2 to Level 2 3 to Level
   * 3
   */

  private void switchToLevel(KeyCode code) throws FileNotFoundException {

    if (code == KeyCode.DIGIT1) {
      currentLevel = 0;
      switchLevel(root);
    } else if (code == KeyCode.DIGIT2) {
      currentLevel = 1;
      switchLevel(root);
    } else if (code == KeyCode.DIGIT3) {
      currentLevel = 2;
      switchLevel(root);
    }
  }

  /**
   * CHEAT KEY METHOD
   * <p>
   * private method that changes the bouncerCheatFlag on or off based on its previous state whenever
   * the cheat key is pressed (B)
   */

  private void bouncerCheat(KeyCode code) {

    if (code == KeyCode.B) {
      bouncerCheatFlag = !bouncerCheatFlag;
    }

  }

  /**
   * CHEAT KEY METHOD
   * <p>
   * private method that makes the bottom of the screen bouncy as well, thus making the player
   * invincible and unable to lose any lives
   * <p>
   * activated by clicking B and the cheat is turned off everytime the level is switched
   */

  private void checkBounceCheat(boolean bounceCheatFlag, Bouncer ball) {

    if (bounceCheatFlag) {
      ball.bouncerCheat(layoutWidth, layoutHeight);
    } else {
      ball.checkBounce(layoutWidth);
      lives = ball.checkLifeLost(layoutHeight, lives);

    }

  }

  /**
   * CHEAT KEY METHOD
   * <p>
   * private method that makes all bricks that are currently in the level WEAK (breakable in 1 hit),
   * making the level far easier to complete
   */

  private void setAllBricksWeak(KeyCode code, HashSet<Brick> bricks) {
    if (code == KeyCode.W) {
      for (Brick brickArg : bricks) {
        brickArg.hitsLeft = 1;
      }
    }
  }

  /**
   * CHEAT KEY METHOD
   * <p>
   * private method that adds a singular life when the cheat key L is pressed
   */

  private void addLife(KeyCode code) {
    if (code == KeyCode.L) {
      lives++;
    }
  }

  /**
   * CHEAT KEY METHOD
   *
   * the private method that checks if the R key has been pressed to reset the ball and paddle to
   * their original positions
   */

  private void checkReset(KeyCode code) {

    if (code == KeyCode.R) {
      if (currentLevel >= 2) {
        myBouncer2.resetBall();
      }

      myBouncer.resetBall();
      myPaddle.resetPaddle();

    }

  }


}