package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 행성연결_16398 {
	static int N;
	static long cost;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s->s.cost));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		parent = new int[N+1];
		for (int i = 0; i < N + 1; i++) {
			parent[i] = i;
		}

		int[][] table = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int c = Integer.parseInt(st.nextToken());
				table[i][j] = c;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				pq.add(new Edge(i, j, table[i][j]));
			}
		}

		while (!pq.isEmpty())
		{
			Edge edge = pq.poll();

			if (find(edge.u) != find(edge.v)) {
				cost += edge.cost;
				union(edge.u, edge.v);
			}
		}
		System.out.println(cost);
	}

	private static int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

	private static void union(int u, int v) {
		u = parent[u];
		v = parent[v];
		if (u < v) parent[u] = parent[parent[v]];
		else parent[v] = parent[parent[u]];
	}

	private static class Edge {
		int u, v, cost;

		public Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}
}
