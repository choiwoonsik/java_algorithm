package 트리;

import java.util.*;
import java.io.*;
/*
6
2 5 4
3 -1 6
5 -1 -1
4 -1 -1
6 -1 -1
1 2 3
 */
public class 트리의높이와너비_2250 {
	static int N;
	static Node root;
	static int[][] table;
	static int[] parent;
	static int[][] dp;
	static int MIN = 0;
	static int MAX = 1;
	static int height;
	static int order;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		table = new int[N + 1][2];
		parent = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			table[v][0] = left;
			table[v][1] = right;
			if (left != -1) parent[left] = v;
			if (right != -1) parent[right] = v;
		}

		int rootdex = 0;
		for (int i = 1; i < N + 1; i++) {
			if (parent[i] == 0) {
				rootdex = i;
				break;
			}
		}
		root = new Node(rootdex, 1, null, null);
		makeTree(root);

		dp = new int[height + 1][2];
		for (int i = 0; i < height + 1; i++) {
			dp[i][MIN] = Integer.MAX_VALUE;
		}
		dfs(root);

		int len;
		int floor = 1;
		int maxLen = 0;
		for (int h = 1; h < height + 1; h++) {
			len = dp[h][MAX] - dp[h][MIN] + 1;

			if (len > maxLen) {
				maxLen = len;
				floor = h;
			}
		}
		System.out.println(floor + " " + maxLen);
	}

	private static void dfs(Node node) {
		if (node.left != null) {
			dfs(node.left);
		}
		order++;
		dp[node.depth][MIN] = Math.min(dp[node.depth][MIN], order);
		dp[node.depth][MAX] = Math.max(dp[node.depth][MAX], order);
		if (node.right != null) {
			dfs(node.right);
		}
	}

	private static void makeTree(Node node) {
		height = Math.max(height, node.depth);

		if (table[node.value][0] != -1) {
			node.left = new Node(table[node.value][0], node.depth + 1, null, null);
			makeTree(node.left);
		}
		if (table[node.value][1] != -1) {
			node.right = new Node(table[node.value][1], node.depth + 1, null, null);
			makeTree(node.right);
		}
	}

	private static class Node {
		int value;
		int depth;
		Node left;
		Node right;

		public Node(int value, int depth, Node left, Node right) {
			this.value = value;
			this.depth = depth;
			this.left = left;
			this.right = right;
		}
	}
}
