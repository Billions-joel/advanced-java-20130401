package training.calculator;

public interface ExpressionFunction<A> {
	A apply(Expression e);

	static final ExpressionFunction<String> INFIX = new ExpressionFunction<String>() {
		@Override
		public String apply(Expression e) {
			return null; // TODO
		}
	};

	static final ExpressionFunction<Integer> VALUE = new ExpressionFunction<Integer>() {
		@Override
		public Integer apply(Expression e) {
			return null; // TODO
		}
	};

}
