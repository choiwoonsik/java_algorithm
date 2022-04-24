package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
3 3 5
2 3 5
3 5
1 2 3
 */
public class 함께블록쌓기2_18427 {
	static int N;
	static int M;
	static int H;
	static int[][] table;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		table = new int[N + 1][M + 1];
		dp = new int[H + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			int j = 1;
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				table[i][j++] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N + 1; i++) {
			dp[0][i] = 1;
		}

		for (int h = 1; h < H + 1; h++) {
			for (int person = 1; person < N + 1; person++) {
				for (int blockIdx = 1; blockIdx < M + 1; blockIdx++) {
					int block = table[person][blockIdx];

					if (block > 0 && h - block >= 0) {
						dp[h][person] += dp[h - block][person - 1] % 10007;
					}
				}
				dp[h][person] += dp[h][person - 1] % 10007;
			}
		}

		System.out.println(dp[H][N] % 10007);
	}
}
