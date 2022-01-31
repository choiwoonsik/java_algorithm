package 다익스트라;

import java.util.*;
import java.io.*;

public class 최소비용구하기_1916 {
	static int N;
	static int M;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[u].add(new Edge(v, c));
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		System.out.println(dijkstra(s, e));
	}

	private static long dijkstra(int s, int e) {
		long INF = Long.MAX_VALUE;
		long[] dp = new long[N + 1];
		Arrays.fill(dp, INF);

		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(E -> E.cost));
		pq.add(new Edge(s, 0));
		dp[s] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.cost > dp[cur.node]) {
				continue;
			}

			for (Edge next : adj[cur.node]) {
				if (dp[next.node] > dp[cur.node] + next.cost) {
					dp[next.node] = dp[cur.node] + next.cost;
					pq.add(new Edge(next.node, dp[next.node]));
				}
			}
		}

		return dp[e];
	}

	private static class Edge {
		int node;
		long cost;

		public Edge(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
