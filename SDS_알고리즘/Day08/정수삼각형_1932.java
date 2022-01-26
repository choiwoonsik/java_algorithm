package SDS_알고리즘.Day08;

import java.io.*;
import java.util.*;

public class 정수삼각형_1932 {
	static int N;
	static int[][] number;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		number = new int[N + 2][N + 2];
		dp = new int[N + 2][N + 2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < i + 1; j++) {
				number[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dynamic();

		int MAX = 0;
		for (int i = 1; i < N + 1; i++) {
			MAX = Math.max(MAX, dp[N][i]);
		}
		System.out.println(MAX);
	}

	private static void dynamic() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < i + 1; j++) {
				dp[i][j] = number[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
			}
		}
	}
}
