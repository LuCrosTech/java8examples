package java8features;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamsTest {

	Logger logger = LoggerFactory.getLogger(StreamsTest.class);
	
	@Test
	public void testWhenIsPresentTheFirstElementFromExistingArray() {
		logger.info("--testWhenIsPresentTheFirstElementFromExistingArray--");
		Arrays.asList("elem1", "elem2", "elem3").stream().findFirst().ifPresent(System.out::println);
		Assert.assertTrue(Stream.of("a1", "a2", "a3").findFirst().isPresent());
	}

	@Test
	public void testStreamInRange() {
		logger.info("--testStreamInRange--");
		IntStream.range(1, 6).forEach(System.out::println);
		Assert.assertEquals(5, LongStream.range(5, 10).count());
	}
	
	@Test
	public void testMapStreamAndCalculateAvgOnOperation() {
		logger.info("--testMapStreamAndCalculateAvgOnOperation--");
		Arrays.stream(new int[] {1, 2, 3})
		.map(n -> 2 * n + 1)
		.average()
		.ifPresent(System.out::println);
		
		Assert.assertEquals(5.666666666666667, IntStream.range(1, 4).map(n -> n*n + 1).average().getAsDouble(), 0);
		logger.info("Displaying set for range 2");
		IntStream.range(1, 4).map(n -> n*n + 1).forEach(System.out::println);
	}
	
	@Test
	public void testStringOperationOverNumberedStringsAndGetMaxValue() {
		logger.info("==testStringOperationOverNumberedStringsAndGetMaxValue==");
		
		Supplier<IntStream> intStream = () -> Stream.of("a1", "a2", "a3").map(s->s.substring(1)).mapToInt(Integer::parseInt);
		intStream.get().max().ifPresent(System.out::println);
		Assert.assertEquals(3, intStream.get().max().getAsInt());
	}
	
	@Test
	public void testWrappingPrimitiveIntIntoObjectAndValidate() {
		logger.info("--testWrappingPrimitiveIntIntoObjectAndValidate--");
		
		Supplier<Stream<String>> stream = () -> IntStream.range(100, 400).filter(i -> i %100 == 0).mapToObj(i -> "A-" + i);
		
		stream.get().forEach(System.out::println); 
		
		Assert.assertEquals(3, stream.get().count());
		Assert.assertTrue(stream.get().anyMatch(s -> "A-200".equals(s)));	
	}
	
	@Test
	public void testMultipleMapping() {
		Assert.assertTrue(Stream.of(1.0, 2.0, 3.0).mapToInt(Double::intValue).mapToObj(i -> "A-" + i).anyMatch(s -> "A-1".equals(s)));
	}
}
