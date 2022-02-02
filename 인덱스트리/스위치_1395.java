package 인덱스트리;

import java.io.*;
import java.util.*;

public class 스위치_1395 {
	static int N;
	static int M;
	static int S;
	static int[] lazy;
	static int[] tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = 1;
		while (S < N) S *= 2;
		lazy = new int[S * 2];
		tree = new int[S * 2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int queryLeft = Integer.parseInt(st.nextToken());
			int queryRight = Integer.parseInt(st.nextToken());

			if (cmd == 0) {
				updateRange(1, 1, S, queryLeft, queryRight);
			} else if (cmd == 1) {
				int count = query(1, 1, S, queryLeft, queryRight);
				answer.append(count).append("\n");
			}
		}

		System.out.print(answer);
	}

	private static void updateRange(int node, int start, int end, int queryLeft, int queryRight) {
		updateLazy(node, start, end);

		if (queryLeft > end || queryRight < start) return;

		if (queryLeft <= start && end <= queryRight) {
			tree[node] = (end - start + 1) - tree[node];

			if (start != end) {
				lazyTurn(node * 2);
				lazyTurn(node * 2 + 1);
			}
			return;
		}

		int mid = (start + end) / 2;
		updateRange(node * 2, start, mid, queryLeft, queryRight);
		updateRange(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	private static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] = (end - start + 1) - tree[node];

			if (start != end) {
				lazyTurn(node * 2);
				lazyTurn(node * 2 + 1);
			}

			lazy[node] = 0;
		}
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {
		updateLazy(node, start, end);

		if (queryLeft > end || queryRight < start)
			return 0;

		if (start == end)
			return tree[node];

		if (queryLeft <= start && end <= queryRight)
			return tree[node];

		int mid = (start + end) / 2;
		return query(node * 2, start, mid, queryLeft, queryRight) +
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
	}

	private static void lazyTurn(int node) {
		lazy[node] = lazy[node] == 1 ? 0 : 1;
	}
}
