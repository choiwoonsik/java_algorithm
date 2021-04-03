package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
A는 매일 매일 출근할 수 있다. B는 출근한 다음날은 반드시 쉬어야 한다. C는 출근한 다음날과 다다음날을 반드시 쉬어야 한다
CAB
 */

public class 출근기록_14238 {
	// n-1, n-2 의 출근 기록
	// a, b, c 의 남은 출근 날짜
	static boolean[][][][][] dp = new boolean[51][51][51][4][4];
	static int[] days = new int[3];
	static char[] ans = new char[51];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			days[s.charAt(i) - 'A']++;
		}

		if (!solve(0, 0, 0, 0, 0))
			System.out.println("-1");
		else
			System.out.println(ans);
	}

	private static boolean solve(int a, int b, int c, int pre1, int pre2) {

		if (a == days[0] && b == days[1] && c == days[2]) return true;

		if (dp[a][b][c][pre1][pre2]) return false;

		dp[a][b][c][pre1][pre2] = true;
		// a가 출근하는 경우
		if (days[0] > a) {
			if (solve(a + 1, b, c, 1, pre1)) {
				ans[a + b + c] = 'A';
				return true;
			}
		}
		// b가 출근하는 경우
		if (days[1] > b && pre1 != 2) {
			if (solve(a, b+1, c, 2, pre1)) {
				ans[a + b + c] = 'B';
				return true;
			}
		}
		// c가 출근하는 경우
		if (days[2] > c && pre1 != 3 && pre2 != 3) {
			if (solve(a, b, c + 1, 3, pre1)) {
				ans[a + b + c] = 'C';
				return true;
			}
		}
		return false;
	}
}
