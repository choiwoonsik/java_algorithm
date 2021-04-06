package Gold이상문제_정리.코딩테스트;

import java.util.*;

public class 카카오커머스_2_1 {
	static boolean[] parts = new boolean[16];
	public int solution(int[][] needs, int r) {
		int answer = 0;
		answer = solve(0, 0, r, needs);
		return answer;
	}

	private int solve(int now, int selected, int r, int[][] needs) {
		if (selected == r && now == needs[0].length) {
			// 끝까지 와서 부품 다 고른 경우
			int can_count = 0;
			for (int i = 0; i < needs.length; i++) {
				boolean is_fail = false;
				for (int j = 0; j < needs[i].length; j++) {
					if (needs[i][j] == 1 && !parts[j]) {
						is_fail = true;
						break;
					}
				}
				if (!is_fail) can_count++;
			}
			return can_count;
		}

		if (now == needs[0].length) {
			// 끝까지 왔는데 부품개수가 부족한 경우
			return 0;
		}

		parts[now] = true;
		int count1 = solve(now+1, selected+1, r, needs);
		parts[now] = false;
		int count2 = solve(now+1, selected, r, needs);

		return Math.max(count1, count2);
	}
}
