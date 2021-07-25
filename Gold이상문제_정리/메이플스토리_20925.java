package Gold이상문제_정리;

/*
2 10
0 10
50 100
0 2
2 0
 */

import java.util.*;
import java.io.*;

public class 메이플스토리_20925 {
	static int N, T;
	static int[][] moveTime;
	static int[] E;
	static int[] GET;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		T = Integer.parseInt(input[1]);

		moveTime = new int[N][N];
		E = new int[N];
		GET = new int[N];
		dp = new int[T+1][N];

		for (int t = 0; t < T; t++)
			Arrays.fill(dp[t], -1);

		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			E[i] = Integer.parseInt(input[0]);
			GET[i] = Integer.parseInt(input[1]);
			if (E[i] == 0) dp[0][i] = 0;
		}
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				moveTime[i][j] = Integer.parseInt(input[j]);
			}
		}

		for (int t = 1; t <= T; t++) {
			for (int i = 0; i < N; i++) {
				// 1) i 사냥터에서 사냥을 이어서 하는 경우
				// 		: (1분전 i 사냥터에서의 누적경험치 + 얻는 1분 경험치) vs dp[t][i]
				if (dp[t - 1][i] != -1)
					dp[t][i] = Math.max(dp[t][i], dp[t - 1][i] + GET[i]);

				// 2) j 사턍터에서 moveTime[j][i]를 소모하고 i사냥터로 이동하는 경우
				// 		: moveTime[j][i]분 전 j사냥터에서 누적 경험치 vs dp[t][i];
				for (int j = 0; j < N; j++) {
					int prevTime = t - moveTime[j][i];
					if (i == j) continue;
					if (prevTime < 0) continue;
					if (dp[prevTime][j] != -1 && dp[prevTime][j] >= E[i]) {
						dp[t][i] = Math.max(dp[t][i], dp[prevTime][j]);
					}
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, dp[T][i]);
		System.out.println(max);
	}
}
