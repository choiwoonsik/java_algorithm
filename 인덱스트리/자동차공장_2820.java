package 인덱스트리;

import java.io.*;
import java.util.*;

public class 자동차공장_2820 {
	static int N;
	static int M;
	static int S;
	static int inOutTime;
	static int[] lazy;
	static int[] in;
	static int[] out;
	static int[] tree;
	static int[] pays;
	static ArrayList<Integer>[] adj;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = 1;
		while (S < N) S *= 2;
		adj = new ArrayList[N + 1];
		in = new int[N + 1];
		out = new int[N + 1];
		lazy = new int[S * 2];
		tree = new int[S * 2];
		pays = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}


		for (int i = 1; i <= N; i++) {
			if (i == 1) {
				int pay = Integer.parseInt(br.readLine());
				pays[i] = pay;
			} else {
				st = new StringTokenizer(br.readLine());
				int pay = Integer.parseInt(st.nextToken());
				int boss = Integer.parseInt(st.nextToken());
				adj[boss].add(i);
				pays[i] = pay;
			}
		}

		dfs(1);

		for (int i = 1; i < N + 1; i++) {
			tree[S + in[i] - 1] = pays[i];
		}


		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			String type = st.nextToken();
			if (type.equals("p")) {
				int node = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				int queryLeft = in[node] + 1;
				int queryRight = out[node];
				updateRange(1, 1, S, queryLeft, queryRight, diff);
			} else if (type.equals("u")) {
				int node = Integer.parseInt(st.nextToken());
				int queryLeft = in[node];
				int queryRight = in[node];
				int pay = query(1, 1, S, queryLeft, queryRight);
				answer.append(pay).append("\n");
			}
		}

		System.out.print(answer);
	}

	private static void dfs(int node) {
		in[node] = ++inOutTime;
		for (int child : adj[node]) {
			dfs(child);
		}
		out[node] = inOutTime;
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {
		updateLazy(node, start, end);

		if (end < queryLeft || start > queryRight)
			return 0;

		if (start == end) return tree[node];

		if (queryLeft <= start && end <= queryRight)
			return tree[node];

		int mid = (start + end) / 2;
		return query(node * 2, start, mid, queryLeft, queryRight) +
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
	}

	private static void updateRange(int node, int start, int end, int queryLeft, int queryRight, int diff) {
		updateLazy(node, start, end);

		if (end < queryLeft || start > queryRight)
			return;

		if (queryLeft <= start && end <= queryRight) {
			tree[node] += diff;

			if (start != end) {
				lazy[node * 2] += diff;
				lazy[node * 2 + 1] += diff;
			}
			return;
		}

		int mid = (start + end) / 2;
		updateRange(node * 2, start, mid, queryLeft, queryRight, diff);
		updateRange(node * 2 + 1, mid + 1, end, queryLeft, queryRight, diff);
	}

	private static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += lazy[node];

			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}

			lazy[node] = 0;
		}
	}
}
