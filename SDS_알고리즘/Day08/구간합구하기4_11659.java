package SDS_알고리즘.Day08;
/*
5 3
5 4 3 2 1
1 3
2 4
5 5
 */

import java.util.*;
import java.io.*;

public class 구간합구하기4_11659 {
	static int N;
	static int K;
	static int[] nums;
	static int[] cummulativeSum;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		cummulativeSum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			nums[i] = n;
		}

		cummulativeSum[1] = nums[1];
		for (int i = 2; i <= N; i++) {
			cummulativeSum[i] = cummulativeSum[i - 1] + nums[i];
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			answer.append(cummulativeSum[v] - cummulativeSum[u] + nums[u]).append("\n");
		}
		System.out.print(answer);
	}
}
