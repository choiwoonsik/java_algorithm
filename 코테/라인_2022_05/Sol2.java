package 코테.라인_2022_05;

import java.util.Arrays;

public class Sol2 {
	public static void main(String[] args) {
		Solution sol = new Solution();

		int solution = sol.solution(5, new int[]{2, 4, 5});
		System.out.println(solution);

//		solution = sol.solution(6, new int[]{1, 2, 3});
//		System.out.println(solution);

//		solution = sol.solution(4, new int[]{2, 3});
//		System.out.println(solution);
	}

	private static class Solution {
		static int INF;
		static int N;
		static int[] T;
		static int[] dp; // 줄 개수 당 시간

		public int solution(int n, int[] times) {

			INF = 10000000;
			N = n;
			T = times.clone();

			dp = new int[2002];
			Arrays.fill(dp, INF);
			dp[0] = 0;
			dp[1] = times[0];

			for (int i = 1; i <= N; i++) {
				for (int j = i - 1; j >= Math.ceil((double) i / 2); j--) {
					for (int k = 1; k <= i - j; k++) {
						dp[i] = Math.min(dp[i], dp[k] + dp[i - (k * 2)] + times[k - 1]);
					}
				}
			}

			return dp[n];
		}
	}
}