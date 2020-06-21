package space.entity;

import space.geometry.Hitbox;
import space.gui.DrawEntity;

public class Entity {
	public final Hitbox hitbox;
	public final DrawEntity drawEntity;
	
	public Entity(Hitbox hitbox, DrawEntity drawEntity) {
		this.hitbox = hitbox;
		this.drawEntity = drawEntity;
	}
	
	public Hitbox getHitbox() { return hitbox; }
}