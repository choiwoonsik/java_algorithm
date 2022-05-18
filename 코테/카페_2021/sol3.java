package 코테.카페_2021;

public class sol3 {

	public static void main(String[] args) {
		Solution sol;
		int a;

		sol = new Solution();
		a = sol.solution("abbbcbbb", "bbb");
		System.out.println(a);
		System.out.println(a == 2);

		sol = new Solution();
		a = sol.solution("abcabcabc", "abc");
		System.out.println(a);
		System.out.println(a == 4);

		sol = new Solution();
		a = sol.solution("bbbbb", "bb");
		System.out.println(a);
		System.out.println(a == 10);

		sol = new Solution();
		a = sol.solution("abacaba", "acb");
		System.out.println(a);
		System.out.println(a == 0);

				/*
		|bbbbb|  /  bb
		|bb   |
		| bb  |
		|  bb |
		|   bb|
		|b b  |
		| b b |
		|  b b|
		|b  b |
		| b  b|
		|b   b|
		 */
	}

	private static class Solution {
		static int maxLen;

		private int solution(String line1, String line2) {
			maxLen = line1.length();
			int total = 0;

			int len = line2.length();
			for (int space = 0; space * (len - 1) + line2.length() <= maxLen; space++) {
				total += findWord(space, line1, line2);
			}

			return total;
		}

		private int findWord(int space, String line1, String line2) {
			int len = line2.length();
			int matchingCount = 0;

			for (int start = 0; start + space * (len - 1) + len <= line1.length(); start++) {

				if (line1.charAt(start) == line2.charAt(0) && start + space * (len - 1) + len <= line1.length()) {
					int line2Idx = 0;
					boolean isMatching = true;
					for (int i = start; i < line1.length(); i += (space + 1)) {
						if (line2Idx >= len) break;
						if (line1.charAt(i) != line2.charAt(line2Idx++)) {
							isMatching = false;
						}
					}

					if (isMatching) {
						matchingCount++;
					}
				}
			}

			return matchingCount;
		}
	}
}
