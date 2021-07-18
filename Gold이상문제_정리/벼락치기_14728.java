package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
3 310
50 40
100 70
200 150
 */

public class 벼락치기_14728 {
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		dp = new int[T+1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int hour = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());

			for (int time = T; time >= hour; time--) {
				dp[time] = Math.max(dp[time], dp[time - hour] + point);
			}
		}
		System.out.println(dp[T]);
	}
}
