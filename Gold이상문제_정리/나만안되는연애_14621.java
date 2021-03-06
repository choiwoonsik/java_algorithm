package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 나만안되는연애_14621 {
	static char[] MW;
	static int[] parent;
	static int N, M, cost;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s->s.c));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		MW = new char[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			MW[i] = st.nextToken().charAt(0);

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			int dist = Integer.parseInt(st.nextToken());
			if (MW[c1] != MW[c2]) {
				pq.add(new Edge(c1, c2, dist));
				pq.add(new Edge(c2, c1, dist));
			}
		}

		while (!pq.isEmpty())
		{
			Edge edge = pq.poll();

			int u = find(edge.u);
			int v = find(edge.v);

			if (u != v) {
				cost += edge.c;
				union(u, v);
			}
		}

		for (int i = 1; i < N; i++) {
			if (find(parent[i]) != find(parent[0])) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(cost);
	}

	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u < v) parent[u] = parent[parent[v]];
		else parent[v] = parent[parent[u]];
	}

	private static int find(int u) {
		if (parent[u] == u) return u;
		return parent[u] = find(parent[u]);
	}

	private static class Edge {
		int u, v, c;

		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}
	}
}
