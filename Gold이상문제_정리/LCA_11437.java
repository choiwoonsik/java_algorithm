package Gold이상문제_정리;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

/*
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15
 */
public class LCA_11437 {
	static int LCA_LAST = 17;
	static int N, M;
	static int[] depth;
	static boolean[] visited;
	static int[][] lca;
	static ArrayList<Node>[] vertex;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		depth = new int[N+1];
		visited = new boolean[N+1];
		lca = new int[N+1][LCA_LAST+1];
		vertex = new ArrayList[N+1];

		for (int i = 0; i < N+1; i++)
			vertex[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			vertex[u].add(new Node(v));
			vertex[v].add(new Node(u));
		}

		find_depth_parent(1, 0, 0);
		build_lca();

		M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			find_lca(x, y);
		}
		System.out.print(str);
	}

	private static void find_lca(int x, int y) {
		if (depth[x] > depth[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}

		for (int i = LCA_LAST; i >= 0 ; i--) {
			if (depth[y] >= depth[x] + (1 << i))
				y = lca[y][i];
		}

		if (y == x) {
			str.append(x).append("\n");
			return;
		}

		for (int i = LCA_LAST; i >= 0 ; i--) {
			if (lca[y][i] != lca[x][i]) {
				y = lca[y][i];
				x = lca[x][i];
			}
		}
		str.append(lca[x][0]).append("\n");
	}

	private static void build_lca() {
		for (int i = 1; i <= LCA_LAST; i++) {
			for (int j = 1; j <= N; j++) {
				lca[j][i] = lca[lca[j][i-1]][i-1];
			}
		}
	}

	private static void find_depth_parent(int node, int d, int parent) {
		visited[node] = true;
		depth[node] = d;
		lca[node][0] = parent;

		for (Node next : vertex[node])
		{
			if (!visited[next.v]) {
				find_depth_parent(next.v, d + 1, node);
			}
		}
	}

	private static class Node {
		int v;

		public Node(int v) {
			this.v = v;
		}
	}
}
