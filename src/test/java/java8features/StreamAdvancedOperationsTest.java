package java8features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		List<Person> filtered = persons.stream().filter(p -> p.getName().get().startsWith("P"))
				.collect(Collectors.toList());
		// filtered.stream().forEach(System.out::println);
		System.out.println(filtered);
		Assert.assertEquals(2, filtered.size());
	}

	@Test
	public void testFilterAndCollectToSet() {
		logger.info("--testFilterAndCollectToSet--");
		Set<Person> filtered = persons.stream().filter(p -> p.getName().get().startsWith("P"))
				.collect(Collectors.toSet());
		// filtered.stream().forEach(System.out::println);
		System.out.println(filtered);
		Assert.assertEquals(2, filtered.size());
	}

	@Test
	public void testCollectAndGroupToMap() {
		logger.info("--testCollectAndGroupToMap--");
		Map<Integer, List<Person>> personsByAge = persons.stream()
				.collect(Collectors.groupingBy(p -> p.getAge().get()));

		personsByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		Assert.assertEquals(3, personsByAge.size());
	}

	@Test
	public void testAverageAge() {
		logger.info("-testAverageAge-");
		Double averageAge = persons.stream().collect(Collectors.averagingInt(p -> p.getAge().get()));
		System.out.println("Avg age: " + averageAge);
		Assert.assertEquals(19.0d, averageAge.doubleValue(), 0);

	}

	@Test
	public void testSummaryStats() {
		logger.info("--testSummaryStats--");
		IntSummaryStatistics ageSummary = persons.stream().collect(Collectors.summarizingInt(p -> p.getAge().get()));

		System.out.println(ageSummary);
		Assert.assertEquals(4, ageSummary.getCount());
	}

	@Test
	public void testJoiningStrings() {
		logger.info("--joiningStrings--");
		String phrase = persons.stream().filter(p -> p.getAge().get() >= 18).map(p -> p.getName().get())
				.collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		System.out.println(phrase);

	}

	@Test
	public void testCollectToMap() {
		logger.info("--testCollectToMap--");
		Map<Integer, String> map = persons.stream().collect(
				Collectors.toMap(p -> p.getAge().get(), p -> p.getName().get(), (name1, name2) -> name1 + ";" + name2));
		System.out.println(map);
	}

	@Test
	public void testCustomCollector() {
		logger.info("--testCustomCollector--");

		Collector<Person, StringJoiner, String> personNameCollector = Collector.of(() -> new StringJoiner(" | "),
				(j, p) -> j.add(p.getName().get().toUpperCase()), (j1, j2) -> j1.merge(j2), StringJoiner::toString);

		String names = persons.stream().collect(personNameCollector);

		System.out.println(names);
		Assert.assertTrue(names.length() > 0);
	}

	@Test
	public void testTransformStreamOfThreeTeamObjectsIntoStreamOfNinePersonObjects() {
		logger.info("--testTransformStreamOfThreeTeamObjectsIntoStreamOfNinePersonObjects--");
		Random random = new Random();
		List<Team> teams = new ArrayList<>();

		IntStream.range(1, 4).forEach(i -> teams.add(new Team("Team" + i)));
		teams.forEach(t -> IntStream .range(1, 4).forEach(i -> t.people.add(new Person("Person" + i + " <- " + t.name, random.nextInt(21)))));
	
		teams.forEach(System.out::println);
		
		teams.stream().flatMap( t -> t.people.stream()).forEach(p -> System.out.println(p.getName().get()));
	}

	@Test
	public void testTransformStreamWithPeekAndFlatMap() {
		logger.info("--testTransformStreamWithPeekAndFlatMap--");
		Random random = new Random();
		IntStream
			.range(1, 4) 
			.mapToObj(i -> new Team("Team " + i))
			.peek(t -> IntStream.range(1, 4)
					.mapToObj(i -> new Person("Person " + i + " <- " + t.name, random.nextInt(35)))
					.forEach(t.people::add))
			.flatMap(t -> t.people.stream())
			.forEach(p -> System.out.println(p.getName().get()));
	}

	@Test
	public void testAccessToInnerField_withFlatMapAndOptional() {
		logger.info("--testAccessToInnerField_withFlatMapAndOptional--");
		MovieSpec movieSpec = new MovieSpec();
		if (movieSpec!= null && movieSpec.mediaType != null && movieSpec.mediaType.region!=null) {
			System.out.println("Region Zone of the media type of the movie spec: " + movieSpec.mediaType.region.zone);
		}
		// Each call to flatMap returns an Optional wrapping the desired object if present
		// or null if absent
		Optional.of(new MovieSpec())
		.flatMap(m -> Optional.ofNullable(m.mediaType))
		.flatMap(t -> Optional.ofNullable(t.region))
		.flatMap(r -> Optional.ofNullable(r.zone))
		.ifPresent(System.out::println);
	}
}
