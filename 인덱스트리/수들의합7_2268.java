package 인덱스트리;

import java.io.*;
import java.util.*;

public class 수들의합7_2268 {
	static int N;
	static int M;
	static int S;
	static final int SUM = 0;
	static final int MODIFY = 1;
	static long[] tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = 1;
		while (S < N) S *= 2;
		tree = new long[S * 2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == SUM) {
				int queryLeft = Integer.parseInt(st.nextToken());
				int queryRight = Integer.parseInt(st.nextToken());
				if (queryLeft > queryRight) {
					int tmp = queryLeft;
					queryLeft = queryRight;
					queryRight = tmp;
				}
				long sum = query(1, 1, S, queryLeft, queryRight);
				answer.append(sum).append("\n");
			} else if (command == MODIFY) {
				int index = Integer.parseInt(st.nextToken());
				long newVal = Integer.parseInt(st.nextToken());
				long diff = newVal - tree[S + index - 1];
				update(index, diff);
			}
		}

		System.out.print(answer);
	}

	private static void update(int node, long diff) {
		int index = node + S - 1;
		while (index >= 1) {
			tree[index] += diff;
			index /= 2;
		}
	}

	private static long query(int node, int start, int end, int queryLeft, int queryRight) {
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
}
