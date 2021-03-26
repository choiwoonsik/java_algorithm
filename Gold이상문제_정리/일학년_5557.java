package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
11
8 3 2 4 8 7 2 4 0 8 8
 */
public class 일학년_5557 {
	static int [] num;
	static long[][] dp;
	static int len;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		len = Integer.parseInt(br.readLine());
		num = new int[len+1];
		dp = new long[len+1][21];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i < len+1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		// 첫번째 - 첫번째 수의 경우의 수
		dp[1][num[1]] = 1;
		for (int idx = 2; idx < len; idx++) {
			dp(idx);
		}
		System.out.println(dp[len - 1][num[len]]);
	}
	private static void dp(int idx) {

		for (int n = 0; n <= 20; n++) {
			if (dp[idx - 1][n] == 0) continue;
			if (n + num[idx] <= 20) {
				dp[idx][n + num[idx]] += dp[idx - 1][n];
			}
			if (n - num[idx] >= 0) {
				dp[idx][n - num[idx]] += dp[idx - 1][n];
			}
		}
	}
}
