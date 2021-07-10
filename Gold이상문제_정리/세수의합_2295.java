package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
5
2
3
5
10
18
 */

public class 세수의합_2295 {
	static int[] A;
	static int N, maxSum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(br.readLine());
		Arrays.sort(A);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int left = A[i] + A[j];
//				System.out.println("L "+left);
				binarySearch(left);
				if (maxSum == A[A.length - 1]){
					System.out.println(maxSum);
					return;
				}
			}
		}

		System.out.println(maxSum);
	}

	private static void binarySearch(int left) {
		int l = 0;
		int r = A.length - 1;
		int max = A[r];
		int sum;

		while (l <= r)
		{
			int mid = (l + r) / 2;

			if (left + A[mid] < max) {
				l = mid + 1;
				sum = left + A[mid];
				if (find(sum)) maxSum = Math.max(maxSum, sum);
			}
			else if (left + A[mid] > max)
				r = mid - 1;
			else {
				maxSum = max;
				break;
			}
		}
	}

	private static boolean find(int sum) {
		int l = 0;
		int r = A.length - 1;

		while (l <= r)
		{
			int mid = (l + r) / 2;

			if (A[mid] > sum)
				r = mid - 1;
			else if (A[mid] < sum)
				l = mid + 1;
			else
				return true;
		}
		return false;
	}
}
