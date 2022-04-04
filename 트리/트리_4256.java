package 트리;

import java.io.*;
import java.util.*;

/*
2
4
3 2 1 4
2 3 4 1
8
3 6 5 4 8 7 1 2
5 6 8 4 3 1 2 7
 */
public class 트리_4256 {
	static int T;
	static int N;
	static Node root;
	static boolean[] isUsed;
	static HashMap<Integer, Integer> map;
	static Queue<Integer> preOrderQ = new LinkedList<>();
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			map = new HashMap<>();
			preOrderQ.clear();
			isUsed = new boolean[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int v = Integer.parseInt(st.nextToken());
				preOrderQ.add(v);
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int v = Integer.parseInt(st.nextToken());
				map.put(v, i);
			}

			root = new Node(0, null, null);
			makeTree(root, N);
			dfs(root);
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void dfs(Node tree) {
		if (tree.left != null)
			dfs(tree.left);
		if (tree.right != null)
			dfs(tree.right);
		answer.append(tree.value).append(" ");
	}

	private static void makeTree(Node root, int max) {
		if (preOrderQ.isEmpty()) return;

		int cur = preOrderQ.poll();
		int curdex = map.get(cur);

		isUsed[curdex] = true;
		root.value = cur;

		int left = 0;
		int right = 0;
		for (int i = curdex - 1; i >= 0; i--) {
			if (!isUsed[i]) left++;
			else break;
		}
		for (int i = curdex + 1; i < max; i++) {
			right++;
		}

		if (left != 0) {
			root.left = new Node(-1, null, null);
			makeTree(root.left, curdex);
		}
		if (right != 0) {
			root.right = new Node(-1, null, null);
			makeTree(root.right, max);
		}
	}

	private static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value, Node left, Node right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
}
