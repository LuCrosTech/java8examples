package java8features;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

public class OptionalExamples {

	@Test
	public void whenCreatesEmptyOptional_thenCorrect() {
		Optional<String> empty = Optional.empty();
		Assert.assertFalse(empty.isPresent());
	}

	@Test
	public void givenNonNull_whenCreatesOptional_thenCorrect() {
		String name = "lucrostech";
		Optional<String> opt = Optional.of(name);
		Assert.assertEquals("Optional[lucrostech]", opt.toString());
	}

	@Test(expected = NullPointerException.class)
	public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
		String name = null;
		Optional<String> opt = Optional.of(name);
	}

	@Test
	public void givenNonNull_whenCreatesNullable_thenCorrect() {
		String name = "lucrostech";
		Optional<String> opt = Optional.ofNullable(name);
		Assert.assertEquals("Optional[lucrostech]", opt.toString());
	}

	@Test
	public void givenNull_whenCreatesNullable_thenCorrect() {
		String name = null;
		Optional<String> opt = Optional.ofNullable(name);
		Assert.assertEquals("Optional.empty", opt.toString());
	}

	@Test
	public void givenOptional_whenIsPresentWorks_thenCorrect() {
		Optional<String> opt = Optional.of("lucrostech");
		Assert.assertTrue(opt.isPresent());
		opt = Optional.ofNullable(null);
		Assert.assertFalse(opt.isPresent());
	}

	@Test
	public void givenOptional_whenIfPresentWorks_thenCorrect() {
		Optional<String> opt = Optional.of("lucrostech");
		opt.ifPresent(name -> System.out.println(name.length()));
	}

	@Test
	public void whenOrElseWorks_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElse("john");
		Assert.assertEquals("john", name);
	}

	@Test
	public void whenOrElseGetWorks_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElseGet(() -> "john");
		Assert.assertEquals("john", name);
	}

	private String getMyDefault() {
		System.out.println("Getting Default Value");
		return "Default Value";
	}

	@Test
	public void whenOrElseGetAndOrElseOverlap_thenCorrect() {
		String text = null;
		System.out.println("Using orElseGet:");
		String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
		Assert.assertEquals("Default Value", defaultText);

		System.out.println("Using orElse:");
		defaultText = Optional.ofNullable(text).orElse(getMyDefault());
		Assert.assertEquals("Default Value", defaultText);
	}

	@Test
	public void whenOrElseGetAndOrElseDiffer_thenCorrect() {
		String text = "Text present";

		System.out.println("Using orElseGet");
		String defaultText = Optional.ofNullable(text).orElseGet(this::getMyDefault);
		Assert.assertEquals("Text present", defaultText);

		System.out.println("Using orElse");
		defaultText = Optional.ofNullable(text).orElse(getMyDefault());
		Assert.assertEquals("Text present", defaultText);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenOrElseThrowWorks_thenCorrect() {
		String nullName = null;
		String name = Optional.ofNullable(nullName).orElseThrow(IllegalArgumentException::new);
		// This was a method reference used in the Supplier<? extends X>
	}

	@Test
	public void givenOptional_whenGetsValue_thenCorrect() {
		Optional<String> opt = Optional.of("lucrostech");
		String name = opt.get();
		Assert.assertEquals("lucrostech", name);
	}

	@Test(expected = NoSuchElementException.class)
	public void givenOptionalWithNull_whenGetThrowsException_thenCorrect() {
		Optional<String> opt = Optional.ofNullable(null);
		String name = opt.get();
	}

}
