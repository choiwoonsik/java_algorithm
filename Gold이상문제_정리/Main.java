package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class Solution{
	static int[] money_calc;
	static ArrayList<pair>[] adj;
	static Map<String, Integer> name_id_map = new HashMap<>();
	static Map<String, String> child_parent_map = new HashMap<>();
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = {};
		money_calc = new int[enroll.length];

		// map
		for (int i = 0; i < enroll.length; i++)
			name_id_map.put(enroll[i], i+1);

		// init tree
		adj = new ArrayList[enroll.length+1];
		for (int i = 0; i < enroll.length + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		// make tree
		for (int i = 1; i <= enroll.length; i++) {
			// for root
			if (referral[i - 1].equals("-")) {
				adj[0].add(new pair(i, enroll[i - 1]));
				child_parent_map.put(enroll[i - 1], "center");
			} else { // other
				int refer_id = name_id_map.get(referral[i - 1]);
				adj[refer_id].add(new pair(i, enroll[i - 1]));
				child_parent_map.put(enroll[i - 1], referral[i - 1]);
			}
		}
		// calc_each_person
		for (int i = 0; i < seller.length; i++) {
			String s = seller[i];
			int earn_money = amount[i];
			calc_seller(s, earn_money);
		}

		answer = money_calc.clone();

		return answer;
	}

	private void calc_seller(String seller, int earn_money) {
		String my_name = seller;
		while (true)
		{
			// my turn
			int my_id = name_id_map.get(my_name);
			int parent_money = earn_money / 10;
			if (parent_money < 1) {
				money_calc[my_id] += earn_money;
				break;
			} else {
				earn_money = earn_money * 10 / 9;
				money_calc[my_id] += earn_money;
			}
			// parent turn
			String parent = child_parent_map.get(seller);
			my_name = parent;
			earn_money = parent_money;
			if (parent.equals("center")) break;
		}
	}

	private static class pair
	{
		int id;
		String name;

		public pair(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}

class Solution1 {
	static int row, col;
	static int[][] sub_map;
	static int[][] map;

	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		map = new int[rows+1][columns+1];
		row = rows;
		col = columns;

		int n = 1;
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				map[i][j] = n++;
			}
		}

		for (int t = 0; t < queries.length; t++) {
			int min = turn(t, queries);
			answer[t] = min;
		}

		return answer;
	}

	private static int turn(int t, int[][] queries) {
		int y1 = queries[t][0];
		int x1 = queries[t][1];
		int y2 = queries[t][2];
		int x2 = queries[t][3];

		sub_map = new int[row+1][col+1];
		for (int y = y1; y < y2; y++)
			System.arraycopy(map[y], x1, sub_map[y], x1, x2 + 1 - x1);
		int min = row * col+1;

		// top
		for (int x = x1; x < x2; x++) {
			sub_map[y1][x + 1] = map[y1][x];
			min = Math.min(min, map[y1][x]);
		}
		// down
		for (int x = x1+1; x <= x2; x++) {
			sub_map[y2][x - 1] = map[y2][x];
			min = Math.min(min, map[y2][x]);
		}
		// right
		for (int y = y1; y < y2; y++) {
			sub_map[y + 1][x2] = map[y][x2];
			min = Math.min(min, map[y][x2]);
		}
		// left
		for (int y = y1+1; y <= y2; y++) {
			sub_map[y - 1][x1] = map[y][x1];
			min = Math.min(min, map[y][x1]);
		}
		// copy
		for (int y = y1; y < y2; y++)
			System.arraycopy(sub_map[y], x1, map[y], x1, x2 - x1 + 1);
		return min;
	}

}

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
