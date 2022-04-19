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
public class 함께블록쌓기_18427 {
	static int N;
	static int M;
	static int H;
	static int[][] blocks;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][H + 1];
		blocks = new int[N + 1][M];

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			String[] array = s.split(" ");
			for (int j = 0; j < array.length; j++) {
				blocks[i][j] = Integer.parseInt(array[j]);
			}
		}

		for (int i = 0; i <= N; i++) {
			dp[i][0] = 1;
		}

		for (int p = 1; p <= N; p++) {
			for (int h = 1; h <= H; h++)
			{
				for (int i = 0; i < M; i++) {
					if (blocks[p][i] == 0) break;
					if (h - blocks[p][i] >= 0) {
						dp[p][h] += dp[p - 1][h - blocks[p][i]] % 10007;
					}
				}
				dp[p][h] += dp[p - 1][h] % 10007;
			}
		}

		System.out.print(dp[N][H] % 10007);
	}
}
