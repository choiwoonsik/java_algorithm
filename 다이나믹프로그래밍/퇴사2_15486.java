package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
7
3 10
5 20
1 10
1 20
2 15
4 40
2 200

3
4 1000
2 10
1 30
 */
public class 퇴사2_15486 {
	static int N;
	static int[] dp;
	static int[][] table;
	static int YES = 1;
	static int NO = 0;
	static int DAY = 0;
	static int COST = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		table = new int[N + 1][2];
		dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			table[i][DAY] = day;
			table[i][COST] = cost;
		}

		int ret = dfs();
		System.out.print(ret);
	}

	private static int dfs() {
		int max = 0;
		for (int today = 0; today <= N; today++) {

			if (dp[today] > max) {
				max = dp[today];
			}

			int nextDay = today + table[today][DAY];
			if (nextDay <= N) {
				dp[nextDay] = Math.max(dp[nextDay], max + table[today][COST]);
			}
		}
		return max;
	}
}
