package java8features;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Luis Crosby
 *
 */
public class StreamOperationsTest {

	Logger logger = LoggerFactory.getLogger(StreamOperationsTest.class);
	@Test
	public void testNothingToPrint_intermediateOp() {
		logger.info("--testNothingToPrint_intermediateOp--");
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter:\t\t" + s);
			return true;
		});
	}

	@Test
	public void testPrintUnfiltered() {
		logger.info("--testPrintUnfiltered--");
		Stream.of("d2", "a2", "b1", "b3", "c").filter(s -> {
			System.out.println("filter:\t\t" + s);
			return true;
		}).forEach(s -> System.out.println("forEach:\t" + s));
	}

	@Test
	public void testPrintMapThenMatch() {
		logger.info("--testPrintMapThenMatch--");

		boolean val = Stream.of("d2", "a2", "b1", "b3", "c").map(s -> {
			System.out.println("map:\t\t " + s);
			return s.toUpperCase();
		}).anyMatch(s -> {
			System.out.println("anyMatch:\t" + s);
			return s.startsWith("A");
		});
		Assert.assertTrue(val);
	}

	@Test
	public void testMapFilterForEach() {
		logger.info("--testMapFilterForEach--");

		Stream.of("d2", "a2", "b1", "b3", "c")
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		}).filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		}).forEach(s -> System.out.println("forEach: " + s));

	}
	
	@Test
	public void testFilterMapForEach() {
		logger.info("--testFilterMapForEach--");

		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		})
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		})
		.forEach(s -> System.out.println("forEach: " + s));

	}

	@Test
	public void testSortedFilterMapForEach() {
		// sorted is a stateful intermediate operation
		// because state needs to be maintained during ordering
		logger.info("--testSortedFilterMapForEach--");

		Stream.of("d2", "a2", "b1", "b3", "c")
		.sorted((s1, s2) -> {
			System.out.printf("Sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		})
		.filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		})
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		})
		.forEach(s -> System.out.println("forEach: " + s));

	}
	
	@Test
	public void testFilterSortedMapForEach() {
		// Filter reduces the map
		logger.info("--testFilterSortedMapForEach--");

		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a");
		})
		.sorted((s1, s2) -> {
			System.out.printf("Sort: %s; %s\n", s1, s2);
			return s1.compareTo(s2);
		})
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		})
		.forEach(s -> System.out.println("forEach: " + s));

	}
	
	@Test(expected=IllegalStateException.class)
	public void testReuseStreamAndFail() {
		logger.info("--testReuseStreamAndFail--");
		Stream<String> stream = Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s->s.startsWith("a"));
		Assert.assertTrue(stream.anyMatch(s->true));
		Assert.assertTrue(stream.noneMatch(s->true));
	}
	
	@Test
	public void testReuseStreamWithSupplier() {
		logger.info("--testReuseStreamWithSupplier--");
		
		Supplier<Stream<String>> streamSupplier = () ->
			Stream.of("d2", "a2", "b1", "b3", "c")
					.filter(s->s.startsWith("a"));
		
		Assert.assertTrue(streamSupplier.get().anyMatch(s->true));
		Assert.assertFalse(streamSupplier.get().noneMatch(s->true));
	}	
}
