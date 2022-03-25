package 트리DP;

import java.io.*;
import java.util.*;

/*
7
1000 3000 4000 1000 2000 2000 7000
1 2
2 3
4 3
4 5
6 2
6 7
 */
public class 우수마을_1949 {
	static int N;
	static int[][] dp;
	static int NICE = 0;
	static int NOT_NICE = 1;
	static boolean[] visited;
	static int[] populations;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		populations = new int[N + 1];
		adj = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1);
		System.out.print(Math.max(dp[1][NICE], dp[1][NOT_NICE]));
	}

	private static void dfs(int root) {
		dp[root][NICE] += populations[root];
		visited[root] = true;

		for (int neighbors : adj[root]) {
			if (visited[neighbors]) continue;

			dfs(neighbors);

			dp[root][NOT_NICE] += Math.max(dp[neighbors][NICE], dp[neighbors][NOT_NICE]);
			dp[root][NICE] += dp[neighbors][NOT_NICE];
		}
	}
}
