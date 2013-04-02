package training.generics;

public class ImmutableStack<A> {

	private static final ImmutableStack<Object> EMPTY = new ImmutableStack<Object>();

	public final A head;
	public final ImmutableStack<A> tail;
	public final int size;

	private ImmutableStack() {
		head = null;
		tail = null;
		size = 0;
	}

	private ImmutableStack(A head, ImmutableStack<A> tail) {
		this.head = head;
		this.tail = tail;
		this.size = tail.size + 1;
	}

	public ImmutableStack<A> prepend(A element) {
		return new ImmutableStack<A>(element, this);
	}

	public <B> ImmutableStack<B> map(Function<? super A, ? extends B> fn) {
		return size == 0
			? ImmutableStack.<B>empty()
			: tail.map(fn).prepend(fn.apply(head));
	}

	@SuppressWarnings("unchecked")
	public static <A> ImmutableStack<A> empty() {
		return (ImmutableStack<A>) EMPTY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		result = prime * result + ((tail == null) ? 0 : tail.hashCode());
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
		ImmutableStack other = (ImmutableStack) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		if (tail == null) {
			if (other.tail != null)
				return false;
		} else if (!tail.equals(other.tail))
			return false;
		return true;
	}

}
