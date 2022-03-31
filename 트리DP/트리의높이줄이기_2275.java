package 트리DP;

import java.util.*;
import java.io.*;

public class 트리의높이줄이기_2275 {
	static int N;
	static int H;
	static int ROOT;
	static boolean[] visited;
	static int[] DP;
	static int[] REDUCE;
	static int[] PARENT;
	static int SUM = 0;
	static ArrayList<Node> adj[];
	static ArrayList<Integer> tree[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N + 1];
		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		DP = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		REDUCE = new int[N + 1];
		PARENT = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			if (parent == 0 && weight == 0) {
				ROOT = i;
			} else {
				adj[parent].add(new Node(i, weight));
				PARENT[i] = parent;
			}
		}

		dfs(ROOT, 0, 0);
		calc();
		System.out.println(SUM);
	}

	private static void calc() {
		for (int i = tree.length - 1; i >= 0; i--) {
			for (int child : tree[i]) {
				if (REDUCE[child] > DP[PARENT[child]]) {
					// 부모의 누적 비용보다 자식에서 줄일 양이 더 크다는 건 자식사이의 간선 비용이 추가된 것
					SUM += REDUCE[child] - DP[PARENT[child]];
					REDUCE[PARENT[child]] = DP[PARENT[child]];
					// 부모가 줄일 양은 이제 부모까지의 누적 비용이 된다. 자식 간선은 정리됐으므로
				} else {
					// 부모의 누적 비용보다 자식에서 줄일 양이 작거나 같다면 해당 자식으로의 간선 비용은 상관 없는 것.
					// 줄일 비용 위로 업데이트
					REDUCE[PARENT[child]] = Math.max(REDUCE[PARENT[child]], REDUCE[child]);
				}
			}
		}
	}

	private static void dfs(int root, int number, int prev) {
		visited[root] = true;
		DP[root] = prev;
		tree[number].add(root);

		if (adj[root].size() == 0) {
			REDUCE[root] = DP[root] - H;
		}

		for (Node child : adj[root]) {
			if (visited[child.here]) continue;
			dfs(child.here, number + 1, prev + child.weight);
		}
	}

	private static class Node {
		int here;
		int weight;

		public Node(int here, int weight) {
			this.here = here;
			this.weight = weight;
		}
	}
}
