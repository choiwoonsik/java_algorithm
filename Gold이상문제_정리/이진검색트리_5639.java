package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
50
30
24
5
28
45
98
52
60
 */

public class 이진검색트리_5639 {
	static ArrayList<Integer> nodelist = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int node;
		String s;
		while ((s = br.readLine()) != null)
		{
			node = Integer.parseInt(s);
			nodelist.add(node);
		}

		int root = nodelist.remove(0);
		Node top = new Node(root,null, null);

		for (int i : nodelist)
		{
			find(top, i);
		}

//		System.out.println("전위");
//		preOrder(top);
//
//		System.out.println("후위");
		postOrder(top);
	}

	private static void find(Node now, int n) {
		// 작은 경우
		if (now.val > n) {
			if (now.left != null)
				find(now.left, n);
			else
				now.left = new Node(n, null, null);
		}
		else {
			if (now.right != null)
				find(now.right, n);
			else
				now.right = new Node(n, null, null);
		}
	}

	private static void preOrder(Node root) {
		System.out.println(root.val);
		if (root.left != null) {
			preOrder(root.left);
		}
		if (root.right != null) {
			preOrder(root.right);
		}
	}

	private static void postOrder(Node root) {
		if (root.left != null)
			postOrder(root.left);
		if (root.right != null)
			postOrder(root.right);
		System.out.println(root.val);
	}

	private static class Node
	{
		int val;
		Node left;
		Node right;

		public Node(int val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
