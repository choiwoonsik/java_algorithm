package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 평범한베낭_12865 {
	static int N, K;
	static int[][] DP;
	static int[][] stock;
	static int Max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		DP = new int[N+1][K+1];
		stock = new int[N+1][2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			stock[i] = new int[]{w, v};
		}
		find();
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				ret = Math.max(ret, DP[i][j]);
			}
		}
		System.out.println(ret);
	}

	private static void find() {

		for (int i = 1; i <= N; i++) {
			int W = stock[i][0];
			int V = stock[i][1];

			for (int weight = 1; weight <= K; weight++) {
				// 물건빼고 다시 넣기 vs 그냥
				if (W <= weight)
					DP[i][weight] = Math.max(DP[i-1][weight - W] + V,  DP[i-1][weight]);
				// 못넣음
				else
					DP[i][weight] = DP[i-1][weight];
			}
		}
	}
}
