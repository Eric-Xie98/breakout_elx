# Breakout Design
## Eric Xie


## Design Goals

My design goal for Breakout was to create a functioning, simple Breakout game that followed as closely
to most of my PLAN.md as possible. I wanted to ensure that my code was overall decent and efficient as well as
loosely followed the recently learned Single Responsibility and Open-Closed Principle. Furthermore, I wanted
to design a game that was challenging, but possible for the players to win. 


## High-Level Design

In terms of high-level design, I separated the game into two different parts in my mind: the game mechanics
and the objects in the game. As you can see, the BouncerGame() contains most of the main game mechanics that handle
the game conditions such as winning and losing. The classes of Brick, Paddle, and Bouncer are centered around the 
objects that are interacted with in the game and house the methods that pertain to it. For example, the Bouncer() class
handles the interactions between the Brick and Paddle within the game while the BouncerGame() mechanic class runs the method
in the step() function every 1/60th of a second or so.

## Assumptions or Simplifications

As mentioned in other documents, one assumption I made was that the user would never touch the game window and expand or
minimize it. Because of the game window is created with a certain height and width, the ball takes those
values into account only. If the user were to expand or minimize the window, the ball would bounce
off a invisible wall or bounce off-screen respectively. 

A simplification I made to the game was the explosive brick idea. Originally, I planned in my PLAN.md document
that I would create an explosive brick that would damage all the surrounding blocks in a certain radius by 1. However,
I found that doing this would take a large amount of logic and navigating through the HashSet of bricks I had and finding
the bricks that surrounded it, etc.. As a result, I decided to simplify the powerup into something else.

Another somewhat assumption I made was that whoever the player was would already know what kind of controls were
in the game as well as the powerup. Unless they read the README.md and DESIGN.md like you are, they would be essentially lost
on what does what or what the rules of the game are.

## Changes from the Plan

As I mentioned above, I simplified the game by taking away the explosive brick idea, which altered how the
levels were built. Another change in the plan was switching the paddle cheat key into a power up instead. I felt that
the powerup was a bit stale with how creative we could be with it, so I turned it into a powerup instead. 

I also deviated from the "pictures" of the levels, which I created after playing multiple iterations of the game. Some of the levels
I initially designed didn't seem fun or weren't well-designed.


## How to Add New Levels

In order to add a new level variation to the game, you first have to create a private static final String at the top of
the BouncerGame class to hold the resource path. Next, you have to go to the switchLevel() method and add in the logic statement
that checks the current level. From there, you'll follow a similar pattern to the previous lines of code and tell the method
to read in the new file instead if the currentLevel instance variable == 4. Even though this isn't that many changes, it definitely
isn't an efficient or a well-designed method of implementing a new level. Thinking now, I could have added the level names to an
array list and did something with that.