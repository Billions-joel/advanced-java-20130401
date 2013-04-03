package training.calculator;

public interface ExpressionVisitor<A> {

	A visitNum(Expression.Num num);
	A visitOp(Expression.Op op);

	static final ExpressionVisitor<String> INFIX = new ExpressionVisitor<String>() {
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

	static final ExpressionVisitor<Integer> VALUE = new ExpressionVisitor<Integer>() {
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
