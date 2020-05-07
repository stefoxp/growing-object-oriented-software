# 08 Building on Third-Party Code

The critical point about third-party code is that we don't control it.

We must focus on the integration between our design and the external code.
In integration, we have an abstraction to implement.

We must check that we are using the third-party API correctly, and adjust our abstraction to fit if we find that our assumptions are incorrect.

## Only mock types that you own

### Don't mock types you can't change

Providing mock implementations of third-party types is of limited use when unit-testing the objects that call them. We find that tests that mock external libraries often need to be complex to get the code into the right state.

A second risk is that we have to be sure that the behavior we stub or mock matches what the external library will actually do.

Even if we get it right once, we have to make sure that the tests remain valid when we upgrade the libraries.

### Write an adapter layer

We will have used TDD to design interfaces for the services our objects need.
We write a layer of *adapter objects* that uses the third-party API to implement these interfaces.
We keep this layer as thin as possible.
We test these adapters with focused integration tests to confirm our understanding of how the third-party API works.

There are some exceptions where mocking third-party libraries can be helpful:

- we might use mocks to simulate behavior that is hard to trigger with the real library
- we might use mocks to test a sequence of calls
