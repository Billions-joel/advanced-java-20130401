package training.clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Map;
import java.util.WeakHashMap;

import training.concurrency.Actor;

public class Fibonacci extends Actor {

	private final Map<Integer, BigInteger> cache = new WeakHashMap<Integer, BigInteger>();

	private BigInteger fib(int n) {
		BigInteger result = cache.get(n);
		if (result == null) {
			result = n < 2 ? BigInteger.ONE : fib(n - 1).add(fib(n - 2));
			cache.put(n, result);
		}
		return result;
	}

	@Override
	protected void receive(Object message) {
		if (message instanceof Socket) {
			try {
				Socket client = (Socket) message;
				try {
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					try {
						int i = (Integer) in.readObject();
						ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
						out.writeObject(fib(i));
					} catch (ClassNotFoundException e) {
					} finally {
						in.close();
					}
				} finally {
					client.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
