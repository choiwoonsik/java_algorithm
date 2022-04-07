package 트리;

import java.io.*;
import java.util.*;
/*
12
0 1 100
1 2 1000
0 3 100
3 4 1000
0 5 1
6 5 10
7 5 10
7 8 10
8 9 10
9 10 100
11 9 100
 */
public class 트리수정_12912 {
	static int N;
	static ArrayList<Edge>[] adj;
	static boolean[] visited;
	static Edge[] edges;
	static long[] dp;
	static long MAX_DIAMETER;
	static long MAX_LEN;
	static int LAST;
	static int END;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		edges = new Edge[N + 1];
		visited = new boolean[N + 1];
		dp = new long[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[from].add(new Edge(i, from, to, dist));
			adj[to].add(new Edge(i, to, from, dist));
			edges[i] = new Edge(i, from, to, dist);
		}

		for (int edge = 0; edge < N - 1; edge++) {
			Edge curEdge = edges[edge];

			long firstD = treeDiameter(curEdge.from, curEdge.to);
			long secondD = treeDiameter(curEdge.to, curEdge.from);

			MAX_DIAMETER = Math.max(firstD + secondD + curEdge.dist, MAX_DIAMETER);
		}

		System.out.print(MAX_DIAMETER);
	}

	private static long treeDiameter(int from, int to) {
		LAST = -1;
		END = -1;

		if (adj[from].size() == 2) END = from;

		MAX_LEN = 0;
		Arrays.fill(dp, 0);
		Arrays.fill(visited, false);
		visited[to] = true;
		dfs(from);

		if (LAST == -1) LAST = from;
		MAX_LEN = 0;
		Arrays.fill(dp, 0);
		Arrays.fill(visited, false);
		visited[to] = true;
		dfs(LAST);

		return MAX_LEN;
	}

	private static void dfs(int node) {
		visited[node] = true;

		if ((adj[node].size() == 1 || node == END) && dp[node] > MAX_LEN) {
			MAX_LEN = Math.max(MAX_LEN, dp[node]);
			LAST = node;
			return;
		}

		for (Edge child : adj[node]) {
			if (visited[child.to]) continue;

			dp[child.to] += dp[node] + child.dist;

			dfs(child.to);
		}
	}

	private static class Edge{
		int number;
		int from;
		int to;
		int dist;

		public Edge(int number, int from, int to, int dist) {
			this.number = number;
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}
}
