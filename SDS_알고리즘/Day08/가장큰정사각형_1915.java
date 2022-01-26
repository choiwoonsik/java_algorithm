package SDS_알고리즘.Day08;

import java.io.*;
import java.util.*;

public class 가장큰정사각형_1915 {
	static int N, M;
	static int Max;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dp = new int[N + 2][M + 2];

		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				int tmp = line.charAt(j - 1) - '0';
				dp[i][j] = tmp;
				if (tmp == 1) Max = 1;
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {

				if (dp[i][j] == 0) continue;

				int adjMin = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]);

				if (adjMin >= 1) {
					dp[i][j] = adjMin + 1;
					Max = Math.max(dp[i][j], Max);
				}
			}
		}

		System.out.println(Max * Max);
	}
}
