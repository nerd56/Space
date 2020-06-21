package space;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import space.entity.*;
import space.gui.*;
import space.geometry.*;

public class Game {
	public List<Entity> entities;
	public Camera camera;
	
	private Entity toxic;
	private Entity mars;
	private Entity rocket;
	private Entity line;
	
	private Point rocketRadius;
	
	public void init() {
		Point toxicCenter = new Point(0, 0);
		Point marsCenter = new Point(-200, 0);
		
		rocketRadius = new Point(50, 0);
		Point rocketCenter = new Point(0, 0);
		
		DrawEntity toxicDrawEntity = new DrawEntity(Art.toxic.width*2, Art.toxic.height*2, 0, Art.toxic);
		Circle toxicHitbox = new Circle(toxicCenter, Art.toxic.width);
		toxic = new Planet(toxicHitbox, toxicDrawEntity);
		
		DrawEntity marsDrawEntity = new DrawEntity(Art.mars.width, Art.mars.height, 0, Art.mars);
		Circle marsHitbox = new Circle(marsCenter, Art.mars.width/2);
		mars = new Planet(marsHitbox, marsDrawEntity);
		
		DrawEntity rocketDrawEntity = new DrawEntity(Art.rocket.width, Art.rocket.height, 0, Art.rocket);
		Hitbox rocketHitbox = rocketCenter;
		rocket = new Entity(rocketHitbox, rocketDrawEntity);
		
		int w = 200; int h = 40;
		int x = 200; int y = 0;
		int[] pixels = new int[w*h];
		Arrays.fill(pixels, 0xFFFF00FF);
		Bitmap lineBitmap = new Bitmap(w, h, pixels);
		Line l = new Line(new Point(w-1, h-1), new Point(0, 0));
		Line lineHitbox = new Line(new Point(x-(w-1)/2.0, y-h/2.0), new Point(x+(w-1)/2.0, y+h/2.0));
		Painter.drawLine(l, 0xFF00, lineBitmap);
		DrawEntity lineDrawEntity = new DrawEntity(w, h, 0, lineBitmap);
		
		line = new Entity(lineHitbox, lineDrawEntity);
		
		camera = new Camera(rocketCenter, 1, 0);
		
		entities = new ArrayList<Entity>();
		
		entities.add(toxic);
		entities.add(mars);
		entities.add(rocket);
		entities.add(line);
	}
	
	public void tick() {
		toxic.drawEntity.angle += Math.PI/600;
		Point tc = toxic.hitbox.getCenter();
		Point mc = mars.hitbox.getCenter();
		Point rc = rocket.hitbox.getCenter();
		mc.rotate(tc, Math.PI/360);
		mars.drawEntity.angle -= Math.PI/360;
		rocketRadius.rotate(new Point(0, 0), -Math.PI/120);
		rc.setPoint(mc.add(rocketRadius));
		rocket.drawEntity.angle += Math.PI/120;
		
		boolean collide = ((Line)(line.hitbox)).isCollide((Circle)mars.getHitbox());
		if (collide) System.out.println(collide);
	}
}