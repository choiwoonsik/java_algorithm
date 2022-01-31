package SDS_알고리즘.Day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 앱_7579 {
	static int N;
	static int M;
	static int totalCost;
	static int[] memmory;
	static int[] cost;
	static int[][] DP;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		memmory = new int[N + 1];
		cost = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			memmory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			totalCost += cost[i];
		}
		DP = new int[N + 1][totalCost + 1];

		for (int app = 1; app <= N; app++) {
			int curCost = cost[app];
			int curMem = memmory[app];

			for (int c = 0; c <= totalCost; c++) {

				DP[app][c] = DP[app - 1][c];

				if (c < curCost) continue;

				if (DP[app][c] < DP[app - 1][c - curCost] + curMem) {
					DP[app][c] = DP[app - 1][c - curCost] + curMem;
				}
			}
		}

		for (int i = 1; i <= totalCost; i++) {
			if (DP[N][i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}
