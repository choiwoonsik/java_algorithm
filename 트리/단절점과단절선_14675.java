package 트리;

import java.io.*;
import java.util.*;

/*
5
1 2
2 3
3 4
4 5
4
1 1
1 2
2 1
2 2

7
1 2
1 3
2 4
2 5
3 6
3 7
 */
public class 단절점과단절선_14675 {
	static int N;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static boolean[] isCutNode;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		isCutNode = new boolean[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1);

		StringBuilder query = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());

			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (type == 1) {
				query.append(isCutNode[num] ? "yes" : "no");
			} else query.append("yes");
			query.append("\n");
		}

		System.out.print(query);
	}

	private static void dfs(int root) {
		visited[root] = true;

		if (adj[root].size() >= 2) {
			isCutNode[root] = true;
		}

		for (Integer child : adj[root]) {
			if(visited[child]) continue;

			System.out.println("> "+child);
			dfs(child);
		}
	}
}
