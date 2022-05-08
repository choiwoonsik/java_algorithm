package 코테.카카오_2022_05;

import java.util.*;

public class sol2 {
	public static void main(String[] args) {
//		Solution sol1 = new Solution();
//		int solution1 = sol1.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1});
//		System.out.println(solution1);
//
//		Solution sol2 = new Solution();
//		int solution2 = sol2.solution(new int[]{1}, new int[]{1});
//		System.out.println(solution2);
//
//		Solution sol3 = new Solution();
//		int solution3 = sol3.solution(new int[]{1, 2, 3}, new int[]{4, 5, 15});
//		System.out.println(solution3);

//		Solution sol4 = new Solution();
//		int solution4 = sol4.solution(new int[]{8, 1, 3}, new int[]{4, 5, 5});
//		System.out.println(solution4);

		Solution sol4 = new Solution();
		int solution4 = sol4.solution(new int[]{8, 1, 3, 9, 7, 2}, new int[]{1, 2, 1, 1, 3, 2});
		System.out.println(solution4);
	}

	private static class Solution {
		static int[] nums;
		static long[] dp;
		static int N;
		static long HALF;
		static int Min = Integer.MAX_VALUE;

		public int solution(int[] queue1, int[] queue2) {
			N = queue1.length;
			nums = new int[N * 2];
			dp = new long[N * 2];

			for (int i = 0; i < N; i++) {
				nums[i] = queue1[i];
				HALF += nums[i];
			}
			for (int i = N; i < N * 2; i++) {
				nums[i] = queue2[i - N];
				HALF += nums[i];
			}
			dp[0] = nums[0];
			for (int i = 1; i < N * 2; i++) {
				dp[i] = dp[i - 1] + nums[i];
			}

			HALF /= 2;

			int left = 0;
			long sum = 0;
			if (dp[N - 1] == dp[2 * N - 1] - dp[N - 1]) {
				return 0;
			}
			while (left < N * 2) {
				int index = binarySearch(left, N * 2 - 1);
				sum = dp[index] - dp[left] + nums[left];

				if (sum == HALF) {
					int count = needSwap(left, index);
					if (count < Min) {
						Min = count;
					}
				}

				left++;
			}

			if (Min == Integer.MAX_VALUE) return -1;
			return Min;
		}

		private int needSwap(int left, int right) {
			int count = 0;

			if (left < N && right < N) {
				System.out.println("case 1");
				int rightSum = 0;
				rightSum += left;
				rightSum += N;
				rightSum += left;
				count += right - left + 1;
				count += rightSum;
			} else if (left < N) {
				System.out.println("case 2");
				count += left;
				count += right - N + 1;
			} else {
				System.out.println("case 3");
				int leftSum = 0;
				leftSum += right - N;
				leftSum += N;
				leftSum += right - N;
				count += right - left + 1;
				count += leftSum;
			}

			return count;
		}

		private int binarySearch(int left, int right) {
			int l = left;
			int r = right;

			while (l < r) {
				int mid = (l + r) / 2;
				long sum = dp[mid] - dp[left] + nums[left];

				if (sum >= HALF) {
					r = mid;
				} else {
					l = mid + 1;
				}
			}

			return r;
		}
	}
}
