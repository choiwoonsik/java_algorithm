package 트리DP;

import java.io.*;
import java.util.*;

public class 트리의독립집합_2213 {
	static int N;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[][] trace;
	static int[] w;
	static boolean[] visited;
	static int[][] dp;
	static int YES = 0;
	static int NO = 1;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		w = new int[N + 1];
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		dp = new int[2][N + 1];
		trace = new ArrayList[2][N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
			trace[0][i] = new ArrayList<>();
			trace[1][i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1);

		StringBuilder answer = new StringBuilder();
		if (dp[YES][1] > dp[NO][1]) {
			answer.append(dp[YES][1]).append("\n");
			trace[YES][1].sort(null);
			for (Integer traceNode : trace[YES][1]) {
				answer.append(traceNode).append(" ");
			}
		} else {
			answer.append(dp[NO][1]).append("\n");
			trace[NO][1].sort(null);
			for (Integer traceNode : trace[NO][1]) {
				answer.append(traceNode).append(" ");
			}
		}

		System.out.print(answer);
	}

	private static void dfs(int root) {
		visited[root] = true;

		dp[YES][root] += w[root];
		trace[YES][root].add(root);

		dp[NO][root] = 0;

		for (Integer child : adj[root]) {
			if (visited[child]) continue;
			dfs(child);

			dp[YES][root] += dp[NO][child];
			trace[YES][root].addAll(trace[NO][child]);

			dp[NO][root] += Math.max(dp[YES][child], dp[NO][child]);
			if (dp[YES][child] > dp[NO][child]) {
				trace[NO][root].addAll(trace[YES][child]);
			} else {
				trace[NO][root].addAll(trace[NO][child]);
			}
		}
	}
}
