package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 트리의기둥과가지_20924 {
	static int N, R, G;
	static long Max;
	static boolean[] visited;
	static ArrayList<Node>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		visited = new boolean[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			int dist = Integer.parseInt(input[2]);
			adj[from].add(new Node(to, dist));
			adj[to].add(new Node(from, dist));
		}

		long treeH = treeHeigth(R, 0);
		long longestL = leafLen(G, 0);
		System.out.printf("%d %d", treeH, longestL);
	}

	private static long leafLen(int root, long len) {
		if (leafCount(root) == 0) return len;

		for (Node next : adj[root]) {
			if (!visited[next.n]) {
				visited[next.n] = true;
				Max = Math.max(Max, leafLen(next.n, next.d + len));
			}
		}
		return Max;
	}

	private static long treeHeigth(int root, long len) {
		visited[root] = true;

		if (leafCount(root) != 1) {
			G = root;
			return len;
		} else {
			for (Node next : adj[root]) {
				if (!visited[next.n]) {
					return treeHeigth(next.n, len + next.d);
				}
			}
		}
		return -1;
	}

	private static int leafCount(int node)
	{
		int count = 0;
		for (Node next : adj[node]) {
			if (!visited[next.n]) count++;
		}
		return count;
	}

	private static class Node
	{
		int n;
		int d;

		public Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}
