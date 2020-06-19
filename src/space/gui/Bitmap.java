package space.gui;

public class Bitmap {
	private static final int IGNORE = 0xFFFF00FF;
	
	public final int width;
	public final int height;
	public final int[] pixels;
	
	public Bitmap(int width, int height, int[] pixels) {
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}
	
	public void draw(int xOffset, int yOffset, Bitmap bitmap) {
		xOffset -= bitmap.width/2;
		yOffset -= bitmap.height/2;
		DrawBox db = getDrawBox(xOffset, yOffset, bitmap.width, bitmap.height);
		
		for (int y = 0; y < db.yEnd; y++) {
			for (int x = 0; x < db.xEnd; x++) {
				int pixel = bitmap.pixels[x+db.xStart + (db.yStart+y)*bitmap.width];
				if (pixel != IGNORE)
					pixels[db.xOffset+x + (db.yOffset+y)*width] = pixel;
			}
		}
	}
	
	public void draw(int xOffset, int yOffset, double scale, Bitmap bitmap) {
		bitmap = bitmap.getBitmapByScale(scale);
		draw(xOffset, yOffset, bitmap);
	}
	
	public void draw(int xOffset, int yOffset, double scale, double angle, Bitmap bitmap) {
		bitmap = bitmap.getBitmapByScale(scale).getBitmapByRotate(angle);
		draw(xOffset, yOffset, bitmap);
	}
	
	public Bitmap getBitmapByRotate(double angle) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double tan = sin/cos;
		
		int w = (int)(Math.abs(width*cos) + Math.abs(height*sin));
		int h = (int)(Math.abs(height*cos) + Math.abs(width*sin));
		
		int centerX = w/2; int centerY = h/2;
		return this;
	}
	
	public Bitmap getBitmapByScale(double scale) {
		int w = (int)(width*scale);
		int h = (int)(height*scale);
		int[] p = new int[w*h];
		Bitmap bitmap = new Bitmap(w, h, p);
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int pixel = pixels[(int)(x/scale) + (int)(y/scale)*width];
				if (pixel != IGNORE)
					p[x + y*w] = pixel; 
			}
		}
		return bitmap;
	}
	
	private DrawBox getDrawBox(int xOffset, int yOffset, int w, int h) {
		int xStart = 0;
		int yStart = 0;
		
		int xEnd = w;
		int yEnd = h;
		
		if (xOffset < 0) 			{xStart = -xOffset; xEnd += xOffset; xOffset = 0;}
		if (yOffset < 0) 			{yStart = -yOffset; yEnd += yOffset; yOffset = 0;}
		if (xOffset+xEnd >= width)  {xEnd = width - xOffset;}
		if (yOffset+yEnd >= height) {yEnd = height - yOffset;}
		
		return new DrawBox(xOffset, yOffset, xStart, yStart, xEnd, yEnd);
	}
	
	private static class DrawBox {
		public final int xOffset;
		public final int yOffset;
		public final int xStart;
		public final int yStart;
		public final int xEnd;
		public final int yEnd;
		
		public DrawBox(int xOffset, int yOffset, int xStart, int yStart, int xEnd, int yEnd) {
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.xStart = xStart;
			this.yStart = yStart;
			this.xEnd = xEnd;
			this.yEnd = yEnd;
		}
		
		@Override
		public String toString() {
			return "xOffset: " + xOffset + " yOffset: " + yOffset + 
			"    xStart: " + xStart + " yStart: " + yStart +
			"    xEnd: " + xEnd + " yEnd: " + yEnd;
		}
	}
}