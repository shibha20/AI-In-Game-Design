# AI-In-Game-Design

Goal of the project : Design two non-player characters that acts as “guards” based on the steering behaviors. 

•	Dot Matrix (the green square) is controlled by the player using the standard WASD keys. She moves at 4 units/frame. Her goal is to reach the exit of the room (the light blue bar on the right) without being caught, at which point the player wins.

•	Hack and Slash (the yellow and orange squares respectively) are the non-player characters opposing the player. Their goal is to catch the player (at which point the player loses). They can only move up to 3 units/frame, so they will need to be smart! 

•	Bob (the blue square) is another NPC who works with the player. He always moves towards either Hack or Slash (whichever is closer to Dot) at 2 units/frame. If he hits Hack or Slash, they will be destroyed, so they will need to avoid him.

•	There are two “wires” (the red squares) that swing back and forth. Any character that hits them (including Dot) is destroyed.

Overall goals

You are to consider your Hack and Slash to be “characters” of some sort in an interesting and playable game. This means that:

•	They should not be perfect. That is, it should be possible for a player to beat the them and reach the exit. Note that this will not be the main challenge!

•	They should appear “intelligent”. That is, while it should be possible for the player to win, it should not be because Hack and Slash have done something obviously “stupid” (like wandering away from the door when the player is nearby, or wandering into Bob or a wire).

•	They should not appear to be predictable. That is, the player should not be able to win every time using the exact same strategy.

•	Hack and Slash should have different behaviors. Not only will this probably give you a better chance of success, it will also help enhance the sense of “personality”. 
