package SDS_알고리즘.Day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LCS2_9252 {
	static int[][] DP;
	static int[][] DIR;
	static int UP = 0;
	static int LEFT = 1;
	static int CROSS = 2;
	static StringBuilder answer = new StringBuilder();
	static int LEN;
	static String origin;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		String s0 = br.readLine();
		String s1 = br.readLine();
		origin = s0;

		int N = s0.length();
		int M = s1.length();
		DP = new int[N + 1][M + 1];
		DIR = new int[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			char c0 = s0.charAt(i - 1);
			for (int j = 1; j < M + 1; j++) {
				char c1 = s1.charAt(j - 1);

				if (c0 == c1) {
					DP[i][j] = Math.max(DP[i][j], DP[i - 1][j - 1] + 1);
					DIR[i][j] = CROSS;
				} else {
					if (DP[i][j - 1] > DP[i - 1][j]) {
						DP[i][j] = DP[i][j - 1];
						DIR[i][j] = LEFT;
					} else {
						DP[i][j] = DP[i - 1][j];
						DIR[i][j] = UP;
					}
				}
			}
		}

		LEN = DP[N][M];
		trace(N, M);
		System.out.println(LEN);
		System.out.print(answer.reverse());
	}

	private static void trace(int y, int x) {
		if (y <= 0 || x <= 0) return;

		if (DIR[y][x] == CROSS) {
			answer.append(origin.charAt(y - 1));
			trace(y - 1, x - 1);
		} else if (DIR[y][x] == LEFT) {
			trace(y, x - 1);
		} else if (DIR[y][x] == UP){
			trace(y - 1, x);
		}
	}
}
