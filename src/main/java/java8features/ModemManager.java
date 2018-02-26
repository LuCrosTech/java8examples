package java8features;

import java.util.Optional;

public class ModemManager {

	public boolean priceIsInRange1(Modem modem) {
		boolean isInRange = false;

		if (modem != null 
				&& modem.getPrice() != null 
				&& (modem.getPrice() >= 10 
				&& modem.getPrice() <= 15)) {
			isInRange = true;
		}
		return isInRange;
	}

	public boolean priceIsInRange2(Modem modem2) {
		return Optional.ofNullable(modem2)
				.map(Modem::getPrice)
				.filter(price -> price >= 10)
				.filter(price -> price <= 15)
				.isPresent();
	}
}
