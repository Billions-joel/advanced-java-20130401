package training.generics;

public class ImmutableQueue<A> {

	private static final ImmutableQueue<Object> EMPTY = new ImmutableQueue<Object>();

	private final ImmutableStack<A> in, out;

	public static class DequeueResult<A> {
		public final A head;
		public final ImmutableQueue<A> tail;

		public DequeueResult(A head, ImmutableQueue<A> tail) {
			this.head = head;
			this.tail = tail;
		}
	}

	private ImmutableQueue() {
		this.in = ImmutableStack.empty();
		this.out = ImmutableStack.empty();
	}

	private ImmutableQueue(ImmutableStack<A> in, ImmutableStack<A> out) {
		this.in = in;
		this.out = out;
	}

	public ImmutableQueue<A> enqueue(A element) {
		ImmutableStack<A> in = this.in.prepend(element);
		return new ImmutableQueue<A>(in, out);
	}

	public DequeueResult<A> dequeue() {
		ImmutableStack<A> in = this.in, out = this.out;
		if (out.size == 0) {
			while (in.size != 0) {
				out = out.prepend(in.head);
				in = in.tail;
			}
		}
		return new DequeueResult<A>(out.head, new ImmutableQueue<A>(in, out.tail));
	}

	public boolean isEmpty() {
		return in.size + out.size == 0;
	}

	@SuppressWarnings("unchecked")
	public static <A> ImmutableQueue<A> empty() {
		return (ImmutableQueue<A>) EMPTY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + in.hashCode();
		result = prime * result + out.hashCode();
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
		@SuppressWarnings("rawtypes")
		ImmutableQueue other = (ImmutableQueue) obj;
		if (!in.equals(other.in))
			return false;
		if (!out.equals(other.out))
			return false;
		return true;
	}

}
