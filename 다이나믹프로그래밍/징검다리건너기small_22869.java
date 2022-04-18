package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
5 3
1 4 1 3 1
 */
public class 징검다리건너기small_22869 {
	static int N;
	static int K;
	static int[] costs;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		costs = new int[N];
		dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}

		dfs();
	}

	private static void dfs() {
		int power;

		for (int go = 0; go < N - 1; go++) {
			for (int to = go + 1; to < N; to++) {
				power = (to - go) * (1 + Math.abs(costs[go] - costs[to]));
				if (power <= K && dp[go] != Integer.MAX_VALUE) {
					dp[to] = dp[go] + power;
				}
			}
		}

		System.out.print(dp[N-1] == Integer.MAX_VALUE ? "NO" : "YES");
	}
}
