package training.calculator;

import java.util.Stack;

public class Calculator {

	public static boolean handleNumber(String token, Stack<Integer> stack) {
		try {
			stack.push(Integer.parseInt(token));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean handleOperator(String token, Stack<Integer> stack) {
		Operator op;
		if (token.equals("+")) {
			op = Operator.ADD;
		} else if (token.equals("-")) {
			op = Operator.SUBTRACT;
		} else if (token.equals("*")) {
			op = Operator.MULTIPLY;
		} else if (token.equals("/")) {
			op = Operator.DIVIDE;
		} else {
			return false;
		}

		int rhs = stack.pop(), lhs = stack.pop();
		stack.push(op.operate(lhs, rhs));
		return true;
	}

	public static int calculate(String expression) {
		Stack<Integer> stack = new Stack<Integer>();
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
		System.out.println(calculate(args[0]));
	}

}
