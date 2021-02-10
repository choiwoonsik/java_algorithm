package Gold이상문제_정리;

import java.io.*;

public class 신기한소수2_2023 {
	static int N;
	static StringBuilder str = new StringBuilder();
	static StringBuilder ret = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		backtracking(str);
		System.out.println(ret);
	}

	private static void backtracking(StringBuilder s) {
		if (s.length() == N) {
			ret.append(s).append("\n");
			return;
		}
		if (s.length() > N) return;

		for (int i = 1; i <= 9; i++) {
			if (isPossible(i, s)) {
				s.append(i);
				backtracking(s);
				s.delete(s.length() - 1, s.length());
			}
		}
	}

	private static boolean isPossible(int num, StringBuilder s) {
		s.append(num);
		int number = Integer.parseInt(s.toString());
		s.delete(s.length()-1, s.length());

		int div = 2;
		if (number < div) return false;
		else {
			while (div <= Math.sqrt(number))
				if (number % div++ == 0) return false;
		}
		return true;
	}
}
