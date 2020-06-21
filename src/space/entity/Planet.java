package space.entity;

import space.geometry.Circle;
import space.gui.DrawEntity;

public class Planet extends Entity {
	public Circle hitbox;
	
	public Planet(Circle hitbox, DrawEntity drawEntity) {
		super(hitbox, drawEntity);
		this.hitbox = hitbox;
	}
	
	@Override
	public Circle getHitbox() { return hitbox; }
}