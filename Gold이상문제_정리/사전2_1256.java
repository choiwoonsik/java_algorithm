package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
2 2 2
 */
public class 사전2_1256 {
	static int N, M, K;
	static int[][] dp;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N+1][M+1];
		for (int a = 1; a <= N; a++)
			dp[a][0] = 1;
		for (int z = 1; z <= M; z++)
			dp[0][z] = 1;
		dp[0][0] = 0;

		for (int a = 1; a < N + 1; a++) {
			for (int z = 1; z < M + 1; z++) {
				dp[a][z] = dp[a - 1][z] + dp[a][z - 1];
				if (dp[a][z] > 1000000000) dp[a][z] = 1000000001;
			}
		}
		if (dp[N][M] < K) System.out.println(-1);
		else {
			makeAZ(N, M, K);
			System.out.println(str);
		}
	}

	private static void makeAZ(int n, int m, int k) {
		if (n == 0)
			str.append("z".repeat(m));
		else if (m == 0)
			str.append("a".repeat(n));

		else {
			if (k <= dp[n - 1][m]) {
				str.append("a");
				makeAZ(n - 1, m, k);
			}
			else {
				str.append("z");
				makeAZ(n, m - 1, k - dp[n - 1][m]);
			}
		}
	}
}
