package java8features;

import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class PredicateTest {

	@Test
	public void numberIsOdd() {
		Predicate<Integer> isOdd = i -> i % 2 == 1;
		Assert.assertTrue(isOdd.test(15));
	}

}
