package org.fest.assertions.examples.advanced;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.condition.AllOf.allOf;
import static org.fest.assertions.condition.AnyOf.anyOf;
import static org.fest.assertions.condition.Not.not;
import static org.fest.util.Collections.set;

import java.util.Set;

import org.junit.Test;

import org.fest.assertions.core.Condition;
import org.fest.assertions.examples.AbstractAssertionsExamples;

public class UsingConditionExamples extends AbstractAssertionsExamples {

  @Test
  public void is_condition_example() {
    assertThat("Yoda").is(jedi);
    assertThat("Vader").isNot(jedi);
    try {
      // condition not verified to show the clean error message 
      assertThat("Vader").is(jedi);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("expecting:<'Vader'> to be:<jedi>");
    }
  }

  @Test
  public void has_condition_example() {
    assertThat("Yoda").has(jediPower);
    assertThat("Solo").doesNotHave(jediPower);
    try {
      // condition not verified to show the clean error message 
      assertThat("Vader").has(jediPower);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("expecting:<'Vader'> to have:<jedi power>");
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void anyOf_condition_example() {
    assertThat("Vader").is(anyOf(jedi, sith));
  }

  @Test
  public void condition_example_on_multiple_elements() {
    // are & areNot
    assertThat(set("Luke", "Yoda")).are(jedi);
    assertThat(set("Leia", "Solo")).areNot(jedi);

    // have & doNotHave
    assertThat(set("Luke", "Yoda")).have(jediPower);
    assertThat(set("Leia", "Solo")).doNotHave(jediPower);

    // areAtLeast & areNotAtLeast
    assertThat(set("Luke", "Yoda", "Leia")).areAtLeast(2, jedi);
    assertThat(set("Luke", "Yoda", "Obiwan")).areAtLeast(2, jedi);
    assertThat(set("Luke", "Yoda", "Leia")).areNotAtLeast(1, jedi);
    assertThat(set("Luke", "Solo", "Leia")).areNotAtLeast(1, jedi);

    // haveAtLeast & doNotHaveAtLeast
    assertThat(set("Luke", "Yoda", "Leia")).haveAtLeast(2, jediPower);
    assertThat(set("Luke", "Yoda", "Obiwan")).haveAtLeast(2, jediPower);
    assertThat(set("Luke", "Yoda", "Leia")).doNotHaveAtLeast(1, jediPower);
    assertThat(set("Luke", "Solo", "Leia")).doNotHaveAtLeast(1, jediPower);

    // areAtMost & areNotAtMost
    assertThat(set("Luke", "Yoda", "Leia")).areAtMost(2, jedi);
    assertThat(set("Luke", "Solo", "Leia")).areAtMost(2, jedi);
    assertThat(set("Luke", "Yoda", "Leia")).areNotAtMost(1, jedi);
    assertThat(set("Luke", "Yoda", "Obiwan")).areNotAtMost(1, jedi);

    // haveAtMost & doNotHaveAtMost
    assertThat(set("Luke", "Yoda", "Leia")).haveAtMost(2, jediPower);
    assertThat(set("Luke", "Solo", "Leia")).haveAtMost(2, jediPower);
    assertThat(set("Luke", "Yoda", "Leia")).doNotHaveAtMost(1, jediPower);
    assertThat(set("Luke", "Yoda", "Obiwan")).doNotHaveAtMost(1, jediPower);

    // areExactly & areNotExactly
    assertThat(set("Luke", "Yoda", "Leia")).areExactly(2, jedi);
    assertThat(set("Luke", "Yoda", "Leia")).areNotExactly(1, jedi);

    // haveExactly & haveNotExactly
    assertThat(set("Luke", "Yoda", "Leia")).haveExactly(2, jediPower);
    assertThat(set("Luke", "Yoda", "Leia")).doNotHaveExactly(1, jediPower);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void has_not_condition_example() {
    assertThat("Yoda").has(jediPower);
    assertThat("Yoda").has(allOf(jediPower, not(sithPower)));
    assertThat("Solo").has(not(jediPower));
    assertThat("Solo").doesNotHave(jediPower);
    assertThat("Solo").is(allOf(not(jedi), not(sith)));
  }

  private final Condition<String> jedi = new Condition<String>("jedi") {
    private final Set<String> jedis = set("Luke", "Yoda", "Obiwan");

    @Override
    public boolean matches(String value) {
      return jedis.contains(value);
    }
  };

  private final Condition<String> jediPower = new Condition<String>("jedi power") {
    private final Set<String> jedis = set("Luke", "Yoda", "Obiwan");

    @Override
    public boolean matches(String value) {
      return jedis.contains(value);
    }
  };

  private final Condition<String> sith = new Condition<String>("sith") {
    private final Set<String> siths = set("Sidious", "Vader", "Plagueis");

    @Override
    public boolean matches(String value) {
      return siths.contains(value);
    }
  };

  private final Condition<String> sithPower = new Condition<String>("sith power") {
    private final Set<String> siths = set("Sidious", "Vader", "Plagueis");

    @Override
    public boolean matches(String value) {
      return siths.contains(value);
    }
  };

}
