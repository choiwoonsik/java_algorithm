package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] lca;
	static int[] depth;
	static int N = 1;
	public static void main(String[] args) throws IOException {

		Node tree = new Node();
		tree.n = -1;
		makeTree(tree);

		preOrder(tree);
		System.out.println();
		System.out.println("***");
		inOrder(tree);
	}

	private static void makeTree(Node root) {
		root.left = new Node();
		root.right = new Node();
		root.left.n = N++;
		root.right.n = N++;
		if (N >= 10)
			return;
		makeTree(root.left);
		makeTree(root.right);
	}

	private static void preOrder(Node root) {
		if (root == null) return;
		visit(root);
		preOrder(root.left);
		preOrder(root.right);
	}

	private static void inOrder(Node root) {
		if (root == null) return;
		inOrder(root.left);
		visit(root);
		inOrder(root.right);
	}

	private static void postOrder(Node root) {
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		visit(root);
	}

	private static void visit(Node root) {
		System.out.print(root.n +" -> ");
	}

	private static class Node {
		int n;
		Node left = null;
		Node right = null;
	}

	private static int find_lca(int x, int y)
	{
		if (depth[x] > depth[y]) {
			int tmp = y;
			y = x;
			x = tmp;
		}

		// 가장 멀리 뛸수 있는 승수 찾으면서 올라가기
		for (int i = 17; i >= 0; i--) {
			if (depth[y] >= depth[x] + (1 << i)) {
				y = lca[y][i];
			}
		}

		// 올라갔더니 lcs가 x
		if (y == x) {
			return x;
		}

		for (int i = 17; i >= 0 ; i--) {

			// 최대로 올라갈수 있는 조상부터 확인해가면서 다르다면 그 조상으로 올라감
			if (lca[y][i] != lca[x][i]) {
				y = lca[y][i];
				x = lca[x][i];
			}
		}
		// 현재 위치가 바로 공통조상이다. 다를때까지 올라왔으므로 현재가 같은 조상
		return lca[x][0];
	}

	private static void swap(int x, int y) {
		int t = x;
		x = y;
		y = t;
	}

	private static class Tree
	{
		Tree[] next = new Tree[26];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		Tree root = new Tree();

		private void insert(String str) {
			Tree current = root;

			System.out.println("input: "+str);
			for (int i = 0; i < str.length(); i++) {
				if (current.next[str.charAt(i) - 'a'] == null)
				{
					current.next[str.charAt(i) - 'a'] = new Tree();
				}
				current = current.next[str.charAt(i) - 'a'];
			}
			current.isEnd = true;
		}
	}
}
