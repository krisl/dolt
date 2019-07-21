package threeDGame;

import java.awt.event.KeyEvent;

public class Game {
	public double time;
	boolean forward;
	boolean backward;
	boolean left;
	boolean right;
	boolean turnleft;
	boolean turnright;
	Controller controls;
	
	public Game() {
		controls = new Controller();
	}
	
	public double  tick(boolean[] key) {
		time +=0.1;
		forward = key[KeyEvent.VK_W] || key[KeyEvent.VK_UP];
		backward = key[KeyEvent.VK_S] || key[KeyEvent.VK_DOWN];
		left = key[KeyEvent.VK_A];
		right = key[KeyEvent.VK_D];
		turnleft = key[KeyEvent.VK_LEFT];
		turnright = key[KeyEvent.VK_RIGHT];
		
		controls.tick(left, right, forward, backward, turnleft, turnright);
		return time;
	}
}
