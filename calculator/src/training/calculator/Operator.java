package training.calculator;

public abstract class Operator {

	public static final Operator ADD = new Operator() {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs + rhs;
		}
	};

	public static final Operator SUBTRACT = new Operator() {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs - rhs;
		}
	};

	public static final Operator MULTIPLY = new Operator() {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs * rhs;
		}
	};

	public static final Operator DIVIDE = new Operator() {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs / rhs;
		}
	};

	public abstract int operate(int lhs, int rhs);
	private Operator() {}

}
