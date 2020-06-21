package space;

import javax.swing.JFrame;
import java.util.concurrent.atomic.AtomicInteger;

public class SpaceComponent implements Runnable {
	private Screen screen;
	private Game game;
	private boolean running = false;
	
	public SpaceComponent() {
		screen = new Screen();
		game = new Game();
		game.init();
		running = true;
		
		JFrame f = new JFrame("Space");
		f.add(screen);
		f.pack();
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void run() {
		double fps = 60;
		double millisPerFrame = 1000/fps;
		double unprocessedTicks = 1;
		double lastTick = System.currentTimeMillis();
		AtomicInteger frames = new AtomicInteger(0);
		AtomicInteger ticks = new AtomicInteger(0);
		new Thread(() -> {
			while (running) {
				try {
					Thread.sleep(1000);
				} catch(Exception ignore) {}
				System.out.println("fps: " + frames + ", ticks: " + ticks);
				frames.set(0);
				ticks.set(0);
			}
		}).start();
		
		while (running) {
			double now = System.currentTimeMillis();
			double delta = now-lastTick;
			lastTick = now;
			unprocessedTicks += delta/millisPerFrame;
			while (unprocessedTicks >= 1) {
				game.tick();
				unprocessedTicks--;
				ticks.incrementAndGet();
			}
			screen.render(game);
			frames.incrementAndGet();
			double now2 = System.currentTimeMillis();
			try {
				double delta2 = now2-now;
				if (delta2 < millisPerFrame)
					Thread.sleep((int)(millisPerFrame-delta2));
			} catch(Exception ignore) {}
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
}