package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
4 3
1 2 3 4
2 3 4 5
3 4 5 6
4 5 6 7
2 2 3 4
3 4 3 4
1 1 4 4
 */
public class 구간합구하기5_11660 {
	static int N;
	static int K;
	static int[][] map;
	static int[][] dp;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][1] = map[1][1];
		for (int r = 2; r <= N; r++) {
			dp[r][1] = dp[r - 1][1] + map[r][1];
		}
		for (int c = 1; c <= N; c++) {
			dp[1][c] = dp[1][c - 1] + map[1][c];
		}
		for (int r = 2; r <= N; r++) {
			for (int c = 2; c <= N; c++) {
				dp[r][c] = dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1] + map[r][c];
			}
		}

		for (int q = 0; q < K; q++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			int part = dp[r2][c2] - dp[r2][c1 - 1] - dp[r1 - 1][c2] + dp[r1 - 1][c1 - 1];
			answer.append(part).append("\n");
		}

		System.out.print(answer);
	}
}
