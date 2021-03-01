package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 사전_1256 {
	static int[][] DP;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		DP = new int[N+1][M+1];

		for (int i = 1; i <= N; i++)
			DP[i][0] = 1;
		for (int i = 1; i <= M; i++)
			DP[0][i] = 1;
		DP[0][0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				DP[i][j] = DP[i][j - 1] + DP[i - 1][j];
				if (DP[i][j] >= 1000000001) DP[i][j] = 1000000001;
			}
		}

		if (DP[N][M] < K)
			System.out.println(-1);
		else {
			makeAZ(N, M, K);
			System.out.println(str);
		}
	}

	private static void makeAZ(int N, int M, int K) {
		if (N == 0) {
			str.append("z".repeat(M));
			return;
		} else if (M == 0) {
			str.append("a".repeat(N));
			return;
		}

		if (DP[N - 1][M] < K) { // 앞이 Z인 경우
			str.append("z");
			makeAZ(N, M - 1, K - DP[N-1][M]);
		}
		else{
			str.append("a");
			makeAZ(N - 1, M , K);
		}
	}
}
