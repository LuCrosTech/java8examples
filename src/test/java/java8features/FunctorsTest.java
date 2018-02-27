package java8features;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FunctorsTest {

	private List<Person> persons;
	private Predicate<Person> isAnAdult;

	@Before
	public void setUp() throws Exception {
		persons = new ArrayList<>();
		persons.add(new Person("Carlita", 11));
		persons.add(new Person("Ana", 18));
		persons.add(new Person("Pedrito", 6));
		persons.add(new Person("Carmen", 35));
		persons.add(new Person("Jose", 21));
		isAnAdult = person -> person.getAge().orElseGet(() -> new Integer(0)) >= 18;
	}

	@Test
	public void testAPredicate_whenPersonIsAdult() {
		Long nrOfAdults = persons.stream().filter(isAnAdult).count();
		Assert.assertEquals(nrOfAdults, new Long(3));
	}

	@Test
	public void testPredicateAndConsumer_PrintwhenPersonIsNotAdult() {
		Stream<Person> children = persons.stream().filter(isAnAdult.negate());
		children.forEach(System.out::println);
	}

	@Test
	public void testPredicateAndConsulter_PrettyPrintAdults() {
		Stream<Person> adults = persons.stream().filter(isAnAdult);
		adults.forEach(person -> person.prettyPrint());
	}

	@Test
	public void testSupplier_newPerson() {
		Supplier<Person> personCreator = () -> new Person("Joe", 20);
		Person person = personCreator.get();
		Assert.assertEquals(20, person.getAge().get().intValue());
	}

	@Test
	public void testFuction_whenNameIsAna() {
		Function<String, Predicate<Person>> function = name -> person -> name.equals(person.getName().get());	
		Long nameAna = persons.stream().filter(function.apply("Ana")).count();
		Assert.assertEquals(1, nameAna.longValue());
	}
	
	@Test
	public void testFuction_whenNameStartsWithC() {
		Function<String, Predicate<Person>> function = letter -> person-> letter.equals(person.getName().get().substring(0, 1));
		Long startsWithC = persons.stream().filter(function.apply("C")).count();
		Assert.assertEquals(2, startsWithC.longValue());
	}
	
	@Test
	public void testFuction_whenNameIsAnaPrint() {
		Function<String, Predicate<Person>> function = name -> person -> name.equals(person.getName().get());
		persons.stream().filter(function.apply("Ana")).forEach(System.out::println);;
	}
	
	@Test
	public void testFuction_whenNameStartsWithCPrint() {
		Function<String, Predicate<Person>> function = letter -> person-> letter.equals(person.getName().get().substring(0, 1));
		persons.stream().filter(function.apply("C")).forEach(Person::prettyPrint);
	}
	
	@Test
	public void testStreamReuse_whenNameStartsWithC_printAndCount() {
		Function<String, Predicate<Person>> funcStartsWith = letter -> person -> letter.equals(person.getName().get().substring(0, 1));
		Supplier<Stream<Person>> personsStreamSupplier = persons::stream;
		Long startsWithC = personsStreamSupplier.get().filter(funcStartsWith.apply("C")).count();
		Assert.assertEquals(2, startsWithC.longValue());
		System.out.println("*******testStreamReuse_whenNameStartsWithC_printAndCount************");
		personsStreamSupplier.get().filter(funcStartsWith.apply("C")).forEach(Person::prettyPrint);
		
	}
	
	@Test
	public void testStream_whenNameIsJose_count() {
		System.out.println("--testStream_whenNameIsJose_count--");
		Predicate<Person> predicateJose = person -> "Jose".equals(person.getName().get());
		Supplier<Stream<Person>> personsSS = persons::stream;
		Assert.assertEquals(1, personsSS.get().filter(predicateJose).count());
	}
}
