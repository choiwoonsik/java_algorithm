package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 좋은수열2_2661 {
	static StringBuilder str = new StringBuilder();
	static int N;
	static boolean isDone;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str.append("1");
		backtracking(str);
	}

	private static void backtracking(StringBuilder s) {
		if (s.length() == N && !isDone) {
			System.out.println(s);
			isDone = true;
			return;
		}
		if (isDone) return;

		for (int i = 1; i <= 3; i++) {
			if (isPossible(i, str)) {
				str.append(i);
				backtracking(s);
				str.delete(str.length()-1, str.length());
			}
		}
	}

	private static boolean isPossible(int num, StringBuilder s) {
		s.append(num);
		for (int i = 1; i <= s.length()/2; i++) {
			if (s.substring(s.length() - i, s.length()).equals(s.substring(s.length() - 2 * i, s.length() - i))) {
				s.delete(s.length()-1, s.length());
				return false;
			}
		}
		s.delete(s.length()-1, s.length());
		return true;
	}
}
