package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
4 7
6 13
4 8
3 6
5 12

3 5
4 2
3 4
1 5

4 8
6 13
4 8
4 6
5 12

4 2
1 1
5 1
5 1
1 1
 */
public class 평범한베낭_12865 {
	static int N, K;
	static int[][] V;
	static int[][] dp;
	static int weight = 0, value = 1;
	static int MAX;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		V = new int[N + 1][2];
		dp = new int[N + 1][K + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			V[i][weight] = w;
			V[i][value] = v;
		}

		for (int i = 1; i <= N; i++) {
			for (int w = 0; w <= K; w++) {
				if (w >= V[i][weight]) {
					dp[i][w] = Math.max(dp[i - 1][w - V[i][weight]] + V[i][value], dp[i][w]);
				}
				dp[i][w] = Math.max(dp[i - 1][w], dp[i][w]);
			}
			MAX = Math.max(MAX, dp[i][K]);
		}

		System.out.println(MAX);
	}
}
