package space.gui;

public class DrawEntity {
	public final int width;
	public final int height;
	public double angle;
	public final Bitmap bitmap;
	
	public DrawEntity(int width, int height, double angle, Bitmap bitmap) {
		this.width = width;
		this.height = height;
		this.angle = angle;
		this.bitmap = bitmap;
	}
}