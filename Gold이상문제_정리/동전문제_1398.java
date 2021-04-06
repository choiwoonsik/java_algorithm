package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 동전문제_1398 {
	static int T;
	static long price;
	static int[] dp = new int[101];
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		make_dp();

		for (int i = 0; i < T; i++) {
			price = Long.parseLong(br.readLine());
			solve();
		}
		System.out.print(str);
	}

	private static void make_dp() {
		for (int i = 1; i < 101; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i - 10 >= 0) dp[i] = Math.min(dp[i], dp[i - 10] + 1);
			if (i - 25 >= 0) dp[i] = Math.min(dp[i], dp[i - 25] + 1);
		}
	}

	private static void solve() {
		int cnt = 0;
		while (price > 0) {
			cnt += dp[(int) (price % 100)];
			price /= 100;
		}
		str.append(cnt).append("\n");
	}
}
