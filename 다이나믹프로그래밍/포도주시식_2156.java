package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
6
6
10
13
9
8
1
 */
public class 포도주시식_2156 {
	static int N;
	static int[] table;
	static int[] dp;
	static int MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		table = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			table[i] = Integer.parseInt(br.readLine());
		}

		dp[0] = 0;
		if (N >= 1) {
			dp[1] = table[1];
		}
		if (N >= 2) {
			dp[2] = table[1] + table[2];
		}
		for (int i = 3; i <= N; i++) {
			dp[i] = dp[i - 3] + table[i - 1] + table[i];
			dp[i] = Math.max(dp[i], dp[i - 2] + table[i]);
			dp[i] = Math.max(dp[i], dp[i - 1]);
		}

		System.out.print(dp[N]);
	}
}
