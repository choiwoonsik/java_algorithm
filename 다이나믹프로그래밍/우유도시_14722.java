package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
4
0 1 2 2
1 1 1 1
2 2 2 2
0 0 1 0
 */
public class 우유도시_14722 {
	static int N;
	static int[][] milk;
	static int[][][] dp;
	static int S = 0;
	static int C = 1;
	static int B = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		milk = new int[N][N];
		dp = new int[3][N][N];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				milk[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, S);

		System.out.print(Math.max(Math.max(dp[S][0][0], dp[C][0][0]), dp[B][0][0]));
	}

	private static int dfs(int y, int x, int now) {
		if (y >= N || x >= N) return 0;

		if (dp[now][y][x] != -1) return dp[now][y][x];

		if (now == milk[y][x]) {
			int next = next(now);
			dp[now][y][x] =
					Math.max(
							Math.max(dfs(y + 1, x, now), dfs(y, x + 1, now)),
							Math.max(dfs(y + 1, x, next) + 1, dfs(y, x + 1, next) + 1)
					);
		} else {
			dp[now][y][x] = Math.max(dfs(y + 1, x, now), dfs(y, x + 1, now));
		}

		return dp[now][y][x];
	}

	private static int next(int now) {
		return now == 2 ? 0 : now + 1;
	}
}
