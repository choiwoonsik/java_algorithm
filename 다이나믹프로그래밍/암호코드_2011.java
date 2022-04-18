package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 암호코드_2011 {
	static String s;
	static HashMap<Integer, Character> alphabet = new HashMap<>();
	static int[] dp;
	static int N;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		s = br.readLine();
		N = s.length();

		numbers = new int[N + 1];
		for (int i = 0; i < N; i++) {
			numbers[i + 1] = s.charAt(i) - '0';
		}

		dp = new int[N + 1];
		dp[0] = 1;

		dfs();
		System.out.print(dp[N] % 1000000);
	}

	private static void dfs() {

		for (int i = 1; i <= N; i++) {

			if (numbers[i] != 0) {
				dp[i] = (dp[i] + dp[i - 1]) % 1000000;
			}

			int x = numbers[i] + numbers[i - 1] * 10;

			if (x >= 10 && x <= 26) {
				dp[i] = (dp[i] + dp[i - 2]) % 1000000;
			}
		}
	}
}
