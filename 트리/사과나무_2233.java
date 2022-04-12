package 트리;

import java.io.*;
import java.util.*;

/*
5
0001011011
4 5

7
00010110010111
8 13

11
0000010110110010111011
4 13

6
000000111111
5 6
 */
public class 사과나무_2233 {
	static int N;
	static int tdx1, tdx2;
	static String bit;
	static int[] parent;
	static int[] depth;
	static int[][] intOutRecord;
	static int IN = 0;
	static int OUT = 1;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		bit = br.readLine();

		st = new StringTokenizer(br.readLine());
		int t1 = Integer.parseInt(st.nextToken()) - 1;
		int t2 = Integer.parseInt(st.nextToken()) - 1;

		parent = new int[N + 1];
		depth = new int[N + 1];
		intOutRecord = new int[N + 1][2];
		makeTree();

		for (int i = 0; i < N; i++) {
			if (intOutRecord[i][IN] == t1 || intOutRecord[i][OUT] == t1) tdx1 = i;
			if (intOutRecord[i][IN] == t2 || intOutRecord[i][OUT] == t2) tdx2 = i;
		}

		int LCA;
		if (tdx1 == tdx2) {
			LCA = tdx1;
		} else {
			LCA = findLCA(tdx1, tdx2);
		}

		System.out.print((intOutRecord[LCA][IN] + 1) + " " + (intOutRecord[LCA][OUT] + 1));
	}

	private static int findLCA(int tdx1, int tdx2) {
		if (depth[tdx1] < depth[tdx2]) {
			return findLCA(tdx2, tdx1);
		}

		while (depth[tdx1] != depth[tdx2]) {
			tdx1 = parent[tdx1];
		}

		if (tdx1 == tdx2) return tdx1;

		while (tdx1 != tdx2) {
			tdx1 = parent[tdx1];
			tdx2 = parent[tdx2];
		}

		return tdx1;
	}

	private static void makeTree() {
		int idx = 1;
		int cur = 0;
		for (int i = 1; i < bit.length(); i++) {
			if (bit.charAt(i) == '0') {
				depth[idx] = depth[cur] + 1;
				parent[idx] = cur;
				intOutRecord[idx][IN] = i;
				cur = idx;
				idx++;
			} else {
				intOutRecord[cur][OUT] = i;
				cur = parent[cur];
			}
		}
	}

	private static class Node {
		int number;
		Node left;
		Node right;

		public Node(int number, Node left, Node right) {
			this.number = number;
			this.left = left;
			this.right = right;
		}
	}
}
