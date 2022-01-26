package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;

/*
N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
 */

public class LCA2_11438 {
	static int[] depth;
	static int[][] parentTalbe;
	static int H;
	static int N;
	static int M;
	static StringBuilder answer = new StringBuilder();
	static ArrayList<Integer>[] tree;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		depth = new int[N + 1];
		tree = new ArrayList[N + 1];
		calcHeight();
		parentTalbe = new int[H + 1][N + 1];
		Arrays.fill(depth, -1);

		for (int i = 1; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());

			tree[parent].add(child);
			tree[child].add(parent);
		}
		initAndDepth(1);
		makeParentTalbe();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int nodeA = Integer.parseInt(st.nextToken());
			int nodeB = Integer.parseInt(st.nextToken());

			int lca = funcLCA(nodeA, nodeB);
			answer.append(lca).append("\n");
		}

		System.out.print(answer);
	}

	private static int funcLCA(int nodeA, int nodeB) {
		// nodeA가 더 아래에 있도록 맞춤
		if (depth[nodeA] < depth[nodeB]) {
			return funcLCA(nodeB, nodeA);
		}

		// 높이 맞추기
		for (int i = 0; i <= H; i++) {
			if (((depth[nodeA] - depth[nodeB]) & (1 << i)) >= 1) {
				nodeA = parentTalbe[i][nodeA];
			}
		}

		// 높이를 맞췄는데 -> 값이 같다면
		if (nodeA == nodeB) {
			return nodeA;
		}

		// 높이를 맞췃는데 -> 값이 다르다면 -> 젤 높이 올가보고, 값이 다르면 교체해서 더 올라간다
		for (int i = H; i >= 0; i--) {
			if (parentTalbe[i][nodeA] != parentTalbe[i][nodeB]) {
				nodeA = parentTalbe[i][nodeA];
				nodeB = parentTalbe[i][nodeB];
			}
		}

		return parentTalbe[0][nodeA];
	}

	private static void makeParentTalbe() {
		for (int p = 1; p <= H; p++) {
			for (int node = 0; node <= N; node++) {
				int upParent = parentTalbe[p - 1][node];
				parentTalbe[p][node] = parentTalbe[p - 1][upParent];
			}
		}
	}

	private static void initAndDepth(int node) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(node);
		depth[node] = 0;
		parentTalbe[0][node] = 0;

		while (!queue.isEmpty()) {
			int parent = queue.poll();

			for (Integer child : tree[parent]) {
				if (depth[child] == -1) {
					depth[child] = depth[parent] + 1;
					parentTalbe[0][child] = parent;
					queue.add(child);
				}
			}
		}
	}

	private static void calcHeight() {
		int n = 1;

		while (n < N) {
			n *= 2;
			H++;
		}
	}
}
