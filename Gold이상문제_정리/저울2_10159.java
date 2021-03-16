package Gold이상문제_정리;

/*
6
5
1 2
2 3
3 4
5 4
6 5
 */

import java.io.*;
import java.util.*;

public class 저울2_10159 {
	static int N, M;
	static int INF = 100000;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dp = new int[N+1][N+1];
		for (int i = 0; i < N+1; i++) {
			Arrays.fill(dp[i], INF);
			dp[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());

			dp[one][two] = 1;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (dp[i][j] > dp[i][k] + dp[k][j])
						dp[i][j] = dp[i][k] + dp[k][j];
				}
			}
		}

		StringBuilder str = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			int count = 0;
			for (int j = 1; j < N + 1; j++) {
				if (dp[i][j] == INF && dp[j][i] == INF){
					count++;
				}
			}
			str.append(count).append("\n");
		}
		System.out.print(str);
	}
}
