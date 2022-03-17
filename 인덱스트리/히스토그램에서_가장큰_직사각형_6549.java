package 인덱스트리;

import java.util.*;
import java.io.*;

/*
7 2 1 4 5 1 3 3
4 1000 1000 1000 1000
0
 */

public class 히스토그램에서_가장큰_직사각형_6549 {
	static int N;
	static int S;
	static int[] H;
	static int[] tree;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			if (N == 0) break;

			S = 1;
			while (S < N) S *= 2;
			tree = new int[S * 2];
			H = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}

			init(1, 1, S);

			answer.append(calcMaxSize(1, 1, N)).append("\n");
		}
		System.out.print(answer);
	}

	private static int init(int node, int start, int end) {
		if (start > N) {
			return -1;
		}

		if (start == end) {
			tree[node] = start;
		} else {
			int mid = (start + end) / 2;
			int leftLowIndex = init(node * 2, start, mid);
			int rightLowIndex = init(node * 2 + 1, mid + 1, end);

			if (leftLowIndex == -1) {
				tree[node] = rightLowIndex;
			} else if (rightLowIndex == -1) {
				tree[node] = leftLowIndex;
			} else {
				tree[node] = H[leftLowIndex] < H[rightLowIndex] ? leftLowIndex : rightLowIndex;
			}
		}
		return tree[node];
	}

	private static long calcMaxSize(int node, int queryLeft, int queryRight) {

		int minLowIndex = query(1, 1, S, queryLeft, queryRight);
		long maxArea = (long) H[minLowIndex] * (queryRight - queryLeft + 1);

		if (queryLeft <= minLowIndex - 1) {
			maxArea = Math.max(maxArea, calcMaxSize(node * 2, queryLeft, minLowIndex - 1));
		}
		if (minLowIndex + 1 <= queryRight) {
			maxArea = Math.max(maxArea, calcMaxSize(node * 2 + 1, minLowIndex + 1, queryRight));
		}

		return maxArea;
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {

		if (queryLeft > end || queryRight < start)
			return -1;

		if (queryLeft <= start && end <= queryRight) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int leftMinHeightIdx = query(node * 2, start, mid, queryLeft, queryRight);
		int rightMinHeightIdx = query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);

		if (leftMinHeightIdx == -1) {
			return rightMinHeightIdx;
		} else if (rightMinHeightIdx == -1) {
			return leftMinHeightIdx;
		} else {
			return H[leftMinHeightIdx] < H[rightMinHeightIdx] ? leftMinHeightIdx : rightMinHeightIdx;
		}
	}
}
