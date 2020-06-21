package space.geometry;

public interface Hitbox {
	boolean isCollide(Point p);
	boolean isCollide(Line l);
	boolean isCollide(Circle c);
	
	void rotate(Point p, double angle);
	void move(Point p);
	
	Point getCenter();
}