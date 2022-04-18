package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
12 2
3 5
1 1
 */
public class 호텔_1106 {
	static int N;
	static int K;
	static int[][] table;
	static int COST = 0;
	static int VALUE = 1;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		table = new int[K][2];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			table[i][COST] = cost;
			table[i][VALUE] = value;
		}

		for (int i = 0; i < N + 1; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			for (int city = 0; city < table.length; city++) {
				int cost = table[city][COST];
				int value = table[city][VALUE];
				int next = i + value;
				if (next >= N) {
					next = N;
				}
				dp[next] = Math.min(dp[i] + cost, dp[next]);
			}
		}

		System.out.print(dp[N]);
	}
}
