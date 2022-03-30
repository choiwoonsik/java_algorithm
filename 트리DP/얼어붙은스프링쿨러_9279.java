package 트리DP;

import java.util.*;
import java.io.*;

/*
3 1
2 1 5
1 3 4
7 7
7 6 10
7 5 10
6 4 1
6 3 1
5 2 1
5 1 2
 */
public class 얼어붙은스프링쿨러_9279 {
	static int[] dp = new int[1001];
	static int YES = 0;
	static int NO = 1;
	static ArrayList<Node>[] adj = new ArrayList[1001];
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		for (int i = 0; i < 1001; i++) {
			adj[i] = new ArrayList<>();
		}

		String line;
		while ((line = br.readLine()) != null) {
			st = new StringTokenizer(line);

			int N = Integer.parseInt(st.nextToken());
			int CENTER = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N + 1; i++) {
				adj[i].clear();
			}
			Arrays.fill(dp, 0);

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());

				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

				adj[u].add(new Node(v, w));
				adj[v].add(new Node(u, w));
			}

			dp[CENTER] = Integer.MAX_VALUE;
			dfs(CENTER, 0, -1);
			answer.append(dp[CENTER]).append("\n");
		}
		System.out.print(answer);
	}

	private static void dfs(int root, int w, int before) {
		dp[root] += w;

		if (adj[root].size() == 1) {
			return;
		}

		int childSum = 0;
		for (Node child : adj[root]) {
			if (child.here == before) continue;

			dfs(child.here, child.w, root);

			childSum += dp[child.here];
		}
		if (childSum < dp[root]) {
			dp[root] = childSum;
		}
	}

	private static class Node {
		int here;
		int w;

		public Node(int here, int w) {
			this.here = here;
			this.w = w;
		}
	}
}
