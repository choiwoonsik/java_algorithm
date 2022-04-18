package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
3 10
1
2
5
 */
public class 동전1_2293 {
	static int N;
	static int K;
	static int[] coins;
	static int[] dp;
	static int[] used;
	static HashSet<String> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N + 1];
		used = new int[N + 1];
		dp = new int[K + 1];

		for (int i = 0; i < N; i++) {
			int price = Integer.parseInt(br.readLine());
			coins[i] = price;
		}

		dp[0] = 1;
		dfs();
		System.out.print(dp[K]);
	}

	private static void dfs() {
		for (int i = 0; i < N; i++) {
			int coin = coins[i];
			for (int p = 0; p <= K; p++) {
				if (p >= coin) {
					dp[p] += dp[p - coin];
				}
			}
		}
	}
}
