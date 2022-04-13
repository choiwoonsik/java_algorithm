package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
11339 - 105*105 - 17*17 - 5*5
 */
public class FourSquares_17626 {
	static int N;
	static int dp[];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		dp[1] = 1;

		for (int num = 2; num <= N; num++) {
			dp[num] = dp[num - 1] + 1;
			for (int i = 1; i * i <= num; i++) {
				dp[num] = Math.min(dp[num], dp[num - (i * i)]);
			}
			dp[num] += 1;
		}

		System.out.print(dp[N]);
	}
}
