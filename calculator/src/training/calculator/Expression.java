package training.calculator;

public interface Expression {

	<A> A accept(ExpressionVisitor<A> fn);

	static final class Num implements Expression {
		public final int value;

		public Num(int value) {
			this.value = value;
		}

		@Override
		public <A> A accept(ExpressionVisitor<A> fn) {
			return fn.visitNum(this);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + value;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Num other = (Num) obj;
			if (value != other.value)
				return false;
			return true;
		}

	}

	static final class Op implements Expression {
		public final Operator op;
		public final Expression lhs, rhs;

		public Op(Operator op, Expression lhs, Expression rhs) {
			this.op = op;
			this.lhs = lhs;
			this.rhs = rhs;
		}

		@Override
		public <A> A accept(ExpressionVisitor<A> fn) {
			return fn.visitOp(this);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + lhs.hashCode();
			result = prime * result + op.hashCode();
			result = prime * result + rhs.hashCode();
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Op other = (Op) obj;
			if (!lhs.equals(other.lhs))
				return false;
			if (op != other.op)
				return false;
			if (!rhs.equals(other.rhs))
				return false;
			return true;
		}
	}

}
