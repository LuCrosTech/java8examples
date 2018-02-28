package java8features;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StreamAdvancedOperationsTest {
	
	Logger logger = LoggerFactory.getLogger(StreamAdvancedOperationsTest.class);
	private List<Person> persons;
	
	@Before
	public void setUp() {
		persons = Arrays.asList(new Person("Max", 18), new Person("Peter", 23), new Person("Pamela", 23),
				new Person("David", 12));
	}

	@Test
	public void testFilterAndCollectToList() {
		logger.info("--testFilterAndCollectToList--");
		List<Person> filtered = persons
				.stream()
				.filter(p -> p.getName().get().startsWith("P"))
				.collect(Collectors.toList());
		//filtered.stream().forEach(System.out::println);
		System.out.println(filtered);
		Assert.assertEquals(2, filtered.size());
	}

	@Test
	public void testFilterAndCollectToSet() {
		logger.info("--testFilterAndCollectToSet--");
		Set<Person> filtered = persons
				.stream()
				.filter(p -> p.getName().get().startsWith("P"))
				.collect(Collectors.toSet());
		//filtered.stream().forEach(System.out::println);
		System.out.println(filtered);
		Assert.assertEquals(2, filtered.size());
	}
	
	@Test
	public void testCollectAndGroupToMap() {
		logger.info("--testCollectAndGroupToMap--");
		Map<Integer, List<Person>> personsByAge = 
				persons
				.stream()
				.collect(Collectors.groupingBy(p -> p.getAge().get()));
		
		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		Assert.assertEquals(3,  personsByAge.size());
	}
	
	@Test
	public void testAverageAge() {
		logger.info("-testAverageAge-");
		Double averageAge = persons.stream()
				.collect(Collectors.averagingInt(p -> p.getAge().get()));
		System.out.println("Avg age: " + averageAge);
		Assert.assertEquals(19.0d, averageAge.doubleValue(),0);
	
	}
	
	@Test
	public void testSummaryStats() {
		logger.info("--testSummaryStats--");
		IntSummaryStatistics ageSummary =
				persons.stream().collect(Collectors.summarizingInt(p->p.getAge().get()));
		
		System.out.println(ageSummary);
		Assert.assertEquals(4, ageSummary.getCount());
	}
	
	@Test
	public void testJoiningStrings() {
		logger.info("--joiningStrings--");
		String phrase = persons
				.stream()
				.filter(p -> p.getAge().get() >= 18)
				.map(p->p.getName().get())
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println(phrase);
		
	}
	
	@Test
	public void testCollectToMap() {
		logger.info("--testCollectToMap--");
		Map<Integer, String> map = persons.stream()
				.collect(Collectors.toMap(p->p.getAge().get(), p->p.getName().get(), (name1, name2) -> name1 + ";" + name2));
		System.out.println(map);
	}
}
