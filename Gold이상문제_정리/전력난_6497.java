package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 전력난_6497 {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			pq.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) break;

			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}
			int total = 0;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				pq.add(new Edge(from, to, cost));
				total += cost;
			}

			int total_cost = 0;
			while (!pq.isEmpty()) {

				Edge edge = pq.poll();

				if (find(edge.from) != find(edge.to)) {
					total_cost += edge.cost;
					union(edge.from, edge.to);
				}
			}
			str.append(total - total_cost).append("\n");
		}
		System.out.print(str);
	}

	private static void union(int u, int v) {
		u = parent[u];
		v = parent[v];
		if (u < v)
			parent[u] = parent[parent[v]];
		else
			parent[v] = parent[parent[u]];
	}

	private static int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

	private static class Edge {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
