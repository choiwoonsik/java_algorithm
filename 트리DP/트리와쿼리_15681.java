package 트리DP;

import java.util.*;
import java.io.*;

public class 트리와쿼리_15681 {
	static int N;
	static int R;
	static int Q;
	static int[] dp;
	static boolean[] visit;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		visit = new boolean[N + 1];
		adj = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(R);

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < Q; i++) {
			int q = Integer.parseInt(br.readLine());
			answer.append(dp[q]).append("\n");
		}

		System.out.print(answer);
	}

	private static void dfs(int r) {
		dp[r] += 1;
		visit[r] = true;
		for (Integer child : adj[r]) {
			if (visit[child]) continue;
			dfs(child);
			dp[r] += dp[child];
		}
	}
}
