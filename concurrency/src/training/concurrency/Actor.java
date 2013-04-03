package training.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class Actor implements Runnable {

	public static enum Message { SHUTDOWN }

	private final BlockingQueue<Object> messages = new LinkedBlockingQueue<Object>();

	protected abstract void receive(Object message);

	public final void tell(Object message) {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	@Override
	public final void run() {
		try {
			while (true) {
				Object message = messages.take();
				if (message == Message.SHUTDOWN)
					break;
				receive(message);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
