/*
 * Created on May 26, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions.examples.advanced;

import static java.lang.String.format;

import org.fest.assertions.api.AbstractAssert;
import org.fest.assertions.core.WritableAssertionInfo;
import org.fest.assertions.examples.data.TolkienCharacter;
import org.fest.assertions.internal.Objects;

/**
 * A simple class to illustrate how to extend Fest assertions with custom ones to make assertions speicific to
 * {@link TolkienCharacter}.
 * 
 * @author Joel Costigliola
 */
// 1 - Remember to inherits from AbstractAssert !
public class TolkienCharacterAssert extends AbstractAssert<TolkienCharacterAssert, TolkienCharacter> {

  // 2 - Write a constructor to build your assertion class from the object you want make assertions on.
  /**
   * Creates a new </code>{@link TolkienCharacterAssert}</code> to make assertions on actual TolkienCharacter.
   * @param actual the TolkienCharacter we want to make assertions on.
   */
  public TolkienCharacterAssert(TolkienCharacter actual) {
    super(actual, TolkienCharacterAssert.class);
  }

  // 3 - A fluent entry point to your specific assertion class
  // the other option here is to gather all your specific assertions entry point to a class inheriting from Assertions
  // thus this class will be your only entry point to all Fest assertions and YOURS.
  // see MyProjectAssertions for an example.
  /**
   * An entry point for TolkienCharacterAssert to follow Fest standard <code>assertThat()</code> statements.<br>
   * With a static import, one's can write directly : <code>assertThat(frodo).hasName("Frodo");</code>
   * @param actual the TolkienCharacter we want to make assertions on.
   * @return a new </code>{@link TolkienCharacterAssert}</code>
   */
  public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
    return new TolkienCharacterAssert(actual);
  }

  // 4 - a specific assertion !
  /**
   * Verifies that the actual TolkienCharacter's name is equal to the given one.
   * @param name the given name to compare the actual TolkienCharacter's name to.
   * @return this assertion object.
   * @throws AssertionError - if the actual TolkienCharacter's name is not equal to the given one.
   */
  public TolkienCharacterAssert hasName(String name) {
    // check that actual TolkienCharacter we want to make assertions on is not null.
    isNotNull();

    // TODO : EASE WRITING NEW ASSERTIONS
    // we overrides the default error message with a more explicit one
    String errorMessage = format("Expected character's name to be <%s> but was <%s>", name, actual.getName());

    // option 1 : raw assertion
    if (!actual.getName().equals(name)) { throw new AssertionError(errorMessage); }

    // option 2 : use of other Fest assertions (integrate better with custom comparator triggered by usingComparator)
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.overridingErrorMessage(errorMessage);
    Objects.instance().assertEqual(info, actual.getName(), name);

    // return the current assertion for method chaining
    return this;
  }

  // 4 - another specific assertion !
  /**
   * Verifies that the actual TolkienCharacter's age is equal to the given one.
   * @param age the given age to compare the actual TolkienCharacter's age to.
   * @return this assertion object.
   * @throws AssertionError - if the actual TolkienCharacter's age is not equal to the given one.
   */
  public TolkienCharacterAssert hasAge(int age) {
    // check that actual TolkienCharacter we want to make assertions on is not null.
    isNotNull();

    // we overrides the default error message with a more explicit one
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.overridingErrorMessage(format("Expected character's age to be <%s> but was <%s>", age, actual.getAge()));
    Objects.instance().assertEqual(info, actual.getAge(), age);

    // return the current assertion for method chaining
    return this;
  }

}
