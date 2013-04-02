package training.concurrency;

import java.math.BigInteger;
import java.util.Random;

public class Fibonacci implements Runnable {

	private static final Random RANDOM = new Random();
	// TODO: hold a reference to logger, and call logger.log in run() method

	private static BigInteger fib(int n) {
		return n < 2 ? BigInteger.ONE : fib(n-1).add(fib(n-2));
	}

	public BigInteger randomFib() {
		return fib(RANDOM.nextInt(35));
	}

	@Override
	public void run() {
		System.out.println(randomFib());
	}
}
