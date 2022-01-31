package SDS_알고리즘.Day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공통부분문자열_5582 {
	static int[][] dp;
	static int max;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		String s0 = br.readLine();
		String s1 = br.readLine();

		int N = s0.length();
		int M = s1.length();
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			char c0 = s0.charAt(i - 1);

			for (int j = 1; j <= M; j++) {
				char c1 = s1.charAt(j - 1);
				if (c0 == c1) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}

		System.out.println(max);
	}
}
