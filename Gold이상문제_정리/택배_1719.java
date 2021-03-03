package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 택배_1719 {
	static int INF = 987654321;
	static int N, E;
	static int[][] dp;
	static int[][] trace;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		trace = new int[N+1][N+1];
		dp = new int[N+1][N+1];

		for (int i = 0; i <= N; i++) {
			Arrays.fill(trace[i], 0);
			Arrays.fill(dp[i], INF);
		}

		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			dp[from][to] = dp[to][from] = cost;
		}

		floyd();
		make_table();
		System.out.print(answer);
	}

	private static void make_table() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					answer.append("- ");
				else if (trace[i][j] == 0)
					answer.append(j).append(" ");
				else
					answer.append(trace[i][j]).append(" ");
			}
			answer.append("\n");
		}
	}

	private static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int s = 1; s <= N; s++) {
				if (k == s) continue;
				for (int e = 1; e <= N; e++) {
					if (s == e) continue;
					if (dp[s][e] > dp[s][k] + dp[k][e]) {
						dp[s][e] = dp[s][k] + dp[k][e];
						if (trace[s][k] == 0) {
							// 아직 이전에 먼저 들린 곳이없으면
							trace[s][e] = k;
						} else {
							// 있으면 먼저 들린 곳
							trace[s][e] = trace[s][k];
						}
					}
				}
			}
		}
	}
}
