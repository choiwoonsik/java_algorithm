package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
20
3
5 3
10 2
1 5
 */
public class 동전바꿔주기_2624 {
	static int N;
	static int M;
	static int[][] info;
	static int[][] dp;
	static int COST = 0;
	static int COUNT = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dp = new int[N + 1][M + 1];
		info = new int[M + 1][2];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			info[i][COST] = cost;
			info[i][COUNT] = count;
		}

		for (int i = 0; i < M + 1; i++) {
			dp[0][i] = 1;
		}

		for (int money = 1; money < N + 1; money++) {
			for (int type = 1; type < M + 1; type++) {

				int coin = info[type][COST];
				int count = info[type][COUNT];

				for (int c = 0; c <= count; c++) {
					if (money - coin * c >= 0) {
						dp[money][type] += dp[money - (coin * c)][type - 1];
					}
				}
			}

		}

		System.out.print(dp[N][M]);
	}
}
