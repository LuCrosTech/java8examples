package recursion;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		System.out.println("gcd: " + gcdRecursion(5, 15));
	}
	
	public static int gcdRecursion(int m, int n) {
		if (n == 0) return m;
		else return gcdRecursion(n, m % n);
	}
	
}
