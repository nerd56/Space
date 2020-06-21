package space;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

import space.gui.*;
import space.entity.*;
import space.geometry.*;

public class Screen extends JPanel {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private static final int SCALE = 1;
	
	private Bitmap bitmap;
	
	private BufferedImage img;
	
	public Screen() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) (img.getRaster().getDataBuffer())).getData();
		bitmap = new Bitmap(WIDTH, HEIGHT, pixels);
	}
	
	public void render(Game game) {
		int[] pixels = new int[WIDTH*HEIGHT];
		Bitmap b = new Bitmap(WIDTH, HEIGHT, pixels);
		Point offset = game.camera.focus;
		double scale = game.camera.scale;
		for (Entity e : game.entities) {
			DrawEntity de = e.drawEntity;
			Point p = e.hitbox.getCenter().getCopy();
			p.rotate(offset, game.camera.angle);
			int x = (int)((p.x - offset.x)*scale)+WIDTH/2;
			int y = (int)((p.y - offset.y)*scale)+HEIGHT/2;
			b.draw(x, y, (int)(de.width*scale), (int)(de.height*scale), de.angle-game.camera.angle, de.bitmap);
		}
		
		setBitmap(b);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}
	
	private void setBitmap(Bitmap b) {
		for (int i = 0; i < WIDTH*HEIGHT; i++)
			bitmap.pixels[i] = b.pixels[i];
	}
}