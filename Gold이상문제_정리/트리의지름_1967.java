package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 트리의지름_1967 {
	static ArrayList<Node>[] adj = new ArrayList[10001];
	static boolean[] visited;
	static int N, IDX, MAX;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adj[parent].add(new Node(child, cost));
			adj[child].add(new Node(parent, cost));
		}

		DFS(1, 0);
		if (N != 1) {
			int far_leaf = IDX;
			Arrays.fill(visited, false);
			DFS(far_leaf, 0);
		}

		System.out.println(MAX);
	}

	private static void DFS(int root, int cost) {
		visited[root] = true;

		if (cost > MAX) {
			MAX = cost;
			IDX = root;
		}

		for (Node next : adj[root]) {
			if (!visited[next.parent])
				DFS(next.parent, cost + next.cost);
		}
	}

	private static class Node
	{
		int parent, cost;

		public Node(int parent, int cost) {
			this.parent = parent;
			this.cost = cost;
		}
	}
}
