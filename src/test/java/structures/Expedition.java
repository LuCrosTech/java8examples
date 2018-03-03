package structures;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class Expedition {

	/**
	 * "Watch DynamicEDIST"
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	@Deprecated
	private int count(String s1, String s2) {
		int output = 3;
		int counter = 0;

		char[] s1Arr = s1.toCharArray();
		for (int i = 0; i < s1Arr.length; i++) {
			// counter = s2.contains(s1Arr[i]);
		}

		return counter;
	}

	@Test
	public void testNumberOfCharactersConverted() {
		String s1 = "Sunday";
		String s2 = "Saturday";

		Assert.assertEquals(3, count(s1, s2));

	}

	/**
	 * Problem 2
	 * 
	 * @param a
	 * @return
	 */
	public int[][] testOnlyOneOccurrenceInMatrix(int[][] a) {

		int[][] b = new int[a.length][a[0].length];

		int changingI = -1, changingJ = -1;
		int changingNum = 0;

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == changingNum) {
					changingI = i;
					changingJ = j;
					break;
				}
			}
			if (changingI != -1 && changingJ != -1) {
				break;
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (i == changingI || j == changingJ)
					b[i][j] = changingNum;
				else
					b[i][j] = a[i][j];
			}
		}
		return b;
	}

	public int[][] testMultipleOccurrencesInMatrix(int[][] a) {

		int changingNum = 0;

		Set<Integer> changingIList = new HashSet<>();
		Set<Integer> changingJList = new HashSet<>();

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] == changingNum) {
					changingIList.add(i);
					changingJList.add(j);
				}
			}
		}

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (changingIList.contains(i) || changingJList.contains(j))
					a[i][j] = changingNum;
			}

		}
		return a;
	}

	public static void main(String[] args) {
		Expedition exp = new Expedition();
		int[][] matrix = { { 0, 1, 2 }, { 5, 4, 5 }, { 8, 9, 8 } };

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.format(" %s ", matrix[i][j]);

			}
			System.out.println("\n");
		}
		System.out.println("\n\n");
		matrix = exp.testMultipleOccurrencesInMatrix(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.format(" %s ", matrix[i][j]);

			}
			System.out.println("\n");
		}
	}
}
