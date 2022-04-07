package 트리;

import java.io.*;
import java.util.*;
/*
7
S 100 3
W 110 1
S 10 1
W 500 2
S 511 5
S 3 1

7
W 10 1
W 40 2
W 10 2
S 100 3
S 50 3
S 20 4
 */
public class 양구출작전_16437 {
	static int N;
	static ArrayList<Node>[] adj;
	static long[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		dp = new long[N + 1];

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int u = 2; u <= N; u++) {
			st = new StringTokenizer(br.readLine());
			String type = st.nextToken();
			int count = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (type.equals("W")) {
				adj[v].add(new Node(u, -count));
			} else {
				adj[v].add(new Node(u, count));
			}
		}

		dfs(1);
		System.out.println(dp[1]);
	}

	private static void dfs(int node) {
		for (Node child : adj[node]) {
			dp[child.here] = child.count;
			dfs(child.here);
			dp[node] += Math.max(dp[child.here], 0);
		}
	}

	private static class Node {
		int here;
		int count;

		public Node(int here, int count) {
			this.here = here;
			this.count = count;
		}
	}
}
