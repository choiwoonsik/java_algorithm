package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
3
2
1 2
1000
3
1 5 10
100
2
5 7
22
 */
public class 동전_9084 {
	static int T;
	static int N;
	static int K;
	static int[] coins;
	static int[] dp;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			coins = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			K = Integer.parseInt(br.readLine());
			dp = new int[K + 1];
			dp[0] = 1;

			for (int coin : coins) {
				for (int i = 0; i <= K; i++) {
					if (i >= coin) {
						dp[i] += dp[i - coin];
					}
				}
			}

			answer.append(dp[K]).append("\n");
		}

		System.out.print(answer);
	}
}
