package SDS_알고리즘.Day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬곱셈순서_11049 {
	static int INF = 987654321;
	static int N;
	static int[][] matrixArr;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		matrixArr = new int[N][2];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			matrixArr[i] = new int[]{r, c};
		}

		for (int i = 1; i < N; i++) { // to : from + i
			for (int from = 0; from + i < N; from++) { // from
				int end = from + i;
				dp[from][end] = INF;
				calcDp(from, end);
			}
		}

		System.out.println(dp[0][N - 1]);
	}

	private static void calcDp(int from, int end) { // 자르는 곳
		for (int cut = from; cut < end; cut++) {
			int tmp = dp[from][cut] + dp[cut + 1][end] + matrixArr[from][0] * matrixArr[cut + 1][0] * matrixArr[end][1];
			dp[from][end] = Math.min(dp[from][end], tmp);
		}
	}
}
