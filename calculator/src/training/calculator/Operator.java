package training.calculator;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

	ADD("+") {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs + rhs;
		}
	},
	SUBTRACT("-") {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs - rhs;
		}
	},
	MULTIPLY("*") {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs * rhs;
		}
	},
	DIVIDE("/") {
		@Override
		public int operate(int lhs, int rhs) {
			return lhs / rhs;
		}
	};

	private static final Map<String, Operator> operators;
	static {
		operators = new HashMap<String, Operator>();
		for (Operator op : values()) {
			operators.put(op.token, op);
		}
	}

	private final String token;
	private Operator(String token) {
		this.token = token;
	}

	public static Operator get(String token) {
		return operators.get(token);
	}

	@Override
	public String toString() {
		return token;
	}

	public abstract int operate(int lhs, int rhs);
}
