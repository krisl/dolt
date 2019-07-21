package threeDGame;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Texture {
	public static Render floor = loadBitmap("/textures/floor.bmp");
	
	static Render loadBitmap(String filename) {
		try {
			URL x = Texture.class.getResource(filename);
			BufferedImage image = ImageIO.read(x);
			int width = image.getWidth();
			int height = image.getHeight();
			Render result = new Render(width, height);
			image.getRGB(0, 0, width, height, result.pixels, 0, width);
			return result;
		} catch (Exception ex) {
			System.out.println("Error loading bitmap");
			throw new RuntimeException();
		}
	}
}
