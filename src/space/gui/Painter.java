package space.gui;

import space.geometry.*;

public class Painter {
	public static void drawPoint(Point p, int color, Bitmap bitmap) {
		int x = (int)p.x, y = (int)p.y;
		int i = x + y*bitmap.width;
		if (x >= 0 && x < bitmap.width && y >= 0 && y < bitmap.height)
			bitmap.pixels[i] = color;
	}
	
	public static void drawLine(Line l, int color, Bitmap bitmap) {
		Point p1 = l.getPointByMinX();
		Point p2 = l.getPointByMaxX();
		int x1 = (int)p1.x, y1 = (int)p1.y, x2 = (int)p2.x, y2 = (int)p2.y;
		
		int dx = x2-x1 + 1;
		int dy = y2-y1;
		
		if (dy >= 0) dy++;
		else dy--;
		
		double yPerX = (double)dy/dx;
		int step;
		
		if (yPerX >= 0) step = 1;
		else step = -1;
	
		double absYPerX = Math.abs(yPerX);
		double unprocessedSteps = absYPerX;
		double totalY = y1;
		
		for (int x = 0; x <= dx; x++) {
			if (absYPerX < 1)
				drawPoint(new Point(x1+x, totalY), color, bitmap);
			else {
				int y = 0;
				while (unprocessedSteps >= 1) {
					drawPoint(new Point(x1+x, totalY+(y++)*step), color, bitmap);
					unprocessedSteps--;
				}
			}
			totalY += yPerX;
			unprocessedSteps += absYPerX;
		}
	}
}