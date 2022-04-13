package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

public class 이xN타일링_11726 {
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];

		dp[1] = 1;
		if (N >= 2) dp[2] = 2;
		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}

		System.out.print(dp[N]);
	}
}
