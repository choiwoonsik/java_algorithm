package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

public class 쉬운계단수_10844 {
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];

		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int num = 0; num < 10; num++) {
				if (num == 0) {
					dp[i][num] = dp[i - 1][1] % 1000000000;
				}
				else if (num == 9) {
					dp[i][num] = dp[i - 1][8] % 1000000000;
				}
				else {
					dp[i][num] = (dp[i - 1][num - 1] + dp[i - 1][num + 1]) % 1000000000;
				}
			}
		}

		System.out.print(Arrays.stream(dp[N]).sum() % 1000000000);
	}
}
