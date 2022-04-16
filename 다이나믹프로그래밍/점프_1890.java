package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
4
2 3 3 1
1 2 1 3
1 2 3 1
3 1 1 0

4
1 2 2 3
1 1 3 3
3 1 1 3
3 2 1 0

9
3 1 2 2 3 3 1 1 2
1 1 2 1 1 2 3 1 2
2 1 1 3 2 2 1 3 1
3 3 1 1 1 3 1 2 1
3 2 2 2 1 1 3 3 1
3 1 3 2 2 3 1 3 3
3 1 1 2 1 1 1 1 1
2 3 1 3 1 3 2 2 2
3 3 3 2 3 1 3 3 0

4
1 1 1 3
1 0 0 0
1 1 0 0
0 2 0 0

5
2 1 1 1 1
3 1 1 3 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 0
 */
public class 점프_1890 {
	static int N;
	static int[][] map;
	static long[][] dp;
	static long total = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		dp[0][0] = 0;
		dfs(N - 1, N - 1);

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(dp[i][j] + " ");
//				total += dp[i][j];
//			}
//			System.out.println();
//		}

		System.out.print(dp[N - 1][N - 1]);
	}

	private static long dfs(int y, int x) {
		long count = 0;

		if (y == 0 && x == 0) {
			return 1;
		}

		if (dp[y][x] != -1) {
			return dp[y][x];
		}

		for (int k = 1; k <= x; k++) {
			if (map[y][x - k] == k) {
				count += dfs(y, x - k);
			}
		}

		for (int k = 1; k <= y; k++) {
			if (map[y - k][x] == k) {
				count += dfs(y - k, x);
			}
		}

		dp[y][x] = count;

		return dp[y][x];
	}
}
