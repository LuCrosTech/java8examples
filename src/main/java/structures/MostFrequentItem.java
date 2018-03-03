package structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MostFrequentItem {

	public static Integer getMostFrequentItem(int[] array) {

		Map<Integer, Integer> elements = new HashMap<>();

		for (int i = 0; i < array.length; i++) {
			// <k, v> = <element, counter>
			if (elements.containsKey(array[i])) {
				elements.put(array[i], elements.get(array[i]) + 1);
			} else {
				elements.put(array[i], 1);
			}

		}

		Set<Map.Entry<Integer, Integer>> elementEntrySet = elements.entrySet();

		int item = 0, frequency = 1;

		for (Entry<Integer, Integer> entry : elementEntrySet) {
			if (entry.getValue() > frequency) {
				frequency = entry.getValue();
				item = entry.getKey();
			}
		}
		
		
		if (frequency > 1)
			return item;
		else
			return null;
	}
}