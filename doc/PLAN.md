# Breakout Plan
### Eric Xie



## Interesting Breakout Variants

 * Idea #1

One breakout variant I found interesting was the original, simple version that had bricks with a variety of
different strengths and colors. This version also would contain cool power-up blocks that could either have an
immediate effect or drop a power-up that would boost the mover/platform for a short while in a certain way. The 
different levels would probably contain a different layout of bricks and power-ups.

 * Idea #2

Another cool idea I saw was the "puzzle" breakout game where the balls were launched from the paddle rather than 
having to continuously bounce the ball back. The bricks slowly came down layer by layer, and if they reached the
bottom, the player lost. The game also contained a variety of power-ups and extra point blocks that floated
down.

## Paddle Ideas

 * Idea #1

I was thinking about implementing the "thirds" example posed in the CS308 website where I make the first
third and last third of the paddle reflect the ball back in the direction it came from. The middle part would
just simply bounce the ball back normally. I'm pretty sure this is the standard paddle design in most simple breakout
games.

 * Idea #2

The second paddle idea corresponds to the second game breakout design idea above where we "catch" the ball after
shooting X amount of it. This allows the player to manually re-aim the balls in a certain direction for the
puzzle game. The balls are automatically caught once they bounce back to the bottom of the screen and all gather 
to the same place where the first ball of the amount bounced back. The user would then use mouse 1 or some key
to release all the balls in the direction of their cursor.

## Block Ideas

 * Idea #1

One kind of block I'm looking to implement is one that has different kinds of strengths. Basically,
different colored blocks can take a certain number of hits from the bouncer/ball. Each ball hit changes the block
color and indicates how many hits are left. Once the strength reaches 0, the block breaks.


 * Idea #2

Another idea revolved around having certain blocks being bombs. The bomb brick damages all the surrounding bricks in a
later determined radius by 1. This effect would only activate once that block is destroyed. I'm thinking of potentially
making the explosion trigger other bomb bricks if they're right next to each other instantly.

 * Idea #3

Another block idea is that it would drop a power-up when broken. These blocks would be the toughest/highest strength
blocks. These power-ups would drop at a linear rate down the screen and must be touched by the paddle to be picked up.


## Power-up Ideas

 * Idea #1
 
One power-up idea is that the ball would simply become bigger and easier to hit. This way it could also potentially
intersect/collide with more bricks at a time.

 * Idea #2

Another power-up idea is that the paddle would grow marginally larger, allowing the player to catch
the ball bouncing back more easily. The code would account for this growth and continue to make the paddle area
react differently to ball bounces (refer to paddle ideas).

 * Idea #3

My last power-up idea would negatively affect the player and speed up the ball by a certain factor that will be
later decided. This speed would be permanent for the remainder of the level.


## Cheat Key Ideas

 * Idea #1

My first cheat key would be giving the player extra lives every time the key is pressed. The player
would normally only have one life. Once the ball goes out of bounds, if the player still has lives left, it respawns
the ball and the game continues. There would be a lives counter somewhere in the corner which could increment.

 * Idea #2

My second cheat would be to instantly win the level and move on to the next level. This button would change the 
scene to maybe a level indicator or straight up to the new level. If the level is the last one, the victory screen is
shown instead.

 * Idea #3

My third cheat would be setting all the blocks in the level to a strength of 1. This would make
the level far easier to beat.

 * Idea #4

My last cheat key idea would be making the paddle slightly larger. This would have a upper limit
that will be set later on. Any more attempts to use the cheat won't work.


## Level Descriptions

 * Idea #1

My first level will be a standard breakout game. It will have blocks of different strengths and no
power-ups. The layout will be very straightforward with strong blocks at the front and weaker blocks near the top.

Level Design:

1 1 1 1 1 1 

1 1 1 1 1 1 

2 2 2 2 2 2 

2 2 2 2 2 2

3 3 3 3 3 3 

 * Idea #2

The second level will now add a second ball that will need to be bounced in tandem with the first ball.
If either ball is dropped, you lose the level. The brick layout of the level will also be different and contain
bricks that are explosive.

Level Layout (E for explosive)

3 3 3 3 3 3 3 3 

2 E 2 2 2 2 E 2

2 2 2 2 2 2 2 2

1 1 E 1 1 E 1 1

1 1 1 1 1 1 1 1


 * Idea #3

This level will continue to have 2 balls like the previous level, but there are now blocks with power-ups.
Furthermore, the paddle will also be slightly smaller. These power-up blocks can drop power-ups that can affect
gameplay both positively and negatively. The level will continue to host some explosive bricks and will also have
a new layout.

Level Layout (E - Explosive P - Powerup):

2 2 2 2 2 2 2 2

E 3 E 3 3 E 3 E

3 3 3 3 3 3 3 3

2 2 2 P P 2 2 2 

3 3 0 0 0 0 3 3 

3 0 0 0 0 0 0 3



## Class Ideas

 * Idea #1

One essential class I would create is for the Bouncer/ball. This class would hold the constructor
for the balls and its instance variables for each ball. Each ball would have its own moving parameters. A method in
this class would be its checkingBounds method that makes sure it bounces off the walls of the game based on the window's
parameters.

 * Idea #2

Another essential class to create is for the bricks. The main brick class would probably hold the basic bricks that
have their own strengths. Different brick types could extend off this main one. A method in this class
would be making the block disappear when its strength goes to 0.

 * Idea #3

Another class is for the paddle, which will be controlled by the player. This class holds its instance variables
that control things such as its size and move speed. One method in this class will definitely be its key input methods
which check whether a key was pressed and released. Furthermore, it will accordingly move the paddle based on 
the input.

 * Idea #4

The last class will be the explosive brick class that will extend off the main brick class. This explosive brick
as mentioned before will damage the surrounding bricks in a certain radius by 1 once destroyed. Methods in this class
can potentially be checking whether the brick next to it is explosive or damaging the blocks next to it by 1.