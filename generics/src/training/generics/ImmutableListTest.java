package training.generics;

import static org.junit.Assert.*;

import org.junit.Test;

public class ImmutableListTest {

	private static final ImmutableList<Integer> EMPTY = ImmutableList.empty();
	private static final ImmutableList<Integer>
		ONE_TWO_THREE = EMPTY.prepend(3).prepend(2).prepend(1),
		ZERO_ONE_TWO_THREE = ONE_TWO_THREE.prepend(0),
		ONE_FOUR_NINE = EMPTY.prepend(9).prepend(4).prepend(1);
	private static final ImmutableList<Object>
		ONE_TWO_THREE_OBJECTS = ImmutableList.empty().prepend("3").prepend("2").prepend("1");

	private static final Function<Integer, Integer>
		SQUARE_FUNCTION = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer value) {
				return value * value;
			}
		};
	
	private static final Function<Object, String>
		TO_STRING_FUNCTION = new Function<Object, String>() {
			@Override
			public String apply(Object value) {
				return value.toString();
			}
		};

	@Test
	public void listsMustShareSubstructure() {
		assertSame(ONE_TWO_THREE, ZERO_ONE_TWO_THREE.tail);
	}

	@Test
	public void testMap() {
		assertEquals(ONE_FOUR_NINE, ONE_TWO_THREE.map(SQUARE_FUNCTION));
	}

	@Test
	public void testMapTypeBounds() {
		assertEquals(ONE_TWO_THREE_OBJECTS, ONE_TWO_THREE.<Object>map(TO_STRING_FUNCTION));
	}

}
