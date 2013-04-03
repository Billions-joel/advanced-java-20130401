package training.calculator;

import java.util.Stack;
import static training.calculator.ExpressionFunction.*;

public class Calculator {

	public static boolean handleNumber(String token, Stack<Expression> stack) {
		try {
			stack.push(new Expression.Num(Integer.parseInt(token)));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean handleOperator(String token, Stack<Expression> stack) {
		Operator op = Operator.get(token);
		if (op == null)
			return false;

		Expression rhs = stack.pop(), lhs = stack.pop();
		stack.push(new Expression.Op(op, lhs, rhs));
		return true;
	}

	public static Expression parse(String expression) {
		Stack<Expression> stack = new Stack<Expression>();
		for (String token : expression.split(" ")) {
			if (!handleNumber(token, stack) && !handleOperator(token, stack)) {
				throw new IllegalArgumentException("invalid token: " + token);
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Calculator <expression>");
			return;
		}
		Expression e = parse(args[0]);
		System.out.println(e.accept(INFIX) + " = " + e.accept(VALUE));
	}

}
