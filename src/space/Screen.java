package space;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;

import space.gui.Bitmap;
import space.gui.Art;

public class Screen extends JPanel {
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final int SCALE = 1;
	
	private Bitmap bitmap;
	
	private BufferedImage img;
	
	public Screen() {
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		int[] pixels = ((DataBufferInt) (img.getRaster().getDataBuffer())).getData();
		bitmap = new Bitmap(WIDTH, HEIGHT, pixels);
	}
	
	public void render(Game game) {
		bitmap.draw(WIDTH/2,
					HEIGHT/2,
					4, Math.PI,Art.rocket);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g = (Graphics2D) g;
		g.drawImage(img, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g.dispose();
	}
}