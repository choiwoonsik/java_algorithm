package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
20 2
 */
public class 합분해_2225 {
	static int N;
	static int K;
	static int[] coins;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coins = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			coins[i] = i;
		}
		dp = new int[K + 1][N + 1];

		for (int i = 0; i < N + 1; i++) {
			dp[1][i] = 1;
		}

		/*
		k = 2
		0 + 20
		1 + 19
		...
		20 + 0

		k = 3
		0 + 0 + 20
		0 + 1 + 19
		...
		19 + 1 + 0
		20 + 0 + 0
		 */
		for (int k = 1; k <= K; k++) {
			for (int money = 0; money <= N; money++) {
				for (int coin : coins) {
					if (money - coin >= 0) {
						dp[k][money] += dp[k - 1][money - coin] % 1000000000;
						dp[k][money] %= 1000000000;
					}
				}
			}
		}

		System.out.println(dp[K][N] % 1000000000);
	}
}
