package 인덱스트리;

import java.util.*;
import java.io.*;

/*
6
2 1 2
2 3 3
1 2
1 2
2 1 -1
1 2
 */

public class 사탕상자2_2243 {
	static int N;
	static int S;
	static int[] tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		S = 1;
		while (S < 1000000) S *= 2;
		tree = new int[S * 2];

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());

			// poll
			if (type == 1) {
				int order = Integer.parseInt(st.nextToken());
				int candyTaste = query(1, 1, S, order);
				update(candyTaste, -1);
				answer.append(candyTaste).append("\n");
			} else if (type == 2) {
				int candyTaste = Integer.parseInt(st.nextToken());
				int diff = Integer.parseInt(st.nextToken());
				update(candyTaste, diff);
			}
		}

		System.out.print(answer);
	}

	private static void update(int candyTaste, int diff) {
		int idx = candyTaste + S - 1;

		tree[idx] += diff;

		while (idx > 1) {
			idx /= 2;
			tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
		}
	}

	private static int query(int node, int start, int end, int order) {

		if (start == end) {
			return start;
		}

		int mid = (start + end) / 2;

		// left
		if (order <= tree[node * 2]) {
			return query(node * 2, start, mid, order);
		} else {
			return query(node * 2 + 1, mid + 1, end, order - tree[node * 2]);
		}
	}
}
