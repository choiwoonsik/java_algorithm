package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
8 2
1 2 3 4 5 6 7 8

10 3
1 2 2 2 2 7 8 9 11 10
 */
public class 가장긴짝수연속한부분수열_22857 {
	static int N;
	static int K;
	static int[][] dp;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		dp = new int[N + 1][K + 1];
		nums = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++)
			{
				if (nums[i] % 2 == 0) {
					dp[i][j] = dp[i - 1][j] + 1;
				}
				if (j > 0 && nums[i] % 2 == 1) {
					dp[i][j] = dp[i - 1][j - 1];
				}
			}
		}

		int max = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				max = Math.max(max, dp[i][K]);
			}
		}

		System.out.println(max);
	}
}
