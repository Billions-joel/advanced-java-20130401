package training.concurrency;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Fibonacci extends Actor {

	public static enum Message { FIB }

	private static final Random RANDOM = new Random();
	private final Logger logger;
	private final Map<Integer, BigInteger> cache = new HashMap<Integer, BigInteger>();

	public Fibonacci(Logger logger) {
		this.logger = logger;
	}

	private BigInteger fib(int n) {
		BigInteger result = cache.get(n);
		if (result == null) {
			result = n < 2 ? BigInteger.ONE : fib(n-1).add(fib(n-2));
			cache.put(n, result);
		}
		return result;
	}

	public BigInteger randomFib() {
		return fib(RANDOM.nextInt(1000));
	}

	@Override
	protected void receive(Object message) {
		if (message == Message.FIB) {
			logger.tell(randomFib());
			tell(Message.FIB);
		}
	}

}
