package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;

public class 플로이드_11404 {
	static int N;
	static int M;
	static int INF = 987654321;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dp = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(dp[i], INF);
			dp[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dp[u][v] = Math.min(dp[u][v], c);
		}

		for (int k = 1; k < N + 1; k++) {
			for (int u = 1; u < N + 1; u++) {
				for (int v = 1; v < N + 1; v++) {
					if (u == v) continue;
					if (dp[u][v] > dp[u][k] + dp[k][v]) {
						dp[u][v] = dp[u][k] + dp[k][v];
					}
				}
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (dp[i][j] == INF) answer.append(0);
				else answer.append(dp[i][j]);
				answer.append(" ");
			}
			answer.append("\n");
		}
		System.out.print(answer);
	}
}
