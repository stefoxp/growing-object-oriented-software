# 06 Object-Oriented Style

We value code that is easy to maintain.

## Design for Maintainability

We grow our systems a slice of functionality at a time.

We use two principal heuristics to guide this structuring:

- separation of concerns
- higher levels of abstraction

We write interfaces to describe its relationships with the outside world in its terminology (Cockburn 's *ports*). Then we write bridges between the application core and each technical domain (Cockburn 's *adapters*).
The bridges implement the interfaces defined by the application model and map between application-level and technical-level objects.

We have some second-level heuristics to help us think about how to find the facets in the behavior where the interfaces should be:

- encapsulation ensures that the behavior of an object can only be affected through its API
- information hiding conceals how an object implements its functionality behind the abstraction of its API

We follow standard practices to maintain encapsulation when coding:

- define immutable value types
- avoid global variables and singletons
- copy collections and mutable values when passing them between objects, and so on

## Internals vs Peer

We must decide what is inside and outside each object.
An object communicates with other objects in the system by sending and receiving messages.
The objects it communicates with directly are its *peers*.

This decision affects how easy an object is to use, and contributes to the internal quality of the system.

## Single responsibility principle

Every object should have a single, clearly defined responsibility.
We should be able to describe what an object does without using any conjunctions.

## Object peer Stereotypes

We categorize an object's peers into three types of relationship:

1. dependencies
The object cannot function without these services.
2. notifications
The object will notify interested peers whenever it changes state or performs a significant action.
Notifications are useful because they decouple objects from each other.
3. adjustments
Peers that adjust the object's behavior to the wider needs of the system.

These stereotypes are only heuristics to help us think about the design, not hard rules.

## Composite simpler than the sum of its parts

The API of a composite object should not be more complicated than of any of its components.

As we grow the code, the composite simpler ... rule contributes to raising the level of abstraction.

## Context independence

A system is easier to change if its objects are context-independent: each object has no built-in knowledge about the system.

The effect of the context-independence rule on a system of objects is to make their relationships explicit, defined separately from the objects themselves.
This simplifies the objects and managing the relationships.

## Hiding the right information

Encapsulation is almost always a good thing to do, but sometimes information can be hidden in the wrong place.
