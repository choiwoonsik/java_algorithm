package 트리;

import java.io.*;
import java.util.*;

public class 북쪽나라의도로_1595 {
	static int N = 10000;
	static ArrayList<Node>[] adj = new ArrayList[10001];
	static boolean[] visited = new boolean[10001];
	static int LAST;
	static int SIZE;
	static int LONGEST;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		dp = new int[N + 1];

		String line = null;
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			if (st.countTokens() != 3) break;

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj[u].add(new Node(v, d));
			adj[v].add(new Node(u, d));
			SIZE = Math.max(SIZE, Math.max(v, u));
		}

		dfs(1, 0);

		Arrays.fill(dp, 0);
		Arrays.fill(visited, false);

		dfs(LAST, 0);
		System.out.println(LONGEST);
	}

	private static void dfs(int node, int dist) {
		visited[node] = true;
		dp[node] += dist;

		if (adj[node].size() == 1 && dp[node] > LONGEST) {
			LONGEST = dp[node];
			LAST = node;
			return;
		}

		for (Node child : adj[node]) {
			if (visited[child.here]) continue;

			dfs(child.here, child.dist + dist);
		}
	}

	private static class Node {
		int here;
		int dist;

		public Node(int here, int dist) {
			this.here = here;
			this.dist = dist;
		}
	}
}
