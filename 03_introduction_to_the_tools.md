# 03 An Introduction to the Tools

We're going to use Java, with the JUnit 4, Hamcrest, and jMock2 frameworks.

## JUnit 4

JUnit use reflection to walk the structure of a class.

```java
public class CatalogTest {
    private final Catalog catalog = new Catalog();

    @Test
    public void containsAnAddedEntry() {
        Entry entry = new Entry("fish", "chips");
        catalog.add(entry);
        assertTrue(catalog.contains(entry));
    }

    @Test
    public void indexesEntriesByName() {
        Entry entry = new Entry("fish", "chips");
        catalog.add(entry);
        assertEquals(entry, catalog.entryFor("fish"));
        assertNull(catalog.entryFor("missing name"));
    }
}
```

### Test Cases

JUnit treats any method annotated with @Test as a test case.
To run a test, JUnit creates a new instance (this ensures that the tests are isolated) of the test class and calls the relevant test method.

### Assertions

### Expecting Exceptions

The @Test annotation supports an optional parameter *expected* that declares that the test case should throw an exception.
The test fail if it does not throw an exception or if it throws an exception of a different type.

```java
@Test(expected=IllegalArgumentException.class)
public void cannotAddTwoEntriesWithTheSameName() {
    catalog.add(new Entry("fish", "chips"));
    catalog.add(new Entry("fish", "peas"));
}
```

### Text Fixtures

A text fixture is the fixed state that exists at the start of a test.
It ensures that a test is repeatable.
The fixture is usually set up by field initializers. It can also be set up by the constructor of the test class or instance initializer blocks.

JUnit also lets you identify methods that set up and tear down the fixture with annotations (@Before and @After).

```java
public class CatalogTest {
    private final Catalog catalog = new Catalog();
    final Entry entry = new Entry("fish", "chips");

    @Before
    public void fillTheCatalog() {
        catalog.add(entry);
    }

    @Test
    public void containsAnAddedEntry() {
        assertTrue(catalog.contains(entry));
    }

    @Test
    public void indexesEntriesByName() {
        assertEquals(entry, catalog.entryFor("fish"));
        assertNull(catalog.entryFor("missing name"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotAddTwoEntriesWithTheSameName() {
        catalog.add(new Entry("fish", "peas"));
    }
}
```

### Text Runners

The runner used for a class can be configured with the @RunWith annotation.

## Hamcrest

Hamcrest is a framework for writing declarative match criteria.
A Hamcrest *matcher* reports whether a given object matches some criteria, can describe its criteria, and can describe why an object does not meet its criteria.
We use matchers in combination with JUnit's assertThat(), which uses self-describing features of the matcher to make clear exactly what wrong when an assertion fails.

Hamcrest is also user-extensible.

## jMock2

jMock creates mock objects dynamically.
It also provides a high-level API.

The core concepts of the jMock API are:

- mockery -> represents the context of the object under test
- mock objects -> stand in for the real neighbors of the object under test
- expectations -> describe how the object under test should invoke its neighbors during the test

By convention, jMock tests hold the mockery in a field named context.

Expectation API is very expressive.
It lets you specify:

- the min and max number of times an invocation is expected
- whether an invocation is expected or merely allowed to happen
- the parameter values
- the ordering constraints with respect to other expectations
- what should happen when the method is invoked
