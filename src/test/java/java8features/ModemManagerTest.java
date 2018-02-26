package java8features;

import org.junit.Assert;
import org.junit.Test;

import java8features.Modem;
import java8features.ModemManager;

public class ModemManagerTest {

	@Test
	public void whenFiltersWithoutOptional_thenCorrect() {
		ModemManager manager = new ModemManager();
		Assert.assertTrue(manager.priceIsInRange1(new Modem(10.0)));
		Assert.assertFalse(manager.priceIsInRange1(new Modem(9.9)));
		Assert.assertFalse(manager.priceIsInRange1(new Modem(null)));
		Assert.assertFalse(manager.priceIsInRange1(new Modem(15.5)));
		Assert.assertFalse(manager.priceIsInRange1(null));
	}

	@Test
	public void whenFiltersWithOptional_thenCorrect() {
		ModemManager manager = new ModemManager();
		Assert.assertTrue(manager.priceIsInRange2(new Modem(10.0)));
		Assert.assertFalse(manager.priceIsInRange2(new Modem(9.9)));
		Assert.assertFalse(manager.priceIsInRange2(new Modem(null)));
		Assert.assertFalse(manager.priceIsInRange2(new Modem(15.5)));
		Assert.assertFalse(manager.priceIsInRange2(null));
	}
}
