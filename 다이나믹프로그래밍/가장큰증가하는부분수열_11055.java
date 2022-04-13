package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
10
1 100 2 50 60 3 5 6 7 8

15
1 100 2 50 60 3 5 6 7 8 9 15 20 25 35
 */
public class 가장큰증가하는부분수열_11055 {
	static int N;
	static int[] nums;
	static int[] dp;
	static int[] parent;
	static int MAX;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		parent = new int[N];
		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int cur = 0; cur < N; cur++) {
			for (int before = 0; before < cur; before++) {
				if (nums[cur] > nums[before]) {
					dp[cur] = Math.max(dp[cur], dp[before]);
				}
			}
			dp[cur] += nums[cur];
			MAX = Math.max(dp[cur], MAX);
		}

		System.out.println(MAX);
	}
}
