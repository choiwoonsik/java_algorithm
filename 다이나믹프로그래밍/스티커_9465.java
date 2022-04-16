package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
2
5
50 10 100 20 40
30 50 70 10 60
7
10 30 10 50 100 20 40
20 40 30 50 60 20 80
 */
public class 스티커_9465 {
	static int N;
	static int T;
	static int[][] map;
	static int[][][] dp;
	static int YES = 0;
	static int NO = 1;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());

			map = new int[2][N];
			dp = new int[2][N][2];

			for (int r = 0; r < 2; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					dp[r][c][YES] = -1;
					dp[r][c][NO] = -1;
				}
			}

			int ret = Math.max(Math.max(dfs(0, 0, YES), dfs(0, 0, NO)), dfs(1, 0, YES));
			answer.append(ret).append("\n");
		}

		System.out.print(answer);
	}

	private static int dfs(int y, int x, int selected) {
		int count;

		if (x >= N) return 0;

		if (dp[y][x][selected] != -1) return dp[y][x][selected];

		int ny = y == 1 ? 0 : 1;
		if (selected == YES) {
			count = map[y][x];
			count += Math.max(dfs(ny, x + 1, YES), dfs(ny, x + 1, NO));
		}
		else {
			count = 0;
			count += Math.max(dfs(y, x + 1, YES), dfs(ny, x + 1, YES));
		}

		dp[y][x][selected] = count;

		return dp[y][x][selected];
	}
}
