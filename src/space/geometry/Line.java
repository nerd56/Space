package space.geometry;

public class Line implements Hitbox {
	public Point p1;
	public Point p2;
	
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public boolean isCollide(Point p) {
		return p.isCollide(this);
	}
	
	@Override
	public boolean isCollide(Line l) {
		return false;
	}
	
	@Override
	public boolean isCollide(Circle c) {
		double x = c.r*getSin();
		double y = c.r*getCos();
		Line l = new Line(new Point(-x, y).add(c.center), new Point(x, -y).add(c.center));
		Point maxX = l.getPointByMaxX(); Point minX = l.getPointByMinX();
		Point maxY = l.getPointByMaxY(); Point minY = l.getPointByMinY();
		
		boolean lessThanMaxY = maxY.compareYTo(this) >= 0;
		boolean greaterThanMinY = minY.compareYTo(this) <= 0;
		boolean lessThanMaxX = maxX.compareXTo(this) >= 0;
		boolean greaterThanMinX = minX.compareXTo(this) <= 0;
		boolean inScopeX = minX.x <= getPointByMaxX().x && maxX.x >= getPointByMinX().x;
		boolean inScopeY = minY.y <= getPointByMaxY().y && maxY.y >= getPointByMinY().y;
		boolean collideWithPoints = p1.isCollide(c) || p2.isCollide(c);
		
		return ((lessThanMaxY && greaterThanMinY) || (lessThanMaxX && greaterThanMinX))
				&& inScopeX && inScopeY || collideWithPoints;
	}
	
	@Override
	public void rotate(Point p, double angle) {
		p1.rotate(p, angle);
		p2.rotate(p, angle);
	}
	
	@Override
	public void move(Point p) {
		p1.move(p);
		p2.move(p);
	}
	
	@Override
	public Point getCenter() {return new Point((p1.x+p2.x)/2, (p1.y+p2.y)/2);}
	
	
	public double getXByY(double y) {return y*getCtan();}
	public double getYByX(double x) {return x*getTan();}
	public double getCtan() {return getCos()/getSin();}
	public double getTan() {return getSin()/getCos();}
	public double getSin() {return getDeltaY()/getLen();}
	public double getCos() {return getDeltaX()/getLen();}
	public double getDeltaX() {return p2.x-p1.x;}
	public double getDeltaY() {return p2.y-p1.y;}
	public double getLenX() {return Math.abs(getDeltaX());}
	public double getLenY() {return Math.abs(getDeltaY());}
	public double getLen() {double xl = getLenX(), yl = getLenY();return Math.sqrt(xl*xl + yl*yl);}
	public Point getPointByMinX() {return p1.x-p2.x <= 0 ? p1 : p2;}
	public Point getPointByMaxX() {return p1.x-p2.x > 0 ? p1 : p2;}
	public Point getPointByMinY() {return p1.y-p2.y <= 0 ? p1 : p2;}
	public Point getPointByMaxY() {return p1.y-p2.y > 0 ? p1 : p2;}
	
	@Override
	public String toString() {
		return "p1: " + p1 + "    p2: " + p2;
	}
}