package Gold이상문제_정리;

/*
5457
3
6 7 8
 */

import java.io.*;
import java.util.*;

public class 리모컨_1107 {
	static int Goal, G_len;
	static int N, Min;
	static boolean[] fail_button = new boolean[10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String tmp = br.readLine();
		Goal = Integer.parseInt(tmp);
		G_len = tmp.length();
		N = Integer.parseInt(br.readLine());
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int b = 0; b < N; b++) {
				int fail_b = Integer.parseInt(st.nextToken());
				fail_button[fail_b] = true;
			}
		}

		if (Goal == 100)
			System.out.println(0);
		else {
			Min = Math.abs(Goal - 100);
			StringBuilder str = new StringBuilder();
			DFS_SAME(str, 0);
			if (G_len > 1)
				DFS_LOWER(str, 0);
			DFS_GREATER(str, 0);
			System.out.print(Min);
		}
	}

	private static void DFS_GREATER(StringBuilder now_str, int push) {
		if (push > Min)
			return;
		if (push == G_len+1) {
			// 숫자를 다 친 경우 -> 덧셈 뺄셈으로 이동
			int now_i = Integer.parseInt(now_str.toString());
			Min = Math.min(Math.abs(Goal - now_i) + push, Min);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (!fail_button[i]) {
				now_str.append(i);
				DFS_GREATER(now_str, now_str.length());
				now_str.deleteCharAt(now_str.length() - 1);
			}
		}
	}

	private static void DFS_LOWER(StringBuilder now_str, int push) {
		if (push > Min)
			return;
		if (push == G_len-1) {
			// 숫자를 다 친 경우 -> 덧셈 뺄셈으로 이동
			int now_i = Integer.parseInt(now_str.toString());
			Min = Math.min(Math.abs(Goal - now_i) + push, Min);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (!fail_button[i]) {
				now_str.append(i);
				DFS_LOWER(now_str, now_str.length());
				now_str.deleteCharAt(now_str.length() - 1);
			}
		}
	}

	private static void DFS_SAME(StringBuilder now_str, int push) {
		if (push > Min)
			return;
		if (push == G_len) {
			// 숫자를 다 친 경우 -> 덧셈 뺄셈으로 이동
			int now_i = Integer.parseInt(now_str.toString());
			Min = Math.min(Math.abs(Goal - now_i) + push, Min);
			return;
		}

		for (int i = 0; i <= 9; i++) {
			if (!fail_button[i]) {
				now_str.append(i);
				DFS_SAME(now_str, now_str.length());
				now_str.deleteCharAt(now_str.length() - 1);
			}
		}
	}
}
