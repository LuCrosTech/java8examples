package structures;

import org.junit.Assert;
import org.junit.Test;

public class MostFrequentItemTest {

	@Test
	public void testMostFrequentItem() {
		int[] arr = new int[] { 1, 5, 4, 23, 5, 4, 5 };
		int mostFrequentItem = 5;

		Integer result = MostFrequentItem.getMostFrequentItem(arr);
		Assert.assertEquals(result.intValue(), mostFrequentItem);
	}

	@Test
	    public void testNoFrequentItem()
	{
		int[] arr = new int[] { 1, 4, 23, 5 };

		Assert.assertNull(MostFrequentItem.getMostFrequentItem(arr));
	}
}
