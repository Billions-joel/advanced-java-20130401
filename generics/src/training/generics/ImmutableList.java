package training.generics;

public class ImmutableList<E> {

	private static final ImmutableList<Object> EMPTY = new ImmutableList<Object>();

	public final E head;
	public final ImmutableList<E> tail;
	public final int size;

	private ImmutableList() {
		head = null;
		tail = null;
		size = 0;
	}

	private ImmutableList(E head, ImmutableList<E> tail) {
		this.head = head;
		this.tail = tail;
		this.size = tail.size + 1;
	}

	public ImmutableList<E> prepend(E element) {
		return new ImmutableList<E>(element, this);
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
