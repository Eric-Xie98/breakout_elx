# Breakout
## Eric Xie


DO NO FORK THIS REPOSITORY, clone it directly to your computer.


This project implements the game of Breakout.

### Timeline

Start Date: 1/6/21

Finish Date: 1/19/21

Hours Spent: ~20

### Resources Used
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Line.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Circle.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/shape/Rectangle.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html

https://www.w3schools.com/java/ref_keyword_extends.asp
https://www.tabnine.com/code/java/methods/javafx.scene.Scene/addEventHandler

https://stackoverflow.com/questions/21331519/how-to-get-smooth-animation-with-keypress-event-in-javafx


### Running the Program

Main class: 

* Main.java

Data files needed: 

* level_1.txt
* level_2.txt
* level_3.txt

These data files hold the layout information for each level and is used to create the bricks for
that level.

Key/Mouse inputs:

* Left + Right Arrow Keys - Used to control the paddle movement from left to right
* R key - Reset ball and paddle
* L key - Adds one life to the lives counter
* 1-3 number keys - Switch to the level corresponding to whatever number key between 1-3 you pressed
* B key - makes the bottom of the screen bouncy, thus making the player unable to lose lives
* W key - makes all the bricks on the screen WEAK or only have 1 hit left before breaking

Cheat keys:
* BounceCheat (B key) - makes the bottom of the screen bouncy, which prevents the ball from falling
through and stops the players from losing lives; this resets at the end of each level, so make sure
to retoggle it after switching levels!
* WeakBricks (W key) - turns all the bricks currently on screen / left on screen into WEAK bricks, 
which are bricks with only 1 hit left before breaking; this cheat is applied to the current level only
and must be reactivated each level if you want to use it for each level


### Notes/Assumptions

Assumptions or Simplifications:

* One assumption in making the game is that the user would never touch the game window and expand or
minimize it. Because of the game window is created with a certain height and width, the ball takes those
values into account only. If the user were to expand or minimize the window, the ball would bounce 
off a invisible wall or bounce off-screen respectively.

* Another assumption is that the user would not know the cheat keys to beat the level. 

* I assumed that the player would already know the instructions and background to playing the level. 
This would relate to moving the paddle, keeping the ball on screen, and the
  different kinds of powerups there are. This knowledge is known because the player would have to read
the README.md outlining the features of the game.

  
For this game, I simplified (not exactly sure what this means):

* Changes in between levels with levels immediately starting one after the other after a level is
completed
* Powerup animation effects and sound effects weren't added at all
* Animation effects of blocks breaking
* I didn't include the exploding bricks from my plan as I realized the difficulty of adding something
of that nature. Since I was using a HashSet of Bricks, I would somehow have to create a method that
detects whether a brick was next to the ExplodingBrick and then damage it by 1. Although a cool concept,
I found that the cost of perfecting a brick wasn't worth it.
* Because I couldn't create ExplosiveBricks, I had to simplify my LevelDesign slightly,
altering the original plan pictures I drew for PLAN.md. I instead opted to create a different Powerup
brick block.
* Another simplification I made was to the brick strength. Originally, I planned the bricks to take hits
from 1 to 5. But for the sake of myself and you as a player, I thought that trying to hit that last brick in corner
5 times was absolutely torturous. I instead set the max brick strength at 3. The brick continues to break
once it hits 0 though.
* Because I found that some of my cheat key ideas coincided with the ones you wanted us to create, I shifted
my paddle cheat key idea to a powerup instead.

Known Bugs:

* One major known bug is the hit detection of the ball and all kinds of bricks at a certain angle. I
believe that because of the way the brick is created (a Line object for each side) and the step() function,
the game doesn't detect collision on a corner of a brick until the ball is too far in. At this point, the ball
is touching 2 lines of the corner, resulting it being flipped in the opposite direction it should go through.
Furthermore, if the ball touches an intersection a certain way, it instantly destroys the block as it rapidly
fluctuates up and down from bouncing between them. This results in weird game physics some time,
especially when the ball is massive.
* Another bug occurs when the paddle gets two paddle powerups, resulting in a deformed paddle. This has
something to do with how the paddle is set up in the level and how .setWidth() expands the rectangles in Paddle
in place. As a result, I only put max one paddle power up in a level (this could be a simplification).

Extra features or interesting things we should not miss:

Not sure. Good luck beating it without the cheats. It took me a long time to even do it once.


### Impressions

My impressions going into this project after not coding for an entire semester was rough. I initially
had trouble getting started and figuring what variables JavaFX used and how breakout worked. Regardless,
once I managed to get into the groove of things, it became more enjoyable. Seeing my game slowly come together
piece by piece, I had fun.

The only gripe I had was not really knowing what "good software design" was yet, so I was confused the majority
of the time on whether what I was coding were good practices. Definitely need to read up on the class readings. 
Initially, the project was manageable, and I could handle all the code. But I noticed (and I'm sure you will too),
that towards the end of the project, I started trading good design for functionality. Funny enough, looking
through and commenting my code, I saw numerous things I could refactor. This would take time, and I know I should've
spent more time redesigning my code, but I had some other class things to attend to. I'm happy to go through
the problems during the TA time with you.

Overall, it was an interesting, albeit time-consuming start to CS308. I'm interested in seeing where this
goes, and I hope I'll come out a better programmer.


