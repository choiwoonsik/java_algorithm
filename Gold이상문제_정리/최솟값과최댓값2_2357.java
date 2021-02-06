package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 최솟값과최댓값2_2357 {
	static int N, M, height, last;
	static int[] minTree;
	static int[] maxTree;
	static int[] A;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder ret = new StringBuilder();
	public static void main(String[] args) throws IOException {
		start();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int min = findMin(1, 1, last, left, right);
			int max = findMax(1, 1, last, left, right);
			ret.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(ret);
	}

	private static void start() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N+1];
		minTree = new int[N*4];
		maxTree = new int[N*4];
		Arrays.fill(minTree, Integer.MAX_VALUE);
		height = (int) Math.ceil(Math.log10(N) / Math.log10(2));
		last = (int) Math.pow(2, height);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
		}
		minInit(1, 1, last);
		maxInit(1, 1, last);
	}

	private static int minInit(int index, int start, int end) {
		if (start > N) return Integer.MAX_VALUE;
		else if (start == end)
			minTree[index] = A[start];
		else {
			int mid = (start + end) / 2;
			minTree[index] = Math.min(minInit(index * 2, start, mid)
					, minInit(index * 2 + 1, mid+1, end));
		}
		return minTree[index];
	}

	private static int maxInit(int index, int start, int end) {
		if (start > N) return 0;
		else if (start == end)
			maxTree[index] = A[start];
		else {
			int mid = (start + end) / 2;
			maxTree[index] = Math.max(maxInit(index * 2, start, mid)
					, maxInit(index * 2 + 1, mid+1, end));
		}
		return maxTree[index];
	}


	private static int findMin(int index, int start, int end, int left, int right)
	{
		if (end < left || right < start) return Integer.MAX_VALUE;
		else if (left <= start && end <= right)
			return minTree[index];
		else {
			int mid = (start + end) / 2;
			return Math.min(findMin(index * 2, start, mid, left, right)
					, findMin(index * 2 + 1, mid+1, end, left, right));
		}
	}

	private static int findMax(int index, int start, int end, int left, int right)
	{
		if (end < left || right < start) return 0;
		else if (left <= start && end <= right)
			return maxTree[index];
		else {
			int mid = (start + end) / 2;
			return Math.max(findMax(index * 2, start, mid, left, right)
					, findMax(index * 2 + 1, mid+1, end, left, right));
		}
	}
}
