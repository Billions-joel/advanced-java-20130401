package training.calculator;

import java.util.Stack;

public class Calculator {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Calculator <expression>");
			return;
		}

		Stack<Integer> stack = new Stack<Integer>();
		for (String token : args[0].split(" ")) {
			try {
				stack.push(Integer.parseInt(token));
			} catch (NumberFormatException e) {
				if (token.equals("+")) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs + rhs);
				} else if (token.equals("-")) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs - rhs);
				} else if (token.equals("*")) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs * rhs);
				} else if (token.equals("/")) {
					int rhs = stack.pop(), lhs = stack.pop();
					stack.push(lhs / rhs);
				} else {
					throw new IllegalArgumentException("invalid token: " + token);
				}
			}
		}
		
		System.out.println(stack.pop());
	}

}
