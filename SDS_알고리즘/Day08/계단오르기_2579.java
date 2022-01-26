package SDS_알고리즘.Day08;

import java.util.*;
import java.io.*;

public class 계단오르기_2579 {
	static int N;
	static int[] stair;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		stair = new int[N + 2];
		dp = new int[N + 2];

		for (int i = 1; i < N + 1; i++) {
			int point = Integer.parseInt(br.readLine());
			stair[i] = point;
		}

		dp[0] = 0;
		dp[1] = stair[1];
		dp[2] = stair[1] + stair[2];
		for (int s = 3; s < N + 1; s++) {
			dp[s] = Math.max(dp[s - 3] + stair[s - 1] + stair[s], dp[s - 2] + stair[s]);
		}

		System.out.println(dp[N]);
	}
}
