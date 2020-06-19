package space;

import javax.swing.JFrame;

public class SpaceComponent implements Runnable {
	private Screen screen;
	private Game game;
	private boolean running = false;
	
	public SpaceComponent() {
		screen = new Screen();
		game = new Game();
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
		while (running) {
			screen.render(game);
			game.tick();
		}
	}
	
	public void start() {
		new Thread(this).start();
	}
}