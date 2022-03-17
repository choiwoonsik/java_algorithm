package 인덱스트리;

import java.io.*;
import java.util.*;

/*
5 2
1 1 1 3 2
 */
public class 달려라홍준_1306 {
	static int N;
	static int M;
	static int S;
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
		tree = new int[2 * S];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			tree[S + i - 1] = Integer.parseInt(st.nextToken());
		}

		init();

		for (int pos = M; pos <= N - M + 1; pos++) {
			int light = query(1, 1, S, pos - (M - 1), pos + (M - 1));
			answer.append(light).append(" ");
		}

		System.out.print(answer);
	}

	private static void init() {
		for (int s = S - 1; s >= 1; s--) {
			tree[s] = Math.max(tree[s * 2], tree[s * 2 + 1]);
		}
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {

		if (queryRight < start || queryLeft > end)
			return 0;

		if (queryLeft <= start && end <= queryRight)
			return tree[node];

		int mid = (start + end) / 2;
		return Math.max(
				query(node * 2, start, mid, queryLeft, queryRight),
				query(node * 2 + 1, mid + 1, end, queryLeft, queryRight)
		);
	}
}
