package java8features;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelStreamsTest {

	Logger logger = LoggerFactory.getLogger(ParallelStreamsTest.class);

	
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
	}
	@Test
	public void testReduceSumAgesInParallel_debugged() {
		logger.info("--testReduceSumAgesInParallel_debugged--");
		Integer ageSum = persons
				.parallelStream()
				.reduce(0, 
						(sum, p) -> {
						System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p,  Thread.currentThread().getName());	
						return sum += p.getAge().get();
						}, 
						(sum1, sum2) -> {
							System.out.format("combiner: sum1=%s, sum2=%s [%s]\n", sum1, sum2,  Thread.currentThread().getName());
							return sum1 + sum2;});
	System.out.println(ageSum);
	}
	
	@Test
	public void testForkJoinPool() {
		logger.info("--testForkJoinPool--");
		ForkJoinPool commonPool = ForkJoinPool.commonPool();
		System.out.println("Paralelism: " + commonPool.getParallelism());
		System.out.println("Poolsize: " + commonPool.getPoolSize());
	}

	@Test
	public void testFilterAndMap_parallelStreams_watch_ThreadNames() {
		logger.info("--testFilterAndMap_parallelStreams_watch_ThreadNames--");
		
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		})
		.map(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		})
		.forEach(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
		});
	}
	
	@Test
	public void testFilterMapAndSort_parallelStreams_watch_ThreadNames() {
		logger.info("--testFilterMapAndSort_parallelStreams_watch_ThreadNames--");
		
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
		.parallelStream()
		.filter(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
			return true;
		})
		.map(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
			return s.toUpperCase();
		})
		.sorted((s1, s2) -> {
			System.out.format("sort: %s <> %s [%s]\n", s1, s2, Thread.currentThread().getName());
			return s1.compareTo(s2);
		})
		.forEach(s -> {
			System.out.format("Filter: %s [%s]\n", s, Thread.currentThread().getName());
		});
	}
}
