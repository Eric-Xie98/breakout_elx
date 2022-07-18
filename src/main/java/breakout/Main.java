package breakout;

import java.io.FileNotFoundException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * @author Eric L. Xie
 */


/**
 * Main class for running the main program while the BounceGame handles the game mechanics.
 * This is where the animation and BouncerGame is created.
 *
 */
public class Main extends Application {
    // useful names for constant values used
    public static final String TITLE = "Breakout Game";
    public static final int WIDTH_SIZE = 480;
    public static final int HEIGHT_SIZE = 600;
    public static final Paint BACKGROUND = Color.BLACK;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    // instance variables
    private BouncerGame myGame;

    /**
     * Initialize what will be displayed and that it will be updated regularly.
     * @param stage The stage where nodes are placed onto the screen and can be updated.
     */
    @Override
    public void start (Stage stage) throws FileNotFoundException {

        // create game to be played
        myGame = new BouncerGame();

        // attach scene to the stage and display it
        Scene scene = myGame.setupGame(WIDTH_SIZE, HEIGHT_SIZE, BACKGROUND);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();



        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> {
            try {
                myGame.step(SECOND_DELAY);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        animation.play();


    }

}
