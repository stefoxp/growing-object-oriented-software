# What Is the Point of Test-Driven Development

Software development as a Learning Process.

## Feedback

The best approach a team can take is to use **empirical feedback** to learn about the system and its use, and then apply that learning back to the system.

Development is incremental and iterative.

- Incremental development builds a system feature by feature.
Each feature is implemented as an end-to-end slice.
The system is always integrated and ready for deployment.
- Iterative development progressively refines the implementation of features in response to feedback

Two practices that support change are:

- we need constant testing to catch regression errors (automate testing)
- we need to keep the code as simple as possible.
Developers spend far more time reading code than writing it.

## Test-Driven Development (TDD)

Test-Driven Development (TDD) turns testing into a design activity.

The cycle at the heart of TDD is:

- write a failing test
- write code to get the test working
- refactor the code

TDD gives us feedback on the quality of *implementation* and *design*.

>The Golden Rule of TDD:
Newer write new functionality without a failing test.

### Refactoring

Refactoring means changing the internal structure of an existing body of code without changing its behavior.
It is a disciplined technique.
It is a micro-technique.

## Acceptance test

A project with only unit tests is missing out on critical benefits of the TDD process.

When we're implementing a feature, we start by writing an acceptance test.
Underneath the acceptance test, we follow the unit level test/implement/refactor cycle to develop the feature.

The outer test loop (acceptance test) is a measure of demonstrable progress, and the growing suite of tests protects us against regression failures when we change the system.
The inner loop (unit test) supports the developers.

>Failing unit tests should never be committed to the source repository.

An acceptance test should exercise the system end-to-end without directly calling its internal code.

A system is *deployable* when the acceptance tests all pass.

## Level of Testing

- **Acceptance** testing is to write end-to-end tests which should be as end-to-end as possible.
- **Integration** tests check how some of our code works with code from outside the team that we can't change.
In a small system, acceptance test might be enough.
In most professional development, we'll want integration tests.
- **Unit** tests.

## Quality

- **External** quality is how well the system meets the needs of its customers and users.
It's usually part of the contract to build.
- **Internal** quality is how well the system meets the needs of its developers and administrators.
The point of maintaining internal quality is to allow us to modify the system's behavior safely and predictably, because it minimizes the risk that a change will force major rework.

Running end-to-end tests tells us about the external quality.
Writing them tells us something about how well we understand the domain.

Writing unit tests gives us a lot of feedback about the quality of our code.
Running them tells us that we haven't broken any classes.

The code must be:

- **loosely coupled**.
Elements are coupled if a change in one forces a change in the other.
- **highly cohesive**
An element's cohesion is a measure of whether its responsibilities form a meaningful unit.
