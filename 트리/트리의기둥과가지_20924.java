package 트리;

import java.io.*;
import java.util.*;

/*
12 1
1 2 1
2 3 2
3 4 3
4 5 1
5 6 2
6 7 1
5 8 1
4 9 2
4 10 3
10 11 1
10 12 3
 */
public class 트리의기둥과가지_20924 {
	static int N;
	static int R;
	static int GR;
	static int GR_H;
	static int L_H;
	static ArrayList<Node>[] adj;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			adj[u].add(new Node(v, d));
			adj[v].add(new Node(u, d));
		}

		if (adj[R].size() == 1) {
			GR_H = findGiga(R, 0);
		} else {
			GR = R;
			GR_H = 0;
		}
		System.out.println(GR);
		findLongestLine(GR, 0);

		System.out.print(GR_H + " " + L_H);
	}

	private static void findLongestLine(int root, int d) {
		visited[root] = true;
		if (adj[root].size() == 1) {
			L_H = Math.max(L_H, d);
			return;
		}

		for (Node child : adj[root]) {
			if (visited[root]) continue;
			findLongestLine(child.here, d + child.dist);
		}
	}

	private static int findGiga(int root, int h) {
		visited[root] = true;
		if (adj[root].size() != 2 && root != R) {
			GR = root;
			return h;
		}

		for (Node child : adj[root]) {
			if (visited[child.here]) continue;
			return findGiga(child.here, h + child.dist);
		}

		return h;
	}

	private static class Node {
		int here;
		int dist;

		public Node(int here, int dist) {
			this.here = here;
			this.dist = dist;
		}
	}
}
