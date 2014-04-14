package org.cidarlab.eugenerbc;

import java.math.BigInteger;
import java.util.List;

public class Combinatorics {
	
	public static BigInteger fact(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Combinatorics.fact not defined for negative inputs");
		} else if(n == 0) {
			return BigInteger.ONE;
		} else {
			return BigInteger.valueOf(n).multiply(fact(n - 1));
		}
	}
	
	public static BigInteger nCr(int n, int r) {
		return fact(n).divide(fact(n - r).multiply(fact(r)));
	}
	
	public static BigInteger pow(int a, int b) {
		if(a < 0 || b < 0) {
			throw new IllegalArgumentException("Combinatorics.pow not defined for negative a,b");
		} else if(b == 0) {
			return BigInteger.ONE;
		} else {
			return BigInteger.valueOf(a).multiply(pow(a, b - 1));
		}
	}
	
	public static BigInteger max(List<BigInteger> list) {
		if(list.size() == 0) {
			throw new IllegalArgumentException("Combinatorics.max not defined on empty list");
		}
		BigInteger max = list.get(0);
		for(BigInteger num : list) {
			if(num.compareTo(max) > 0) {
				max = num;
			}
		}
		return max;
	}

//	public static long fact(int n) {
//		if(n == 0) {
//			return 1;
//		} else if(n < 0) {
//			throw new IllegalArgumentException("Factorial input cannot be negative");
//		}
//		return n * fact(n-1);
//	}
//	
//	public static long fact(long n) {
//		if(n == 0) {
//			return 1;
//		} else if(n < 0) {
//			throw new IllegalArgumentException("Factorial input cannot be negative");
//		}
//		return n * fact(n-1);
//	}
//	
//	public static long nCr(int n, int r) {
//		return fact(n) / (fact(n-r) * fact(r));
//	}
//	
//	public static long nCr(long n, int r) {
//		return fact(n) / (fact(n-r) * fact(r));
//	}
//	
//	public static long sumArray(int[] arr) {
//		return sumArray(arr, arr.length);
//	}
//	
//	public static long sumArray(int[] arr, int end) {
//		long total = 0;
//		for(int i = 0; i < end; i++) {
//			total +=arr[i];
//		}
//		return total;
//	}
	
}
