package space.gui;

import space.geometry.Point;

public class Camera {
	public Point focus;
	public double scale;
	public double angle;
	
	public Camera(Point focus, double scale, double angle) {
		this.focus = focus;
		this.scale = scale;
		this.angle = angle;
	}
}