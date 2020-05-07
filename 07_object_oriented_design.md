# 07 Achieving Object-Oriented Design

A system built from such components will have the flexibility to reconfigure and adapt as requirements change.

There are three aspects of TDD that help us achieve this scoping:

1. starting with a test means that we have to describe what we want to achieve before we consider how
2. to keep unit tests understandable we have to limit their scope
3. to construct an object for a unit test, we have to pass its dependencies to it
This encourages context independence.

## Communication over Classification

We can use interfaces to define the available messages between objects, but we also need to define their patterns of communication: **communication protocols**.

We use TDD with mock objects as a technique to make these communication protocols visible, as a tool for discovering them during development and as a description when revisiting the code.

```java
@Test
public void notifiesAuctionClosedWhenMessageReceived() {
    Message message = new Message();
    message.setBody("SOLVersion: 1.1; Event: CLOSE;");

    // this test says that the listener protocol requires that auctionClosed() should be called on its own
    context.checking(new Expectations() {{
        oneOf(listener).auctionClosed();
    }})

    translator.processMessage(UNUSED_CHAT, message);
}
```

TDD with mock objects also encourages information hiding.

## Value types

We're convinced that we should define types to represent value concepts in the domain.

Specific types help:

- to find all the code that's relevant for a change without having to chase through the method calls
- to reduce the risk of confusion
- to use a more object-oriented approach instead of scattering related behavior across the code

We use three basic techniques for introducing value types:

### Breaking out

When we find that the code in an object is becoming complex, that's a sign that it's implementing multiple concerns and that we can break out coherent units of behavior into helper types.

### Buffing off

When we want to mark a new domain concept in the code, we often introduce a placeholder type that wraps a single field.
With each type that we add, we're raising the level of abstraction of the code.

### Bundling up

When we notice that a group of values are always used together, we take that as a suggestion that there's a missing construct.
A first step might be to create a new type with fixed public fields. Later we can migrate behavior to the new type.

We find that the discovery of value types is usually motivated by trying to follow our design principles.

## Where do object come from

The categories for discovering object types are similar except that the design guidance we get from writing unit tests tend to be more important.

### Breaking out: splitting a large object into a group of collaborating objects

When starting a new area of code, we might temporarily suspend our design judgement and just write code.

We have two concerns about deferring cleanup:

1. how long we should wait before doing something
2. occasionally is better to treat this code as a spike: once we know what to do, just roll it back and re-implement cleanly.

>Break up an object if it becomes too large to test easily, or if its test failures become difficult to interpret.

### Budding off: defining a new service that an object needs and adding a new object to provide it

We might be adding behavior to an object and find that some new feature doesn't belong inside it.
Our response is to create an interface to define the service that the object needs *from the object's point of view*.
We write tests for the new behavior as if the service already exists, using mock objects to help describe the relationship between the target object and its new collaborator.
We think of this as *on-demand* design.

>When writing a test, we ask ourselves, "If this worked, who would know?". If the right answer to that question is not in the target object, it's probably time to introduce a new collaborator.

### Bundling up: hiding related objects into a containing object

When we have a cluster of related objects that work together, we can package them up in a containing object.
The new object hides the complexity in an abstraction.

This process has some other nice effects:

- we have to give it a name which helps us understand the domain a little better
- we can scope dependencies more clearly
- we can be more precise with our unit testing

When the test for an object becomes too complicated to set up consider building up some of the collaborating objects.

## Identify relationships with interfaces

We use interfaces to name the roles that objects can play and to describe the messages they'll accept.
We also prefer interfaces to be as narrow as possible.

## Refactor interfaces too

If similar interfaces turno out  to represent different concepts, we can make a point of making them distinct.
A decision to separate similar-looking interfaces is a good time to reconsider their naming.

Another time to consider refactoring interfaces is when we start implementing them.

## Compose objects to describe system behavior

TDD at the unit test level guides us to decompose our system into value types and loosely coupled computational objects.

In jMock we assemble a description of the expected calls for a test in a context object called a Mockery.
During a test run, the Mockery will pass calls made to any of its mocked objects to its Expectations, each of which will attempt to match the call.
If an Expectation matches, that part of the test succeds else, each Expectation reports its disagreement and the test fails.

The advantage of this approach is that we end up with a flexible application structure built from relatively little code. It's particularly suitable where the code has to support many related scenarios.

```java
// jMock check that a method example.doSomething() is called exactly once with an argument of type String
InvocationExpectation expectation = new InvocationExpectation();
expectation.setParameterMatcher(new AllParametersMatcher(Array.asList(new IsInstanceOf(String.class)));
    expectation.setCardinality(new Cardinality(1, 1));
    expectation.setMethodMatcher(new MethodNameMatcher("doSomething"));
    expectation.setObjectMatcher(new IsSame<Example>(example));

    context.addExpectation(expectation);
)
```

## Building up to higher-level programming

The mainstream languages we usually work with bury the information we care about (object and their relationships) in a morass of keywords, setters, punctuation, and the like.
Just assigning and linking objects doesn't help us understand the behavior of the system we're assembling.

Our response is to organize the code into two layers.

### Implementation layer

Is the graph of objects, its behavior is the combined result of how its objects respond to events.

### Declarative layer

It builds up the objects in the implementation layer, using small sugar methods and syntax to describe the purpose of each fragment.

It is, in effect, a small *domain-specific language* embedded.

The declarative layer describes *what* the code will do, while the implementation layer describes *how* the code does it.

The different purposes of the two layers mean that we use a different coding style for each.
For the implementation layer we stick to the conventional object-oriented style guidelines.
For the declarative layer we're more flexible.

A good example is jMock itself.
We can rewrite the last example as:

```java
// jMock check that a method example.doSomething() is called exactly once with an argument of type String
// using "train wreck" in the declarative layer
context.checking(new Expectations() {{
    oneOf(example).doSomething(with(any(String.class)));
}});
```

Most of the time, such a declarative layer emerges from continual merciless refactoring.
Taking care to notice when an area of code is not clear, we add or move structure until it is.
