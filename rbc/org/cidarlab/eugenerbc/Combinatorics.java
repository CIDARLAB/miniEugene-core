package org.cidarlab.eugenerbc;

public class Combinatorics {

	public static long fact(int n) {
		if(n == 0) {
			return 1;
		} else if(n < 0) {
			throw new IllegalArgumentException("Factorial input cannot be negative");
		}
		return n * fact(n-1);
	}
	
	public static long fact(long n) {
		if(n == 0) {
			return 1;
		} else if(n < 0) {
			throw new IllegalArgumentException("Factorial input cannot be negative");
		}
		return n * fact(n-1);
	}
	
	public static long nCr(int n, int r) {
		return fact(n) / (fact(n-r) * fact(r));
	}
	
	public static long nCr(long n, int r) {
		return fact(n) / (fact(n-r) * fact(r));
	}
	
	public static long sumArray(int[] arr) {
		return sumArray(arr, arr.length);
	}
	
	public static long sumArray(int[] arr, int end) {
		long total = 0;
		for(int i = 0; i < end; i++) {
			total +=arr[i];
		}
		return total;
	}
	
}
