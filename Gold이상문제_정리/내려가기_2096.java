package Gold이상문제_정리;

import java.io.*;
import java.util.StringTokenizer;

public class 내려가기_2096 {
	static int[][] A;
	static int[][] max_dp;
	static int[][] min_dp;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		A = new int[N + 1][4];
		max_dp = new int[2][4];
		min_dp = new int[2][4];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 4; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if (i == 1) {
					max_dp[0][j] = A[i][j];
					min_dp[0][j] = A[i][j];
				}
			}
		}

		for (int y = 2; y < N + 1; y++) {
			max_dp(y);
			min_dp(y);
		}
		int max = 0;
		int min = 10000000;
		for (int i = 1; i <= 3; i++) {
			max = Math.max(max_dp[0][i], max);
			min = Math.min(min_dp[0][i], min);
		}
		System.out.println(max + " " + min);
	}

	private static void min_dp(int y) {
		min_dp[1][1] = Math.min(min_dp[0][1] + A[y][1], min_dp[0][2] + A[y][1]);
		min_dp[1][2] = Math.min(Math.min(min_dp[0][1] + A[y][2], min_dp[0][2] + A[y][2]), min_dp[0][3] + A[y][2]);
		min_dp[1][3] = Math.min(min_dp[0][2] + A[y][3], min_dp[0][3] + A[y][3]);

		min_dp[0][1] = min_dp[1][1];
		min_dp[0][2] = min_dp[1][2];
		min_dp[0][3] = min_dp[1][3];
	}

	private static void max_dp(int y) {
		max_dp[1][1] = Math.max(max_dp[0][1] + A[y][1], max_dp[0][2] + A[y][1]);
		max_dp[1][2] = Math.max(Math.max(max_dp[0][1] + A[y][2], max_dp[0][2] + A[y][2]), max_dp[0][3] + A[y][2]);
		max_dp[1][3] = Math.max(max_dp[0][2] + A[y][3], max_dp[0][3] + A[y][3]);

		max_dp[0][1] = max_dp[1][1];
		max_dp[0][2] = max_dp[1][2];
		max_dp[0][3] = max_dp[1][3];
	}
}
