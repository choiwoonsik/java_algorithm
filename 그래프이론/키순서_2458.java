package 그래프이론;

import java.util.*;
import java.io.*;

public class 키순서_2458 {
	static int INF = 987654321;
	static int N;
	static int M;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(dp[i], INF);
			dp[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int smaller = Integer.parseInt(st.nextToken());
			int taller = Integer.parseInt(st.nextToken());
			dp[smaller][taller] = 1;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (dp[i][j] > dp[i][k] + dp[k][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
					}
				}
			}
		}

		int count = 0;
		boolean know;
		for (int i = 1; i < N + 1; i++) {
			know = true;
			for (int j = 1; j < N + 1; j++) {
				if (i == j) continue;
				if (dp[i][j] == INF && dp[j][i] == INF) {
					know = false;
					break;
				}
			}
			if (know) count++;
		}
		System.out.println(count);
	}
}
