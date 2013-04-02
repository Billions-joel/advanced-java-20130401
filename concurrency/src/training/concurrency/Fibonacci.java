package training.concurrency;

import java.math.BigInteger;
import java.util.Random;

public class Fibonacci extends Actor {

	public static enum Message { FIB }

	private static final Random RANDOM = new Random();
	private final Logger logger;

	public Fibonacci(Logger logger) {
		this.logger = logger;
	}

	private static BigInteger fib(int n) {
		return n < 2 ? BigInteger.ONE : fib(n-1).add(fib(n-2));
	}

	public BigInteger randomFib() {
		return fib(RANDOM.nextInt(35));
	}

	@Override
	protected void receive(Object message) {
		if (message == Message.FIB) {
			logger.tell(randomFib());
			tell(Message.FIB);
		}
	}

}
