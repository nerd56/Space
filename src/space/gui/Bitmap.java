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
	
	public void draw(int xOffset, int yOffset, int newW, int newH, double angle, Bitmap bitmap) {
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double scaleX1 = (double)newW/bitmap.width;
		double scaleY1 = (double)newH/bitmap.height;
		int w = (int)(Math.abs(newW*cos) + Math.abs(newH*sin));
		int h = (int)(Math.abs(newH*cos) + Math.abs(newW*sin));
		double scaleX = (double)w/bitmap.width;
		double scaleY = (double)h/bitmap.height;
		xOffset -= w/2;
		yOffset -= h/2;
		DrawBox db = getDrawBox(xOffset, yOffset, w, h);
		
		for (int y = 0; y < db.yEnd; y++) {
			for (int x = 0; x < db.xEnd; x++) {
				double xx = x+db.xStart-w/2;
				double yy = y+db.yStart-h/2;
				
				double xr = (xx*cos - yy*sin);
				double yr = (yy*cos + xx*sin);
				
				double ww = bitmap.width/2.0 + (xr/scaleX1);
				double hh = bitmap.height/2.0 + (yr/scaleY1);

				if (ww >= 0 && ww < bitmap.width && hh >= 0 && hh < bitmap.height) {
					int pixel = bitmap.pixels[(int)ww + (int)hh*bitmap.width];
					if (pixel != IGNORE)
						pixels[db.xOffset+x + (db.yOffset+y)*width] = pixel;
				}
			}
		}
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