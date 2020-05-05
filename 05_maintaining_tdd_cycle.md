# 05 Maintaining the TDD Cycle

## Start each feature with an Acceptance Test

We start work on a new feature by writing failing acceptance tests that demonstrate that the system does not yet have the feature.

We write the acceptance test using only terminology from the application's domain.

The precision of expressing requirements in a form that can be automatically checked helps  us uncover implicit assumptions.

## Separate Tests

We organize our test suites to reflect the different roles that the tests fulfill:

- unit and integration tests support the development team, should run quickly, and should always pass
- acceptance tests for completed features catch regressions and should always pass, but might take longer to run
- new acceptance tests represent work in progress and will not pass until a feature is ready

If requirements change, we must move any affected acceptance tests out of the regression suite back into the in-progress suite, edit them to reflect the new requirements, and change the system to make them pass again.

## Start Testing with the Simplest Success Case

We find it useful to keep a notepad or index cards by the keyboard to jot down failure cases, refactorings, and other technical tasks that need to be addressed.

The feature is finished only when we've crossed off everything on the list.

## Write the Test That You'd Want to Read

While writing the test, we just concentrate on its text.

## Watch the Test Fail

We always watch the test fail before writing the code to make it pass, and check the diagnostic message.
When we get the right failure, we check that the diagnostics are helpful.
We adjust the test code and rerun the tests until the error messages guide us to the problem with the code.

The reasons for insisting on checking the error messages:

1. it checks our assumptions about the code we've working on
2. our emphasis on expressing our intentions is fundamental for developing reliable, maintainable systems

## Develop from the Inputs to the Outputs

We start developing a feature by considering the events coming into the system that will trigger the new behavior.

We will need to write one or more objects to handle these events.
We discover that these objects need supporting services from the rest of the system.
In this way, we work our way through the system.

## Unit-Test Behavior, Not Methods

Just writing lots of tests does not guarantee a codebase that's easy to work with.

One common mistake is thinking about testing methods. We do better when we focus on the features that the object under test should provide.
It helps to choose test names that describe how the object behaves in the scenario being tested.

## Listen to the Tests

When we find a feature that's difficult to test, we ask ourselves how to test it, but also why is it difficult to test.

The same structure that makes the code difficult to test now will make it difficult to change in the future.

If we're finding it hard to write the next failing test, we look again at the design of the production code and often refactor it before moving on.

>Expect Unexpected Changes

## Tuning the Cycle

There's a balance between exhaustively testing execution paths and testing integration.

The best we can get from the testing part of TDD is the confidence that we can change the code without breaking it.
