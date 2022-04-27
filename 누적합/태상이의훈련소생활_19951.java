package 누적합;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
10 3
1 2 3 4 5 -1 -2 -3 -4 -5
1 5 -3
6 10 5
2 7 2
 */
public class 태상이의훈련소생활_19951 {
	static int N;
	static int Q;
	static int[] origin;
	static int[] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		origin = new int[N + 1];
		dp = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			origin[i] = Integer.parseInt(st.nextToken());
		}

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());

			query(from, to, val);
		}

		StringBuilder answer = new StringBuilder();
		
		for (int i = 1; i < N; i++) {
			dp[i] += dp[i - 1];
		}

		for (int i = 0; i < N; i++) {
			dp[i] += origin[i];
			answer.append(dp[i]).append(" ");
		}

		System.out.print(answer);
	}

	private static void query(int from, int to, int val) {
		dp[from] += val;
		dp[to + 1] -= val;
	}
}
