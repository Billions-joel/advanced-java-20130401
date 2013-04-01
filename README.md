Advanced Java - April 1-3, 2013
===============================

Immutability
------------

* Makes it easier to subclass correctly (LSP)
* Makes structural identity stable (safe for use in hash-based collections)
* Makes client code easier to write

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



