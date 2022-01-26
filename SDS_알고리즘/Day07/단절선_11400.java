package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;
/*
7 8
1 4
4 5
5 1
1 6
6 7
2 7
7 3
2 3

 */
public class 단절선_11400 {
	static ArrayList<Integer>[] adj;
	static int V;
	static int E;
	static int order;
	static ArrayList<Edge> cutList = new ArrayList<>();
	static int[] searchOrder;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		searchOrder = new int[V + 1];

		adj = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].add(v);
			adj[v].add(u);
		}

		for (int i = 1; i <= V; i++) {
			if (searchOrder[i] == 0) {
				dfs(i, 0);
			}
		}

		cutList.sort((e0, e1) -> {
			if (e0.u != e1.u) {
				return Integer.compare(e0.u, e1.u);
			} else {
				return Integer.compare(e0.v, e1.v);
			}
		});

		answer.append(cutList.size()).append("\n");
		for (Edge edge : cutList) {
			answer.append(edge.u).append(" ").append(edge.v).append("\n");
		}
		System.out.print(answer);
	}

	private static int dfs(int cur, int root) {
		searchOrder[cur] = ++order;
		int min = searchOrder[cur];

		for (Integer next : adj[cur]) {
			if (next == root) continue;

			if (searchOrder[next] != 0) {
				min = Math.min(min, searchOrder[next]);
				continue;
			}

			int low = dfs(next, cur);

			if (low > searchOrder[cur]) {
				cutList.add(new Edge(Math.min(cur, next), Math.max(cur, next)));
			}

			min = Math.min(min, low);
		}

		return min;
	}

	private static class Edge {
		int u, v;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
}
