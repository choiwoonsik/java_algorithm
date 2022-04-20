package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
11
8 3 2 4 8 7 2 4 0 8 8
 */
public class 일학년_5557 {
	static int N;
	static int[] nums;
	static int answer;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		dp = new long[N + 1][21];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N - 1; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		answer = Integer.parseInt(st.nextToken());

		dp[1][nums[1]] = 1;

		for (int i = 2; i <= N - 1; i++) {
			for (int number = 0; number < 21; number++) {
				if (dp[i - 1][number] == 0) continue;

				if (number + nums[i] <= 20) {
					dp[i][number + nums[i]] += dp[i - 1][number];
				}
				if (number - nums[i] >= 0) {
					dp[i][number - nums[i]] += dp[i - 1][number];
				}
			}
		}

		System.out.print(dp[N - 1][answer]);
	}
}
