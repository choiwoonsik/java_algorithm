package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
10
10 -4 3 1 5 6 -35 12 21 -1
 */
public class 연속합_1912 {
	static int N;
	static int[] nums;
	static int[] dp;
	static int MAX;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		dp[0] = nums[0];
		MAX = dp[0];
		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
			MAX = Math.max(dp[i], MAX);
		}
		System.out.println(MAX);
	}
}
