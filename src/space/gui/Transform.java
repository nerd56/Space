package space.gui;

public class Transform {
	public static Bitmap getBitmapByRotate(double angle, Bitmap bitmap) {
		int width = bitmap.width;
		int height = bitmap.height;
		int[] pixels = bitmap.pixels;
		
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		
		int w = (int)(Math.abs(width*cos) + Math.abs(height*sin));
		int h = (int)(Math.abs(height*cos) + Math.abs(width*sin));
		
		int[] p = new int[w*h];
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int xx = x-w/2;
				int yy = y-h/2;
				
				int xr = (int)(xx*cos - yy*sin);
				int yr = (int)(yy*cos + xx*sin);
				
				int ww = width/2 + xr; int hh = height/2+yr;
				if (ww >= 0 && ww < width && hh >= 0 && hh < height) {
					int pixel = pixels[ww + hh*width];
					p[x + y*w] = pixel;
				} else {
					p[x + y*w] = IGNORE;
				}
			}
		}
		
		return new Bitmap(w, h, p);
	}
	
	public static Bitmap getBitmapByScale(int newW, int newH, Bitmap bitmap) {
		int width = bitmap.width;
		int height = bitmap.height;
		int[] pixels = bitmap.pixels;
		
		double scaleX = (double)newW/width;
		double scaleY = (double)newH/height;
		int w = (int)(width*scaleX);
		int h = (int)(height*scaleY);
		int[] p = new int[w*h];
		Bitmap bitmap = new Bitmap(w, h, p);
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int pixel = pixels[(int)(x/scaleX) + (int)(y/scaleY)*width];
				p[x + y*w] = pixel; 
			}
		}
		return bitmap;
	}
}