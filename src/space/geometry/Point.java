package space.geometry;

public class Point implements Hitbox {
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean isCollide(Point p) {
		return x-p.x == 0 && y-p.y == 0;
	}
	
	@Override
	public boolean isCollide(Line l) {
		boolean inSpaceX = x >= l.getPointByMinX().x && x <= l.getPointByMaxX().x;
		boolean inSpaceY = y >= l.getPointByMinY().y && y <= l.getPointByMaxY().y;
		boolean onLine = Math.abs(l.getTan()) == Math.abs(new Line(l.p1, this).getTan());
		return inSpaceX && inSpaceY && onLine || isCollide(l.p1) || isCollide(l.p2);
	}
	
	@Override
	public boolean isCollide(Circle c) {
		Point p = c.center;
		double dx = p.x-x;
		double dy = p.y-y;
		double s = Math.sqrt(dx*dx + dy*dy);
		return s < c.r;
	}
	
	@Override
	public void rotate(Point p, double angle) {
		Line l = new Line(p, this);
		double sin = Math.sin(angle);
		double cos = Math.cos(angle);
		double w = l.getDeltaX();
		double h = l.getDeltaY();
		double x = w*cos - h*sin;
		double y = h*cos + w*sin;
		this.x = p.x+x;
		this.y = p.y+y;
	}
	
	@Override
	public void move(Point p) {
		x += p.x;
		y += p.y;
	}
	
	@Override
	public Point getCenter() {return this;}
	
	public Point add(Point p) {
		return new Point(x+p.x, y+p.y);
	}
	
	public Point getCopy() {
		return new Point(x, y);
	}
	
	public void setPoint(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public double compareYTo(Line l) {
		return y - (l.p1.y + l.getYByX(x-l.p1.x));
	}
	
	public double compareXTo(Line l) {
		return x - (l.p1.x + l.getXByY(y-l.p1.y));
	}
	
	public double getDeltaX(Point p) {return x-p.x;}
	public double getDeltaY(Point p) {return y-p.y;}
	
	@Override
	public String toString() {
		return "x: " + x + ", y: " + y;
	}
}