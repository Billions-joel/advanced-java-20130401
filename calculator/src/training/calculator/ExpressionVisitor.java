package training.calculator;

public interface ExpressionVisitor<A> {

	A visitNum(Expression.Num num);
	A visitOp(Expression.Op op);


	static final ExpressionVisitor<String> PREFIX = new ExpressionVisitor<String>() {
		@Override
		public String visitNum(Expression.Num num) {
			return String.valueOf(num.value);
		}

		@Override
		public String visitOp(Expression.Op op) {
			return String.format("%s %s %s", op.op, op.lhs.accept(this), op.rhs.accept(this));
		}
	};
	
	static final ExpressionVisitor<String> INFIX = new ExpressionVisitor<String>() {
		@Override
		public String visitNum(Expression.Num num) {
			return String.valueOf(num.value);
		}

		@Override
		public String visitOp(Expression.Op op) {
			return String.format("(%s %s %s)", op.lhs.accept(this), op.op, op.rhs.accept(this));
		}
	};
	
	static final ExpressionVisitor<String> POSTFIX = new ExpressionVisitor<String>() {
		@Override
		public String visitNum(Expression.Num num) {
			return String.valueOf(num.value);
		}

		@Override
		public String visitOp(Expression.Op op) {
			return String.format("%s %s %s", op.lhs.accept(this), op.rhs.accept(this), op.op);
		}
	};

	static final ExpressionVisitor<Integer> VALUE = new ExpressionVisitor<Integer>() {
		@Override
		public Integer visitNum(Expression.Num num) {
			return num.value;
		}

		@Override
		public Integer visitOp(Expression.Op op) {
			return op.op.operate(op.lhs.accept(this), op.rhs.accept(this));
		}
	};

}
