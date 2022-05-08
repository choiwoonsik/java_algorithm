package 코테.카카오_2022_05;

import java.util.*;

public class sol1 {
	public static void main(String[] args) {
		Solution sol = new Solution();

	}

	/*
	각 지표에서 더 높은 점수를 받은 성격 유형이 검사자의 성격 유형이라고 판단합니다.
	단, 하나의 지표에서 각 성격 유형 점수가 같으면,
	두 성격 유형 중 사전 순으로 빠른 성격 유형을 검사자의 성격 유형

	survey : RT
	R : 첫 번째 캐릭터는 i+1번 질문의 비동의 관련 선택지
	T : 두 번째 캐릭터는 i+1번 질문의 동의 관련 선택지
	*/
	private static class Solution {
		static int[] total = new int[8];

		public String solution(String[] survey, int[] choices) {

			for (int i = 0; i < choices.length; i++) {

				String type = survey[i];
				char left = type.charAt(0);
				char right = type.charAt(1);

				int point = choices[i];
				if (point == 0) {
					continue;
				}

				if (point <= 3) {
					int num = typeToNum(left);
					total[num] += 4 - point;
				} else if (point >= 5) {
					int num = typeToNum(right);
					total[num] += point - 4;
				}
			}

			StringBuilder answer = new StringBuilder();
			for (int i = 0; i < 8; i += 2) {
				if (total[i] != total[i + 1]) {
					char c;
					if (total[i] > total[i + 1]) {
						c = numToType(i);
					} else {
						c = numToType(i + 1);
					}
					answer.append(c);
				} else {
					char l = numToType(i);
					char r = numToType(i + 1);
					answer.append(l < r ? l : r);
				}
			}

			return answer.toString();
		}

		private char numToType(int num) {
			if (num == 0) return 'R';
			if (num == 1) return 'T';
			if (num == 2) return 'C';
			if (num == 3) return 'F';
			if (num == 4) return 'J';
			if (num == 5) return 'M';
			if (num == 6) return 'A';
			if (num == 7) return 'N';

			return 0;
		}

		private int typeToNum(char c) {
			if (c == 'R') return 0;
			if (c == 'T') return 1;
			if (c == 'C') return 2;
			if (c == 'F') return 3;
			if (c == 'J') return 4;
			if (c == 'M') return 5;
			if (c == 'A') return 6;
			if (c == 'N') return 7;
			return -1;
		}
	}
}
