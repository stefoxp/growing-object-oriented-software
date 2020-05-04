# 02 Test-Driven Development with Objects

Object-oriented design focuses more on the communication between objects than on the objects themselves.

An object communicates by messages, it has a *method* of handling every type of message that it understands.

An object-oriented system is a web of collaborating objects.

We can change the behavior of the system by changing the composition of its objects.

- *values* model unchanging quantities or measurements
- *objects* have an identity and model computational processes

Our objects should be easily pluggable -> they follow common *communication patterns*.

The **domain model** is in these communication patterns.

An object is an implementation of one or more roles:

- *roles* a set of related responsibilities
- *responsibility* is an obligation to perform a task or know information
- *collaboration* is an interaction of objects or roles

## Law of Demeter - Tell, Don't Ask

>Objects make their decisions based only on the information they hold internally or that which came with the triggering message.

This style produces more flexible code because it's easy to swap objects that play the same role.
The caller sees nothing of their internal structure or the structure of the rest of the system behind the role interface.

```java
// train wreck problem:

((EditSaveCustomizer) master.getModelisable()
  .getDockablePanel()
    .getCustomizer())
      .getSaveItem().setEnabled(Boolean.FALSE.booleanValue());

// solution with Law of Demeter

// this wraps all that implementation detail up behind a single call
master.allowSavingOfCustomisations();
```

We ask when :

- getting information from values and collections
- using a factory to create new objects
- searching or filtering

```java
public class Train {
    private final List<Carriage> carriages[...]
    private int percentReservedBarrier = 70;

    public void reserveSeats(ReservationRequest request) {
        for (Carriage carriage : carriages) {
            if (carriage.getSeats().getPercentReserved() < percentReservedBarrier) {
                request.reserveSeatsIn(carriage);
                return;
            }
        }
        request.cannotFindSeats();
    }
}

// we shouldn't expose the internal structure of Carriage to implement this. Instead, we should ask the question we really want answered
 public void reserveSeats(ReservationRequest request) {
        for (Carriage carriage : carriages) {
            // adding a query method moves the behavior to the most appropriate object, gives it an explanatory name, and makes it easier to test
            if (carriage.hasSeatsAvailableWithin(percentReservedBarrier) {
                // ...
            }
        }
       // ...
    }
```

We can testing the collaborating objects to replace the target object's neighbors in a test with substitutes (**mock objects**).
We can specify how we expect the target object to communicate with its mock neighbors for a triggering event -> *expectations*.

With this infrastructure in place, we can change the way we approach TDD.
We can use the test to help us tease out the supporting roles our object needs, defined as Java interfaces, and fill in real implementations as we develop the rest of the system -> *interface discovery*.

The essential structure of a test is:

- create any required mock objects;
- create any real objects;
- specify how you expect the mock objects to be called by the target object;
- call the triggering method on the target object;
- assert that any resulting values are valid and that all the expected calls have been made;
