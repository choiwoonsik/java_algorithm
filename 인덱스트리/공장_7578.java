package 인덱스트리;

import java.io.*;
import java.util.*;

/*
5
132 392 311 351 231
392 351 132 311 231
*/

public class 공장_7578 {
	static int[] upFactory;
	static HashMap<Integer, Integer> downFactory;
	static int[] tree;
	static int N;
	static int S;
	static long total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = 1;
		while (S < N) S *= 2;
		tree = new int[2 * S];
		upFactory = new int[N + 1];
		downFactory = new HashMap<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			upFactory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int factory = Integer.parseInt(st.nextToken());
			downFactory.put(factory, i);
		}

		for (int i = 1; i <= N; i++) {
			int upIdx = downFactory.get(upFactory[i]);
			int rightCount = query(1, 1, S, upIdx, N);
			total += rightCount;
			update(upIdx, 1);
		}

		System.out.println(total);
	}

	private static void update(int upIdx, int diff) {
		int n = S + upIdx - 1;

		tree[n] += diff;

		while (n > 1) {
			n /= 2;
			tree[n] = tree[n * 2] + tree[n * 2 + 1];
		}
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {
		if (start > queryRight || end < queryLeft)
			return 0;

		if (queryLeft <= start && end <= queryRight) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		return tree[node] = query(node * 2, start, mid, queryLeft, queryRight) +
			query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);
	}
}
