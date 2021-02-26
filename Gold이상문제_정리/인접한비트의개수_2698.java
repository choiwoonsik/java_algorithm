package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 인접한비트의개수_2698 {
	static int[][][] DP;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder ret = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		int N, K;

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			DP = new int[N+1][K+1][2];
			DP[1][0][0] = 1; // 0인 경우 1개
			DP[1][0][1] = 1; // 1인 경우 1개
			for (int n = 2; n <= N; n++) {
				for (int k = 0; k <= K; k++) {
					if (k == 0)
						DP[n][k][1] = DP[n - 1][k][0];
					else
						DP[n][k][1] = DP[n - 1][k][0] + DP[n - 1][k - 1][1];
					DP[n][k][0] = DP[n - 1][k][0] + DP[n - 1][k][1];
				}
			}
			int ans = DP[N][K][0] + DP[N][K][1];
			ret.append(ans).append("\n");
		}
		System.out.print(ret);
	}
}
