package threeDGame;

public class Render {
	protected int height;
	protected int width;
	public final int[] pixels;
	
	public Render(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public void draw(Render render, int xOffSet, int yOffSet) {
		for(int y = 0; y < render.height; y++) {
			int yPix = yOffSet + y;
			if(yPix < 0 || yPix >= height)
				continue;
			
			for(int x = 0; x < render.width; x++) {
				int xPix = xOffSet + x;
				if(xPix < 0 || xPix >= width)
					continue;
				
				int alpha = render.pixels[x + y * render.width];
				if(alpha > 0)
					pixels[xPix + yPix * width] = alpha;
				
			}
		}
	}
	
	
}
