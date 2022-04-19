package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
ACAYKP
CAPCAK

AABBCCDD
ABC
 */
public class LCS_9251 {
	static String s1;
	static String s2;
	static char[] chars1;
	static char[] chars2;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		s1 = br.readLine();
		s2 = br.readLine();
		int W = s1.length();
		int H = s2.length();
		dp = new int[H + 1][W + 1];

		chars1 = new char[W + 1];
		chars2 = new char[H + 1];

		for (int i = 0; i < W; i++) {
			chars1[i + 1] = s1.charAt(i);
		}

		for (int i = 0; i < H; i++) {
			chars2[i + 1] = s2.charAt(i);
		}

		for (int row = 1; row < H + 1; row++) {
			for (int col = 1; col < W + 1; col++) {
				char c1 = chars1[col];
				char c2 = chars2[row];

				if (c1 == c2) {
					dp[row][col] = dp[row - 1][col - 1] + 1;
				} else {
					dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
				}
			}
		}

		System.out.print(dp[H][W]);
	}
}
