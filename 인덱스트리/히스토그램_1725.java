package 인덱스트리;

import java.io.*;
import java.util.*;
/*
7
2
1
4
5
1
3
3
 */
public class 히스토그램_1725 {
	static int N;
	static int S;
	static int[] height;
	static int[] tree; // 가장 낮은 높이의 인덱스

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		int h = (int) Math.ceil(Math.log10(N) / Math.log10(2));
		S = (int) Math.pow(2, h);
		height = new int[N + 1];
		tree = new int[S * 2];

		for (int i = 1; i <= N; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}

		init(1, 1, S);

		long answer = calcAreaSize(1, 1, N);

		System.out.println(answer);
	}

	private static int init(int node, int start, int end) {

		if (start > N) {
			return -1;
		}

		if (start == end) {
			tree[node] = start;
		} else {
			int mid = (start + end) / 2;
			int leftLowIdx = init(node * 2, start, mid);
			int rightLowIdx = init(node * 2 + 1, mid + 1, end);

			if (leftLowIdx == -1) {
				tree[node] = rightLowIdx;
			} else if (rightLowIdx == -1) {
				tree[node] = leftLowIdx;
			} else {
				tree[node] = height[leftLowIdx] < height[rightLowIdx] ? leftLowIdx : rightLowIdx;
			}
		}
		return tree[node];
	}

	private static long calcAreaSize(int node, int queryLeft, int queryRight) {
		if (queryLeft > queryRight) return -1;

		int index = query(1, 1, S, queryLeft, queryRight);
		long maxSize = (long) height[index] * (queryRight - queryLeft + 1);

		maxSize = Math.max(maxSize, calcAreaSize(node * 2, queryLeft, index - 1));
		maxSize = Math.max(maxSize, calcAreaSize(node * 2 + 1, index + 1, queryRight));

		return maxSize;
	}

	private static int query(int node, int start, int end, int queryLeft, int queryRight) {

		if (start > queryRight || end < queryLeft) {
			return -1;
		}

		if (queryLeft <= start && end <= queryRight) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int leftIndex = query(node * 2, start, mid, queryLeft, queryRight);
		int rightIndex = query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);

		if (leftIndex == -1) {
			return rightIndex;
		} else if (rightIndex == -1) {
			return leftIndex;
		} else {
			return height[leftIndex] < height[rightIndex] ? leftIndex : rightIndex;
		}
	}
}
