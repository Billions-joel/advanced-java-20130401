package training.generics;

public class Bounds {

	class Dog {}
	class Puppy extends Dog {}

	class Box<E> {
		E element;
	}

	Box<Dog> dogBox = new Box<Dog>();
	Box<Puppy> puppyBox = new Box<Puppy>();

	// upper-bounded wildcard
	Dog getDog(Box<? extends Dog> dogBox) {
		return dogBox.element;
	}

	/* upper-bounded type parameter
	<D extends Dog> D getDog(Box<D> dogBox) {
		return dogBox.element;
	}
	*/

	void putDog(Box<Dog> dogBox) {
		dogBox.element = new Dog();
	}

	Puppy getPuppy(Box<Puppy> puppyBox) {
		return puppyBox.element;
	}

	// lower-bounded wildcard
	void putPuppy(Box<? super Puppy> puppyBox) {
		puppyBox.element = new Puppy();
	}

	/* lower-bounded type parameter DOESN'T EXIST!!!
	<P super Puppy> void putPuppy(Box<P> puppyBox) {
		puppyBox.element = new Puppy();
	}
	*/

	void test() {
		getDog(dogBox);
		putDog(dogBox);
		getDog(puppyBox);
		// putDog(puppyBox); -- dangerous for the dog
		
		getPuppy(puppyBox);
		putPuppy(puppyBox);
		// getPuppy(dogBox); -- dangerous for you
		putPuppy(dogBox);
	}
}
