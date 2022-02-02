package 인덱스트리;

import java.io.*;
import java.util.*;

public class 구간합구하기2_10999 {
	static int N;
	static int M;
	static int K;
	static int S;
	static long[] tree;
	static long[] lazy;
	static final int SUM = 2;
	static final int ADD = 1;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		S = 1;
		while (S < N) S *= 2;
		tree = new long[S * 2];
		lazy = new long[S * 2];

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			tree[S + i] = num;
		}

		init();

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == ADD) {
				int queryLeft = Integer.parseInt(st.nextToken());
				int queryRight = Integer.parseInt(st.nextToken());
				long diff = Integer.parseInt(st.nextToken());
				updateRange(1, 1, S, queryLeft, queryRight, diff);
			} else if (type == SUM) {
				int queryLeft = Integer.parseInt(st.nextToken());
				int queryRight = Integer.parseInt(st.nextToken());
				long sum = query(1, 1, S, queryLeft, queryRight);
				answer.append(sum).append("\n");
			}
		}

		System.out.print(answer);
	}

	private static void updateRange(int node, int start, int end, int queryLeft, int queryRight, long diff) {
		updateLazy(node, start, end);

		if (start > queryRight || end < queryLeft)
			return;

		if (queryLeft <= start && end <= queryRight) {
			tree[node] += (end - start + 1) * diff;
			if (start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			return;
		}

		int mid = (start + end) / 2;
		updateRange(node * 2, start, mid, queryLeft, queryRight, diff);
		updateRange(node * 2 + 1, mid + 1, end, queryLeft, queryRight, diff);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	private static long query(int node, int start, int end, int queryLeft, int queryRight) {
		updateLazy(node, start, end);

		if (queryRight < start || queryLeft > end)
			return 0;

		if (start == end)
			return tree[node];

		if (queryLeft <= start && end <= queryRight)
			return tree[node];

		int mid = (start + end) / 2;

		return query(node * 2, start, mid, queryLeft, queryRight) +
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
	}

	private static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
	}

	private static void init() {
		for (int i = S - 1; i >= 1; i--) {
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
}
