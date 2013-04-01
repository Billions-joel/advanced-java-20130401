package training.generics;

import org.junit.Test;

public class Bounds {

	class Dog {}
	class Puppy extends Dog {}

	class Box<E> {
		E element;
	}

	Box<Dog> dogBox = new Box<Dog>();
	Box<Puppy> puppyBox = new Box<Puppy>();
	
	Dog[] dogArray = new Dog[1];
	Puppy[] puppyArray = new Puppy[1];

	// upper-bounded wildcard
	Dog getDog(Box<? extends Dog> dogBox) {
		return dogBox.element;
	}

	Dog getDog(Dog[] dogArray) {
		return dogArray[0];
	}

	/* upper-bounded type parameter
	<D extends Dog> D getDog(Box<D> dogBox) {
		return dogBox.element;
	}
	*/

	void putDog(Box<Dog> dogBox) {
		dogBox.element = new Dog();
	}

	void putDog(Dog[] dogArray) {
		dogArray[0] = new Dog();
	}

	Puppy getPuppy(Box<Puppy> puppyBox) {
		return puppyBox.element;
	}

	Puppy getPuppy(Puppy[] puppyArray) {
		return puppyArray[0];
	}

	// lower-bounded wildcard
	void putPuppy(Box<? super Puppy> puppyBox) {
		puppyBox.element = new Puppy();
	}

	void putPuppy(Puppy[] puppyArray) {
		puppyArray[0] = new Puppy();
	}

	/* lower-bounded type parameter DOESN'T EXIST!!!
	<P super Puppy> void putPuppy(Box<P> puppyBox) {
		puppyBox.element = new Puppy();
	}
	*/

	@Test
	public void test() {
		getDog(dogBox);
		putDog(dogBox);
		getDog(puppyBox);
		// putDog(puppyBox); -- dangerous for the dog

		getDog(puppyArray);
		// putDog(puppyArray); -- dangerous for the dog, but the compiler allows this (throws ArrayStoreException)!

		getPuppy(puppyBox);
		putPuppy(puppyBox);
		// getPuppy(dogBox); -- dangerous for you
		putPuppy(dogBox);

		// getPuppy(dogArray);
		// putPuppy(dogArray); -- should be safe for you, but the compiler disallows this!
	}
}
