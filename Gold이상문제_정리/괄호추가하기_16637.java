package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
9
3+8*7-9*2

7
8*3+5+2*3+1-9+8-19
 */
public class 괄호추가하기_16637 {
	static int L;
	static char[] dp;
	static boolean[] paired;
	static int max = -(Integer.MAX_VALUE-1);
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		L = Integer.parseInt(br.readLine());
		dp = br.readLine().toCharArray();
		paired = new boolean[dp.length];

		dfs(0, 0);
		System.out.println(max);
	}

	private static void dfs(int idx, int val) {

		if (idx >= dp.length) {
			max = Math.max(val, max);
			return;
		}

		char operation;
		if (idx == 0) operation = '+';
		else operation = dp[idx - 1];

		if (idx + 2 < L) {
			// 묶는 경우
			int inner_calc = calc(dp[idx] - '0', dp[idx + 2] -'0', dp[idx + 1]);
			dfs(idx + 4, calc(val, inner_calc, operation));
		}
		// 안묶는 경우
		dfs(idx + 2, calc(val, dp[idx] - '0', operation));
	}

	private static int calc(int before, int after, char oper) {
		if (oper == '+') return before += after;
		if (oper == '-') return before -= after;
		if (oper == '*') return before *= after;
		return 0;
	}
}
