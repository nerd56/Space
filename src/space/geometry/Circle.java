package space.geometry;

public class Circle implements Hitbox {
	public Point center;
	public double r;
	
	public Circle(Point center, double r) {
		this.center = center;
		this.r = r;
	}
	
	@Override
	public boolean isCollide(Point p) {
		return p.isCollide(this);
	}
	
	@Override
	public boolean isCollide(Line l) {
		return l.isCollide(this);
	}
	
	@Override
	public boolean isCollide(Circle c) {
		Point p = c.center;
		double dx = p.x-center.x;
		double dy = p.y-center.y;
		double s = Math.sqrt(dx*dx + dy*dy);
		return s < c.r+r;
	}
	
	@Override
	public void rotate(Point p, double angle) {
		center.rotate(p, angle);
	}
	
	@Override
	public void move(Point p) {
		center = center.add(p);
	}
	
	@Override
	public Point getCenter() {return center;}
}