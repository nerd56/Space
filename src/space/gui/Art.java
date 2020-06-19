package space.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Art {
	public static final Bitmap toxic = loadBitmap("/res/ToxicPLNT.png");
	public static final Bitmap mars = loadBitmap("/res/mars.png");
	public static final Bitmap rocket = loadBitmap("/res/rocket.png");
	
	private static Bitmap loadBitmap(String path) {
		try {
			BufferedImage img = ImageIO.read(Art.class.getResource(path));
			int w = img.getWidth();
			int h = img.getHeight();
			int[] pixels = new int[w*h];
			img.getRGB(0, 0, w, h, pixels, 0, w);
			return new Bitmap(w, h, pixels);
		} catch(Exception e) {
			System.out.println("Image " + path + " not found!");
			return new Bitmap(0, 0, new int[0]);
		}
	}
}