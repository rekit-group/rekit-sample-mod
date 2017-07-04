package rekit.mymod.pickups;

import rekit.logic.gameelements.type.Coin;
import rekit.primitives.geometry.Vec;
import rekit.primitives.image.RGBAColor;
import rekit.util.ReflectUtils.LoadMe;

/**
 * A new coin which has a value of [0,300].
 *
 * @author Dominik Fuchss
 *
 */
@LoadMe
public class MagicCoin extends Coin {
	/**
	 * The default color of the coin.
	 */
	private static RGBAColor color = new RGBAColor(0, 200, 0, 7);
	/**
	 * The shadow color of the coin.
	 */
	private static RGBAColor darkColor = new RGBAColor(0, 50, 0, 7);

	/**
	 * Prototype constructor.
	 */
	public MagicCoin() {
		super();
	}

	/**
	 * Constructor with position.
	 *
	 * @param startPos
	 *            the position.
	 */
	protected MagicCoin(Vec startPos) {
		super(startPos);
	}

	@Override
	protected RGBAColor getColor() {
		return MagicCoin.color;
	}

	@Override
	protected RGBAColor getDarkerColor() {
		return MagicCoin.darkColor;
	}

	@Override
	public Coin create(Vec startPos, String... options) {
		return new MagicCoin(startPos);
	}

	@Override
	protected int getValue() {
		return (int) (Math.random() * 300);
	}

}
