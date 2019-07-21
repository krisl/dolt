package threeDGame;

public class Controller {
	public double x =0, z=0, direction = 0;
	public void tick(boolean left, boolean right, boolean forward, boolean back, boolean turnleft, boolean turnright) {
		double rotateSpeed = 1;
		double moveSpeed = 1;
		double xMove = 0;
		double zMove = 0;
		double rotate = 0;
		
		if(left)
			xMove-=0.5;
		
		if(right)
			xMove+=0.5;
		
		if(forward)
			zMove+=1.5;
		
		if(back)
			zMove-=1.5;
		
		if(turnleft)
			rotate--;
		
		if(turnright)
			rotate++;
		
		direction += rotate/30.0;
		
		double xa = xMove * Math.cos(direction) + zMove * Math.sin(direction) * moveSpeed;
		double za = zMove * Math.cos(direction) - xMove * Math.sin(direction) * moveSpeed;
			
		x += xa;
		z += za;
		
	}
}
