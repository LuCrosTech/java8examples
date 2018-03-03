package structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.Test;

public class CollectionFunctions {

	@Test
	public void testMostFrequentItemInList() {
		List<String> list = new ArrayList<>();
		list.add("test");
		list.add("test");
		list.add("hello");
		list.add("hello");
		list.add("yo");
		list.add("test");
		
		int times = Collections.frequency(list, "hello");
		
		System.out.println("Times of hello, with collection frequency: " + times);
		
		Map<String, Long> occurrences = list.stream()
				.collect(Collectors.groupingBy(w -> w, Collectors.counting()));
		
		occurrences.forEach((a, b) -> System.out.format("%s: %s\n", a, b));
		
		list.stream()
		.collect(Collectors.groupingBy(s -> s, Collectors.counting()))
		.entrySet()
		.stream()
		.max(Comparator.comparing(Entry::getValue))
		.ifPresent(System.out::println);
	}

}
