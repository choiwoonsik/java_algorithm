package 트리;

import javax.swing.*;
import java.util.*;
import java.io.*;

/*
3
1 6 4 3 5 2 7
 */
public class 완전이진트리_9934 {
	static int N;
	static Node tree;
	static ArrayList<Integer>[] floor;
	static Queue<Integer> orders;
	static int total;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tree = new Node(0, null, null);
		floor = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			floor[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		total = (int) Math.pow(2, N) - 1;
		orders = new LinkedList<>();
		for (int i = 0; i < total; i++) {
			orders.add(Integer.parseInt(st.nextToken()));
		}

		dfs(tree, 1, 0);

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (Integer n : floor[i]) {
				answer.append(n).append(" ");
			}
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void dfs(Node root, int idx, int depth) {
		if (idx > total) return;

		if (root.left == null) {
			root.left = new Node(0, null, null);
			dfs(root.left, 2 * idx, depth + 1);
		}

		floor[depth].add(orders.poll());

		if (root.right == null) {
			root.right = new Node(0, null, null);
			dfs(root.right, 2 * idx + 1, depth + 1);
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
