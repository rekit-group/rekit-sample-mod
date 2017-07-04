package rekit.mymod.enemies;

import java.util.Random;

import org.fuchss.configuration.Configurable;
import org.fuchss.configuration.annotations.SetterInfo;

import rekit.core.GameGrid;
import rekit.logic.gameelements.GameElement;
import rekit.logic.gameelements.entities.Player;
import rekit.logic.gameelements.type.Enemy;
import rekit.primitives.geometry.Direction;
import rekit.primitives.geometry.Frame;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

/**
 * Sample Enemy that has rudimentary functionality. It bounces on the floor and
 * damages if hit from below or the sides but is vulnerable from the top.
 *
 * See the configuration file in src/main/resources/conf/bouncer.properties
 *
 * @author Angelo Aracri
 */
@SetterInfo(res = "conf/bouncer")
@LoadMe
public final class Pizza extends Enemy implements Configurable {

	/**
	 * The score the {@link Player} receives upon killing this {@link Enemy}. Is
	 * configurable in src/main/resources/conf/pizza.properties
	 */
	public static int POINTS;

	/**
	 * The {@link RGBAColor} of the {@link Enemy}. Is configurable in
	 * src/main/resources/conf/pizza.properties
	 */
	public static RGBAColor COLOR;

	/**
	 * The size of the {@link Enemy} as a two dimensional {@link Vec}. Is
	 * configurable in src/main/resources/conf/pizza.properties
	 */
	public static Vec SIZE;
	
	private static int ITERATIONS = 5;
	
	private static Random RNG = new Random();
	
	private float[] radius;
	
	private float currentAngle;
	
	private static float ANGLE_SPEED = 0.02f;
	
	public Pizza() {
		super();
		this.init();
	}

	/**
	 * Standard Constructor that saves the initial position.
	 *
	 * @param startPos
	 *            the initial position of the Enemy.
	 */
	public Pizza(Vec startPos) {
		super(startPos, new Vec(), Pizza.SIZE);
		
		this.init();
	}
	
	private void init() {
		radius = new float[Pizza.ITERATIONS];
		for (int i = 0; i < Pizza.ITERATIONS; i++) { 
			this.radius[i] = 0.1f + Pizza.RNG.nextFloat() * 0.2f;
		}
	}

	@Override
	public void internalRender(GameGrid f) {
		f.drawCircle(this.getPos(), this.getSize(), new RGBAColor(226, 150, 68, 255));
		f.drawCircle(this.getPos(), this.getSize().scalar(0.85f), new RGBAColor(248, 199, 101, 255));
		
		
		float phi = currentAngle;
		for (int i = 0; i < Pizza.ITERATIONS; i++) { 
			float rad = this.radius[i];
			
			Vec pos = new Vec(Math.sin(phi) * rad, Math.cos(phi) * rad);
			f.drawCircle(this.getPos().add(pos), new Vec(0.1f, 0.1f), new RGBAColor(213, 87, 65, 255));
			
			phi += 2 * Math.PI / (float) Pizza.ITERATIONS;
		}
		// f.drawCircle(pos, size, color);
		// f.drawRectangle(pos, size, color);
		// f.drawRoundRectangle(pos, size, color, arcWidth, arcHeight);
		// f.drawLine(a, b, ingame, usefilter);
		// f.drawPath(startPos, pts, color, usefilter);
		// f.drawPolygon(polygon, color, fill);
		// f.drawText(arg0, arg1, arg2, arg3);
	}

	@Override
	protected void innerLogicLoop() {
		// Do usual entity logic
		super.innerLogicLoop();
		
		this.currentAngle += this.deltaTime * ANGLE_SPEED;
	}

	@Override
	public void reactToCollision(GameElement element, Direction dir) {
		// If the element is hostile to the enemy (meaning element is Player)
		if (this.getTeam().isHostile(element.getTeam())) {
			// If hit from above:
			if (dir == Direction.UP) {
				// give the player points
				this.getScene().getPlayer().addPoints(Pizza.POINTS);
				// Let the player jump jump
				element.killBoost();
				// kill the enemy
				this.addDamage(1);
			} else {
				// Touched dangerous side
				// Give player damage
				element.addDamage(1);
				// Kill the enemy itself
				this.addDamage(1);
			}
		}
	}

	@Override
	public void collidedWith(Frame collision, Direction dir) {
		super.collidedWith(collision, dir);
		this.setVel(new Vec(0, -15f));
	}

	@Override
	public Pizza create(Vec startPos, String... options) {
		return new Pizza(startPos);
	}
}
