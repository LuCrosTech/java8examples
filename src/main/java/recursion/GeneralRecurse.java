package recursion;

public class GeneralRecurse {

	public static int recursiveMethod(int seed) {
		System.out.println(seed);
		seed--;
		if (seed == 0 || seed == -100) {
			return 0;
		}
		return recursiveMethod(seed);
	}

}
