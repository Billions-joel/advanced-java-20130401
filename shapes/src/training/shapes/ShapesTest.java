package training.shapes;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ShapesTest {

	private static final Random RANDOM = new Random();

	/*
	 * equal rectangles must be equal
	 * equal rectangles must have equal hashcodes
	 * rectangle: setWidth shouldn't affect height and vice-versa
	 * squares must remain square
	 */

	@Test
	public void equalRectanglesMustBeEqual() {
		for (int i = 0; i < 100; i++) {
			Rectangle
				r1 = arbitraryRectangle(),
				r2 = new Rectangle(r1.getWidth(), r1.getHeight());
			assertEquals(r1, r2);
		}
	}

	@Test
	public void equalRectanglesMustHaveEqualHashcodes() {
		for (int i = 0; i < 100; i++) {
			Rectangle
				r1 = arbitraryRectangle(),
				r2 = new Rectangle(r1.getWidth(), r1.getHeight());
			assertEquals(r1.hashCode(), r2.hashCode());
		}
	}

	@Test
	public void setRectangleWidthMustNotChangeHeight() {
		for (int i = 0; i < 100; i++) {
			Rectangle
				r1 = arbitraryRectangle(),
				r2 = r1.copyWithWidth(RANDOM.nextInt(50));
			assertEquals(r1.getHeight(), r2.getHeight());
		}
	}

	private static Rectangle arbitraryRectangle() {
		return RANDOM.nextBoolean()
			? new Rectangle(RANDOM.nextInt(50), RANDOM.nextInt(50))
			: arbitrarySquare();
	}

	private static Square arbitrarySquare() {
		return new Square(RANDOM.nextInt(50));
	}
}
