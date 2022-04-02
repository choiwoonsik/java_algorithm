package 트리;

import java.io.*;
import java.util.*;

public class 가장가까운공통조상_3584 {
	static int T;
	static int N;
	static int R;
	static int K;
	static int[] depth;
	static ArrayList<Integer>[] adj;
	static int[][] parentTable;
	static boolean[] isNotRoot;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			adj = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				adj[i] = new ArrayList<>();
			}
			isNotRoot = new boolean[N + 1];
			depth = new int[N + 1];

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adj[u].add(v);
				isNotRoot[v] = true;
			}

			K = 1;
			R = 1;
			while (Math.pow(2, K) < N) {
				K++;
			}
			parentTable = new int[K + 1][N + 1];
			for (int i = 1; i < N + 1; i++) {
				if (!isNotRoot[i]) {
					R = i;
					break;
				}
			}
			dfs(R, 0);
			for (int i = 1; i <= K; i++) {
				for (int j = 1; j < N + 1; j++) {
					parentTable[i][j] = parentTable[i - 1][parentTable[i - 1][j]];
				}
			}

			st = new StringTokenizer(br.readLine());
			int q1 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());

			answer.append(lca(q1, q2)).append("\n");
		}
		System.out.print(answer);
	}

	private static int lca(int q1, int q2) {
		if (depth[q1] < depth[q2]) {
			return lca(q2, q1);
		}

		for (int i = 0; i <= K; i++) {
			if ((depth[q1] - depth[q2] & 1 << i) >= 1) {
				q1 = parentTable[i][q1];
			}
		}

		if (q1 == q2) {
			return q1;
		}

		for (int h = K; h >= 0; h--) {
			if (parentTable[h][q1] != parentTable[h][q2]) {
				q1 = parentTable[h][q1];
				q2 = parentTable[h][q2];
			}
		}

		return parentTable[0][q1];
	}

	private static void dfs(int root, int d) {
		depth[root] = d;

		for (Integer child : adj[root]) {
			parentTable[0][child] = root;
			dfs(child, d + 1);
		}
	}
}
