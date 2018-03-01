package java8features;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StreamReduceTest {

	Logger logger = LoggerFactory.getLogger(StreamReduceTest.class);

	
	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
	}
	
	@Test
	public void testReduceStreamToOneElement() {
		logger.info("--testReduceStreamToOneElement--");
		
		persons.stream()
		.reduce((p1, p2) -> p1.getAge().get() > p2.getAge().get() ? p1: p2)
		.ifPresent(System.out::println);
	}
	
	@Test
	public void testConstructNewPersonWithAggregatedValues() {
		logger.info("--testConstructNewPersonWithAggregatedValues--");
		
		Person result = persons
				.stream()
				.reduce(new Person("", 0), (p1, p2) -> {
					p1.setAge(p1.getAge().get() + p2.getAge().get());
					p1.setName(p1.getName().get() + p2.getName().get());
					return p1;
				});
		System.out.format("name=%s; age=%s", result.getName().get(), result.getAge().get());
	}
	
	@Test
	public void testReduceSumAges() {
		logger.info("--testReduceSumAges--");
		Integer ageSum = persons
				.stream()
				.reduce(0, (sum, p) -> sum += p.getAge().get(), (sum1, sum2) -> sum1 + sum2);
	System.out.println(ageSum);
	}

	@Test
	public void testReduceSumAges_debugged() {
		logger.info("--testReduceSumAges_debugged--");
		Integer ageSum = persons
				.stream()
				.reduce(0, 
						(sum, p) -> {
						System.out.format("accumulator: sum=%s; person=%s\n", sum, p);	
						return sum += p.getAge().get();
						}, 
						(sum1, sum2) -> {
							System.out.format("combiner: sum1=%s, sum2=%s", sum1, sum2);
							return sum1 + sum2;});
	System.out.println(ageSum);
	}


}
