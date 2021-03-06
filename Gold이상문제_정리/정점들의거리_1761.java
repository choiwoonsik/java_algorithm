package Gold이상문제_정리;
/*
7
1 6 13
6 3 9
3 5 7
4 1 3
2 4 20
4 7 2
3
1 6
1 4
2 6
 */

import java.io.*;
import java.util.*;

public class 정점들의거리_1761 {
	static int N;
	static int LCA_MAX = 18;
	static int[] depth;
	static int[][] lca;
	static boolean[] visited;
	static int [] distance;
	static ArrayList<Node>[] vertex;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		depth = new int[N+1];
		lca = new int[N+1][LCA_MAX+1];
		visited = new boolean[N+1];
		vertex = new ArrayList[N+1];
		distance = new int[N+1];
		for (int i = 0; i <= N; i++) {
			vertex[i] = new ArrayList<>();
		}

		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			vertex[u].add(new Node(v, d));
			vertex[v].add(new Node(u, d));
		}

		// depth만들기 - root 노드 1, 깊이 1, 부모 0 (없음)
		dfs(1, 1, 0);

		// lca만들기 - 4번째 부모는 2번째 부모의 2번째부모
		// lca[i][j] = i의 2^j번째 부모 == (i의 2^j-1번째 부모)의 2^j-1번째 부모
		build_lca();

		// 만든 lca배열을 이용해서 조상찾기
		int M = Integer.parseInt(br.readLine());
		StringBuilder str = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int dis = find_distance(u, v);
			str.append(dis).append("\n");
		}

		System.out.println(str);
	}

	private static int find_distance(int u, int v) {
		int lca = find_lca(u, v);
		int aDis = distance[u];
		int bDis = distance[v];
		return aDis+bDis - distance[lca] * 2;
	}

	private static int find_lca(int u, int v) {

		if (depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}

		for (int l = LCA_MAX; l >= 0; l--) {
			if (depth[u] >= depth[v] + (1 << l)) {
				u = lca[u][l];
			}
		}

		if (u == v)
			return u;

		for (int l = LCA_MAX; l >= 0; l--) {
			if (lca[u][l] != lca[v][l]) {
				u = lca[u][l];
				v = lca[v][l];
			}
		}

		return lca[u][0];
	}

	private static void build_lca() {
		for (int l = 1; l < LCA_MAX; l++) {
			for (int n = 1; n <= N; n++) {
				lca[n][l] = lca[lca[n][l-1]][l-1];
			}
		}
	}

	private static void dfs(int root, int d, int p) {
		visited[root] = true;
		depth[root] = d;
		lca[root][0] = p;

		for (Node next : vertex[root]) {
			if (!visited[next.to]) {
				distance[next.to] = distance[root] + next.cost;
				dfs(next.to, d+1, root);
			}
		}
	}

	private static class Node {
		int to, cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
