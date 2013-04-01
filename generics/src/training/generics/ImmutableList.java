package training.generics;

public class ImmutableList<A> {

	private static final ImmutableList<Object> EMPTY = new ImmutableList<Object>();

	public final A head;
	public final ImmutableList<A> tail;
	public final int size;

	private ImmutableList() {
		head = null;
		tail = null;
		size = 0;
	}

	private ImmutableList(A head, ImmutableList<A> tail) {
		this.head = head;
		this.tail = tail;
		this.size = tail.size + 1;
	}

	public ImmutableList<A> prepend(A element) {
		return new ImmutableList<A>(element, this);
	}

	public <B> ImmutableList<B> map(Function<A, B> fn) {
		return size == 0
			? ImmutableList.<B>empty()
			: tail.map(fn).prepend(fn.apply(head));
	}

	@SuppressWarnings("unchecked")
	public static <E> ImmutableList<E> empty() {
		return (ImmutableList<E>) EMPTY;
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
		ImmutableList other = (ImmutableList) obj;
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
