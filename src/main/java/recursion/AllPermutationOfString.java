package recursion;

import java.util.Arrays;

public class AllPermutationOfString {

	public static void main(String[] args) {
		String str = "abcd";
		permute(str.toCharArray(), str.length());
	}
	
	public static void permute(char[] arr, int length) {
		
		int lastIndex = length - 1 ;
		
		if (length == 1)  {
			System.out.println(Arrays.toString(arr));
		} else {
			for (int i = 0 ; i < lastIndex; i++) {
				permute(arr, lastIndex);
				
				if (length % 2 == 1) {
					swap(arr, 0, lastIndex);
				} else {
					swap(arr, i, lastIndex);
				}
			
			}
			permute (arr, lastIndex);
			
		}
		
	}
	
	private static void swap(char[] arr, int right, int left) {
		char temp = arr[right];
		arr[right] = arr[left];
		arr[left] = temp;
	}
}
