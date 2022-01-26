package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;

public class 최단경로_1753 {
	static int V;
	static int E;
	static int INF = 987654321;
	static int[] dp;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dp = new int[V + 1];
		adj = new ArrayList[V + 1];

		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
			dp[i] = INF;
		}

		int S = Integer.parseInt(br.readLine());
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, w));
		}

		dp[S] = 0;
		dijkstra(S);

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (dp[i] == INF) answer.append("INF");
			else answer.append(dp[i]);
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(E -> E.w));
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.w > dp[cur.node]) continue;

			for (Edge next : adj[cur.node]) {
				if (dp[next.node] > dp[cur.node] + next.w) {
					dp[next.node] = dp[cur.node] + next.w;
					pq.add(new Edge(next.node, dp[next.node]));
				}
			}
		}
	}

	private static class Edge {
		int node;
		int w;

		public Edge(int to, int w) {
			this.node = to;
			this.w = w;
		}
	}
}
