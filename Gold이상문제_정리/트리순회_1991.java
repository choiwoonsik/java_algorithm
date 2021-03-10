package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 트리순회_1991 {
	static node[] tree;
	static int N;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		tree = new node[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			char node = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			tree[node - 'A'] = new node(node, left, right);
		}

		preOrder('A');
		str.append("\n");
		inOrder('A');
		str.append("\n");
		postOrder('A');
		System.out.println(str);
	}

	private static void preOrder(char now) {
		str.append(now);
		if (tree[now-'A'].left != '.')
			preOrder(tree[now-'A'].left);
		if (tree[now-'A'].right != '.')
		preOrder(tree[now-'A'].right);
	}

	private static void inOrder(char now) {
		if (tree[now - 'A'].left != '.')
			inOrder(tree[now - 'A'].left);
		str.append(tree[now - 'A'].n);
		if (tree[now - 'A'].right != '.')
			inOrder(tree[now - 'A'].right);
	}

	private static void postOrder(char now) {
		if (tree[now - 'A'].left != '.')
			postOrder(tree[now - 'A'].left);
		if (tree[now - 'A'].right != '.')
			postOrder(tree[now - 'A'].right);
		str.append(tree[now - 'A'].n);
	}

	private static class node
	{
		char n, left, right;

		public node(char n, char left, char right) {
			this.n = n;
			this.left = left;
			this.right = right;
		}
	}
}
