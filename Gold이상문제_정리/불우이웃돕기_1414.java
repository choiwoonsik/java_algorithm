package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
3
abc
def
ghi
 */
public class 불우이웃돕기_1414 {
	static int N, COST, ALL;
	static int[] parent;
	static int[][] LEN = new int[2][26];
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s->s.c));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		int count = 1;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 26; j++) {
				LEN[i][j] = count++;
			}
		}

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= N; j++) {
				if (s.charAt(j-1) - '0' == 0)
					continue;
				if (s.charAt(j-1) - 'a' >= 0) {
					ALL += LEN[0][s.charAt(j - 1) - 'a'];
					pq.add(new Edge(i, j, LEN[0][s.charAt(j-1) - 'a']));
					pq.add(new Edge(j, i, LEN[0][s.charAt(j-1) - 'a']));
				}
				else {
					ALL += LEN[1][s.charAt(j - 1) - 'A'];
					pq.add(new Edge(i, j, LEN[1][s.charAt(j-1) - 'A']));
					pq.add(new Edge(j, i, LEN[1][s.charAt(j-1) - 'A']));
				}
			}
		}

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (find(edge.u) != find(edge.v)) {
				COST += edge.c;
				union(edge.u, edge.v);
			}
		}

		for (int i = 2; i < N+1; i++) {
			if (find(parent[i]) != find(parent[1])) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ALL - COST);
	}
	private static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u < v) parent[u] = parent[parent[v]];
		else parent[v] = parent[parent[u]];
	}

	private static int find(int v) {
		if (parent[v] == v) return v;
		return parent[v] = find(parent[v]);
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
