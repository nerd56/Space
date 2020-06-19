package space;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;

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
		flip();
		bitmap.draw(WIDTH/2,
					HEIGHT/2,
					3, -System.currentTimeMillis()%5000/5000.0*Math.PI*2,Art.toxicPlnt);
		bitmap.draw(WIDTH/2 + (int)(Math.sin(System.currentTimeMillis()%5000/5000.0*Math.PI*2)*WIDTH/3),
					HEIGHT/2 + (int)(Math.cos(System.currentTimeMillis()%5000/5000.0*Math.PI*2)*WIDTH/3),
					4, System.currentTimeMillis()%5000/5000.0*Math.PI*2 - Math.PI/2,Art.rocket);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH*SCALE, HEIGHT*SCALE);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}
	
	private void flip() {
		for (int i = 0; i < bitmap.width * bitmap.height; i++)
			bitmap.pixels[i] = 0;
	}
}