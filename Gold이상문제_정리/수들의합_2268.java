package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
3 5
0 1 3
1 1 2
1 2 3
0 2 3
0 1 3

0 : sum
1 : modify (A[i] = k)
 */
public class 수들의합_2268 {
	static int N,M, height, last;
	static int[] nums;
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		height = (int) Math.ceil(log(N, 2));
		last = (int) Math.pow(2, height);

		nums = new int[N+1];
		tree = new int[4 * N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			// sum
			if (type == 0)
			{
				int tmp;
				if (r < l) {
					tmp = r;
					r = l;
					l = tmp;
				}
				int sum = sum(1, 1, last, l, r);
				answer.append(sum).append("\n");
			}
			// modify
			else if (type == 1) {
				modify(1,1, last, l, r - nums[l]);
			}
		}
		System.out.print(answer);
	}

	private static double log(int n, int base) {
		return (Math.log10(n) / Math.log10(base));
	}

	private static void modify(int index, int start, int end, int changed_idx, int diff) {
		if (changed_idx < start || changed_idx > end) return;

		tree[index] += diff;

		if (start != end) {
			int mid = (start + end) / 2;
			modify(index * 2, start, mid, changed_idx, diff);
			modify(index*2 + 1, mid+1, end, changed_idx, diff);
		}
	}

	private static int sum(int index, int start, int end, int left, int right) {
		if (start > right || end < left) return 0;
		if (left <= start && end <= right) return tree[index];
		else {
			int mid = (start + end) / 2;
			return sum(index*2, start, mid, left, right)
				+ sum(index*2 + 1, mid + 1, end, left, right);
		}
	}
}
