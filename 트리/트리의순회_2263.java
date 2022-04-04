package 트리;

import java.io.*;
import java.util.*;

public class 트리의순회_2263 {
	static int N;
	static HashMap<Integer, Integer> inorderIndex = new HashMap<>();
	static boolean[] isUsed;
	static int[] inOrder;
	static Stack<Integer> postOrder = new Stack<>();
	static Node tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		inOrder = new int[N];
		isUsed = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			inOrder[i] = Integer.parseInt(st.nextToken());
			inorderIndex.put(inOrder[i], i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			postOrder.push(Integer.parseInt(st.nextToken()));
		}

		tree = new Node(0, null, null);
		makeTree(tree, N);
		dfs(tree);
		System.out.print(answer);
	}

	private static void dfs(Node node) {
		answer.append(node.value).append(" ");
		if (node.left != null) {
			dfs(node.left);
		}
		if (node.right != null) {
			dfs(node.right);
		}
	}

	private static void makeTree(Node node, int max) {
		if (postOrder.isEmpty()) {
			return;
		}

		int cur = postOrder.pop();
		int curdex = inorderIndex.get(cur);

		isUsed[curdex] = true;
		node.value = cur;

		int right = 0;
		for (int i = curdex + 1; i < max; i++) {
			right++;
		}
		int left = 0;
		for (int i = curdex - 1; i >= 0; i--) {
			if (isUsed[i]) break;
			left++;
		}

		if (right > 0) {
			node.right = new Node(-1, null, null);
			makeTree(node.right, max);
		}
		if (left > 0) {
			node.left = new Node(-1, null, null);
			makeTree(node.left, curdex);
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
