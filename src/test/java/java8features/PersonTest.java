package java8features;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class PersonTest {

	@Test
	public void givenOptional_whenFlatMapWorks_thenCorrect2() {
		Person person = new Person("john", 26);

		Optional<Person> personOptional = Optional.of(person);

		Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getNameOptional);
		Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
		String name1 = nameOptional.orElse("");
		assertEquals("john", name1);

		String name = personOptional.flatMap(Person::getNameOptional).orElse("");
		assertEquals("john", name);
	}
	//Map transforms values only when they are unwrapped
	// flatMap takes a wrapped value and unwraps it before transforming it

}
