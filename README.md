Advanced Java - April 1-3, 2013
===============================

Immutability
------------

* Makes it easier to subclass correctly (LSP)
* Makes structural identity stable (safe for use in hash-based collections)
* Makes client code easier to write
* Immutable objects can be shared safely between threads

Liskov Substitution Principle
-----------------------------

Code that works for instances of Base should work just fine for instances of Derived. In other words: Derived instances should comply with Base class contracts.

Object Identity
---------------

* Reference Identity
  - `a == b`
* Structural Identity (or Value Identity)
  - `a.equals(b)`

Polymorphism
------------

* Subtype polymorphism (subclass - superclass relationship)
* Parametric polymorphism (generics)
* When the two collide, you have to pay attention to type bounds

Concurrency
-----------

* Traditional lock-based concurrency is too damn hard.
* Use specialized classes from `java.util.concurrent.*` (e.g. `AtomicReference`) when appropriate.
* In general, prefer actors:
  - Generalization of the Producer-Consumer pattern.
  - Mutable state is encapsulated by an actor, and never thread-shared.
  - An actor's internal state changes only in response to messages.
  - Implemented using a message queue: `java.util.concurrent.BlockingQueue`

Design Patterns
---------------

* Strategy: encapsulate varying behavior in an object.
* (Enum) Singleton: provide a fixed set of instances.
* Composite: recursive data structures (e.g. syntax trees, widget layouts).
* Visitor: composite + strategy. Allow traversal of composites with arbitrary behavior.
* Adapter: wraps instance to provide different API.
* Decorator: wraps instance to provide additional bells and whistles.
