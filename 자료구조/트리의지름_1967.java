package 자료구조;

import java.util.*;
import java.io.*;

public class 트리의지름_1967 {
	static int N;
	static int Max;
	static int LastNode;
	static boolean[] visited;
	static ArrayList<Edge>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[10001];
		visited = new boolean[N + 1];

		for (int i = 0; i < 10001; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[u].add(new Edge(v, c));
			adj[v].add(new Edge(u, c));
		}

		dfs(1, 0);
		if (LastNode == 0) {
			System.out.println(0);
		} else {
			Arrays.fill(visited, false);
			dfs(LastNode, 0);
			System.out.println(Max);
		}
	}

	private static void dfs(int root, int cost) {
		if (visited[root]) return;

		visited[root] = true;

		if (cost > Max) {
			Max = cost;
			LastNode = root;
		}

		for (Edge child : adj[root]) {
			if (!visited[child.node]) {
				dfs(child.node, cost + child.cost);
			}
		}
	}

	private static class Edge {
		int node;
		int cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
