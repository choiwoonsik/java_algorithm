package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
3 15
1
5
12
 */
public class 동전2_2294 {
	static int N;
	static int K;
	static int[] coin;
	static HashSet<Integer> coins = new HashSet<>();
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			coins.add(Integer.parseInt(br.readLine()));
		}

		int size = coins.size();
		coin = new int[size];
		dp = new int[K + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		int i = 0;
		for (Integer price : coins) {
			coin[i++] = price;
		}

		dp[0] = 0;
		for (int p = 0; p <= K; p++) {
			if (dp[p] == Integer.MAX_VALUE) continue;
			for (int c : coin) {
				if (p + c <= K) {
					dp[p + c] = Math.min(dp[p] + 1, dp[p + c]);
				}
			}
		}

		System.out.print(dp[K] == Integer.MAX_VALUE ? -1 : dp[K]);
	}
}
