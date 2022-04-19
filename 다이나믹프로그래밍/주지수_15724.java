package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
4 4
9 14 29 7
1 31 6 13
21 26 40 16
8 38 11 23
3
1 1 3 2
1 1 1 4
1 1 4 4

2 3
10 9 8
1 2 3
3
1 1 2 2
1 1 2 3
2 2 2 3
 */
public class 주지수_15724 {
	static int N;
	static int M;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		dp = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 1; r <= N; r++) {
			dp[r][1] = dp[r - 1][1] + map[r][1];
		}
		for (int c = 1; c <= M; c++) {
			dp[1][c] = dp[1][c - 1] + map[1][c];
		}
		for (int i = 2; i <= N; i++) {
			for (int j = 2; j <= M; j++) {
				dp[i][j] = map[i][j] + dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1];
			}
		}

		StringBuilder answer = new StringBuilder();

		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());

			int value = dp[r2][c2] - dp[r2][c1 - 1] - dp[r1 - 1][c2] + dp[r1 - 1][c1 - 1];
			answer.append(value).append("\n");
		}

		System.out.print(answer);
	}
}
