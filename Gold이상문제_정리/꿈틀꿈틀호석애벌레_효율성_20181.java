package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 꿈틀꿈틀호석애벌레_효율성_20181 {
	static int N, K;
	static int[] bugs;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bugs = new int[N+1];
		dp = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			bugs[i] = Integer.parseInt(st.nextToken());
		}

		go();
		System.out.println(dp[N]);
	}

	private static void go() {
		int L = 0;
		int R = 1;
		int eat = 0;
		while (R <= N)
		{
			eat += bugs[R];
			while (eat >= K) {
				dp[R] = Math.max(dp[R-1], Math.max(dp[R], dp[L] + eat - K));
				eat -= bugs[++L];
			}
			R++;
		}
	}
}
