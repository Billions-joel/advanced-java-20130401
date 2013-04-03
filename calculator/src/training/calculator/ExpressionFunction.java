package training.calculator;

public interface ExpressionFunction<A> {
	A apply(Expression e);

	static final ExpressionFunction<String> INFIX = new ExpressionFunction<String>() {
		@Override
		public String apply(Expression e) {
			if (e instanceof Expression.Num) {
				Expression.Num num = (Expression.Num) e;
				return String.valueOf(num.value);
			} else if (e instanceof Expression.Op) {
				Expression.Op op = (Expression.Op) e;
				return String.format("(%s %s %s)", op.lhs.accept(this), op.op, op.rhs.accept(this));
			}
			return null;
		}
	};

	static final ExpressionFunction<Integer> VALUE = new ExpressionFunction<Integer>() {
		@Override
		public Integer apply(Expression e) {
			if (e instanceof Expression.Num) {
				Expression.Num num = (Expression.Num) e;
				return num.value;
			} else if (e instanceof Expression.Op) {
				Expression.Op op = (Expression.Op) e;
				return op.op.operate(op.lhs.accept(this), op.rhs.accept(this));
			}
			return null;
		}
	};

}
