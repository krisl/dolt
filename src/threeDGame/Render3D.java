package threeDGame;

public class Render3D extends Render {
	public Render3D(int width, int height) {
		super(width, height);
	}
	
	public void floor(Game game) {
		int floorpos = 8;
		int ceilpos = 40;
		double rotation = game.controls.direction;// game.time/100.0;
		double cos = Math.cos(rotation);
		double sin = Math.sin(rotation);
		double forward = game.controls.z;//sin*50.0;//game.time /1.0;
		double right = game.controls.x;//-cos*50.0;//game.time /1.0;
		
		for(int y = 0; y < height; y++) {
			double yDepth = (y-height/3.2)/height;
			double z = (floorpos / yDepth) ;
			if(yDepth < 0)
				z = ceilpos/-yDepth;
			
			if(z > 760)
				continue;
			for(int x =0; x < width; x++) {
				double xDepth = (x-width/2.0)/height;
				xDepth *= z;
				int xx = (int) ((  (xDepth/*+game.time*-sin/200*/) * cos + z * sin) + right ) & 0xf; //(Math.abs(xDepth) * width )  & 0xfffff0f;
				int yy = (int)(  (z/*+game.time*-cos/200*/) * cos - xDepth*sin + forward) & 0x0f;
				//pixels[x +y*width] = xx << 11  | yy << 4;
				int pos = (xx &7) + (yy&7 ) * 8;
				int pix = Texture.floor.pixels[pos];
				//System.out.println(pix);
				pixels[x +y*width] = pix & 0x0fff0f;
				//System.out.println(xx);
			}
		}
	}
}
