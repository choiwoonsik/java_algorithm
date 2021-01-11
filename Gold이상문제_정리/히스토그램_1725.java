package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 히스토그램_1725 {
	static int N;
	static int[] A;
	static int[] tree;
	static int last;
	public static void main(String[] args) throws IOException {
		/*
		직사각형의 높이들이 주어진다 해당 높이들을 배열에 담는다
		1. 세그먼트 트리로 구간의 최소 높이를 찾는다
		2. 그 높이를 기준으로 좌, 우로 나눠서 넓이를 구한다
		3. 다시 해당 좌, 우에서 1번을 반복한다
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N+1];
		tree = new int[4*N];

		int height = (int) Math.ceil(Math.log10(N) / Math.log10(2));
		last = (int) Math.pow(2, height);

		for (int i = 1; i <= N; i++) {
			int h = Integer.parseInt(br.readLine());
			A[i] = h;
		}

		init(1, 1, last);
		System.out.println(findMaxArea(1, 1, N));
	}

	private static int init(int index, int start, int end) {
		if (start > N)
			return -1;
		if (start == end)
			tree[index] = start;
		else {
			int mid = (start + end) / 2;
			int leftIndex = init(index * 2, start, mid);
			int rightIndex = init(index * 2 + 1, mid+1, end);
			if (leftIndex == -1)
				tree[index] = rightIndex;
			else if (rightIndex == -1)
				tree[index] = leftIndex;
			else
				tree[index] = A[leftIndex] < A[rightIndex] ? leftIndex : rightIndex;
		}
		return tree[index];
	}

	private static int findMinIndex(int index, int start, int end, int left, int right) {
		if (left > end || right < start)
			return -1;
		if (left <= start && end <= right)
			return tree[index];

		int mid = (start + end) / 2;
		int leftIndex = findMinIndex(index*2, start, mid, left, right);
		int rightIndex = findMinIndex(index*2+1, mid+1, end, left, right);
		if (leftIndex == -1)
			return rightIndex;
		else if (rightIndex == -1)
			return leftIndex;
		else return A[leftIndex] < A[rightIndex] ? leftIndex : rightIndex;
	}

	private static int findMaxArea(int index, int start, int end) {
		int minIdx = findMinIndex(1, 1, last, start, end);
		int area = (end - start + 1) * A[minIdx];

		if (minIdx - 1 >= start) {
			area = Math.max(area, findMaxArea(index*2, start, minIdx-1));
		}
		if (minIdx + 1 <= end) {
			area = Math.max(area, findMaxArea(index*2+1, minIdx+1, end));
		}
		return area;
	}
}
