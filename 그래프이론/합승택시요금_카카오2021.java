package 그래프이론;

import java.util.ArrayList;
import java.util.Arrays;

public class 합승택시요금_카카오2021 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int solution = sol.solution(6, 4, 6, 2, new int[][]
				{
						{4, 1, 10},
						{3, 5, 24},
						{5, 6, 2},
						{3, 1, 41},
						{5, 1, 24},
						{4, 6, 50},
						{2, 4, 66},
						{2, 3, 22},
						{1, 6, 25}
				});
		System.out.println(solution == 82);
		System.out.println(solution);

		sol = new Solution();
		solution = sol.solution(7, 3, 4, 1, new int[][]
				{
						{5, 7, 9},
						{4, 6, 4},
						{3, 6, 1},
						{3, 2, 3},
						{2, 1, 6}
				}
		);
		System.out.println(solution == 14);
		System.out.println(solution);
	}

	private static class Solution {
		static int[][] dp;
		static int N;
		static int INF = 987654321;

		public int solution(int n, int s, int a, int b, int[][] fares) {
			N = n;
			dp = new int[n + 1][n + 1];
			for (int i = 0; i < n + 1; i++) {
				Arrays.fill(dp[i], INF);
				dp[i][i] = 0;
			}


			for (int[] fare : fares) {
				int from = fare[0];
				int to = fare[1];
				int cost = fare[2];
				dp[from][to] = cost;
				dp[to][from] = cost;
			}

			floydWarshall();
			int aCost = dp[s][a];
			int bCost = dp[s][b];
			int sum = aCost + bCost;

			for (int k = 1; k < N + 1; k++) {
				if (dp[s][k] == INF || dp[k][a] == INF || dp[k][b] == INF) continue;
				sum = Math.min(dp[s][k] + dp[k][a] + dp[k][b], sum);
			}

			return sum;
		}

		private void floydWarshall() {

			for (int k = 1; k < N + 1; k++) {
				for (int i = 1; i < N + 1; i++) {
					if (k == i) continue;
					for (int j = 1; j < N + 1; j++) {
						if (k == j) continue;
						if (i == j) continue;
						if (dp[i][k] == INF) continue;
						if (dp[k][j] == INF) continue;
						if (dp[i][k] + dp[k][j] < dp[i][j]) {
							dp[i][j] = dp[i][k] + dp[k][j];
						}
					}
				}
			}
		}
	}
}
