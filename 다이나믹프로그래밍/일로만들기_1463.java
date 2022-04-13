package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

public class 일로만들기_1463 {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		dp[1] = 0;

		for (int num = 2; num < N + 1; num++) {
			dp[num] = dp[num - 1] + 1;
			dp[num] = Math.min(dp[num], dp[num - 1] + 1);
			if (num % 3 == 0) {
				dp[num] = Math.min(dp[num], dp[num / 3] + 1);
			}
			if (num % 2 == 0) {
				dp[num] = Math.min(dp[num], dp[num / 2] + 1);
			}
		}

		System.out.println(dp[N]);
	}
}
