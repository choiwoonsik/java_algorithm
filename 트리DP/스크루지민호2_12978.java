package 트리DP;

import java.io.*;
import java.util.*;

/*
5
4 5
4 2
2 3
1 2
 */
public class 스크루지민호2_12978 {
	static int N;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int[][] dp;
	static int YES = 1;
	static int NO = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		dp = new int[2][N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1);
		System.out.println(Math.min(dp[YES][1], dp[NO][1]));
	}

	private static void dfs(int root) {
		visited[root] = true;
		dp[YES][root] = 1;

		for (Integer child : adj[root]) {
			if (visited[child]) continue;
			dfs(child);

			dp[YES][root] += Math.min(dp[YES][child], dp[NO][child]);
			dp[NO][root] += dp[YES][child];
		}
	}
}
