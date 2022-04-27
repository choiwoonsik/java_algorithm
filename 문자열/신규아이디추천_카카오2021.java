package 문자열;

import java.util.ArrayList;

public class 신규아이디추천_카카오2021 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		String ret = sol.solution("...!@BaT#*..y.abcdefghijklm");
		System.out.println(ret.equals("bat.y.abcdefghi"));

		ret = sol.solution("z-+.^.");
		System.out.println(ret.equals("z--"));

		ret = sol.solution("=.=");
		System.out.println(ret.equals("aaa"));

		ret = sol.solution("123_.def");
		System.out.println(ret.equals("123_.def"));

		ret = sol.solution("abcdefghijklmn.p");
		System.out.println(ret.equals("abcdefghijklmn"));

		ret = sol.solution(".....");
		System.out.println(ret.equals("aaa"));

		ret = sol.solution("%");
		System.out.println(ret.equals("aaa"));

		ret = sol.solution("a...a");
		System.out.println(ret.equals("a.a"));

		ret = sol.solution("-.~!@#$%&*()=+[{]}:?,<>/.-");
		System.out.println(ret.equals("-.-"));
	}

	/*
	1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
	 */
	private static class Solution {
		public String solution(String new_id) {


			// case 1
			String new_id_case1 = new_id.toLowerCase();

			// case 2
			StringBuilder new_id_case2 = new StringBuilder();
			for (int i = 0; i < new_id_case1.length(); i++) {
				char c = new_id_case1.charAt(i);
				if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.') {
					new_id_case2.append(c);
				}
			}

			// case3
			int left = 0;
			int right = 0;
			StringBuilder new_id_case3 = new_id_case2;
			while (right < new_id_case3.length()) {
				if (new_id_case3.charAt(right) == '.') {
					left = right;
					while (right < new_id_case3.length() && new_id_case3.charAt(right) == '.') {
						right++;
					}
					new_id_case3.replace(left, right, ".");
					right = left + 1;
				} else right++;
			}

			// case4
			StringBuilder new_id_case4 = new_id_case3;
			if (new_id_case4.length() > 0 && new_id_case4.charAt(0) == '.') {
				new_id_case4.replace(0, 1, "");
			}
			if (new_id_case4.length() > 0 && new_id_case4.charAt(new_id_case4.length() - 1) == '.') {
				new_id_case4.replace(new_id_case4.length() - 1, new_id_case4.length(), "");
			}

			// case5
			StringBuilder new_id_case5 = new_id_case4;
			if (new_id_case5.length() == 0) {
				new_id_case5.append("a");
			}

			// case 6
			if (new_id_case5.length() >= 16) {
				new_id_case5.replace(15, new_id_case5.length(), "");
				while (new_id_case5.charAt(new_id_case5.length() - 1) == '.') {
					new_id_case5.replace(new_id_case5.length() - 1, new_id_case5.length(), "");
				}
			}

			// case6
			char last = new_id_case5.charAt(new_id_case5.length() - 1);
			while (new_id_case5.length() <= 2) {
				new_id_case5.append(last);
			}

			return new_id_case5.toString();
		}
	}
}
