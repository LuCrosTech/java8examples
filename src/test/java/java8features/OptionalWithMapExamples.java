package java8features;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalWithMapExamples {
	
	@Test
	public void whenOptionalFilterWorks_thenCorrect() {
		Integer year = 2016;
		Optional<Integer> yearOptional = Optional.of(year);
		boolean is2016 = yearOptional.filter(y -> y == 2016).isPresent();
		Assert.assertTrue(is2016);
		boolean is2017 = yearOptional.filter(y -> y == 2017).isPresent();
		Assert.assertFalse(is2017);
	}

	@Test
	public void givenOptional_whenMapWorks_thenCorrect() {
		List<String> companyNames = Arrays.asList("paypal", "oracle", "", "microsoft", "", "apple");
		Optional<List<String>> listOptional = Optional.of(companyNames);

		int size = listOptional.map(List::size).orElse(0);
		Assert.assertEquals(6, size);
	}

	@Test
	public void givenOptional_whenMapWorks_thenCorrect2() {
		String name = "lucrostech";
		Optional<String> nameOptional = Optional.of(name);

		int len = nameOptional.map(String::length).orElse(0);
		Assert.assertEquals(10, len);
	}
	
	@Test
	public void givenOptional_whenMapWoksWithFilter_thenCorrect() {
		String password = " password ";
		
		Optional<String> passOpt = Optional.of(password);
		boolean correctPassword = passOpt.filter( pass -> pass.equals("password")).isPresent();
		Assert.assertFalse(correctPassword);
		
		correctPassword = passOpt.map(String::trim).filter(pass -> pass.equals("password")).isPresent();
		Assert.assertTrue(correctPassword);
		//Map transforms values only when they are unwrapped
		// flatMap takes a wrapped value and unwraps it before transforming it
	}
	
	
}
