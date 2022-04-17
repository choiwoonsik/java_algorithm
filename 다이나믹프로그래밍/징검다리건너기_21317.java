package 다이나믹프로그래밍;


import java.io.*;
import java.util.*;

/*
5
1 2
2 3
4 5
6 7
4
 */
public class 징검다리건너기_21317 {
	static int N;
	static int K;
	static int[][] jumpCost;
	static int SMALL = 0;
	static int BIG = 1;
	static int BIGGEST;
	static int[] dp;
	static int YES = 1;
	static int NO = 0;
	static int MIN = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		jumpCost = new int[N + 1][2];
		dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sj = Integer.parseInt(st.nextToken());
			int bj = Integer.parseInt(st.nextToken());
			jumpCost[i][SMALL] = sj;
			jumpCost[i][BIG] = bj;
		}
		BIGGEST = Integer.parseInt(br.readLine());

		dfs(1, NO, 0);

		System.out.println(MIN);
	}

	private static void dfs(int cur, int USED, int value) {
		if (cur >= N) {
			MIN = Math.min(value, MIN);
			return;
		}

		if (value >= MIN) {
			return;
		}

		if (cur + 1 <= N) {
			dfs(cur + 1, USED, value + jumpCost[cur][SMALL]);
		}
		if (cur + 2 <= N) {
			dfs(cur + 2, USED, value + jumpCost[cur][BIG]);
		}
		if (cur + 3 <= N && USED == NO) {
			dfs(cur + 3, YES, value + BIGGEST);
		}
	}
}
