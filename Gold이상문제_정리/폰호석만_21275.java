package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 폰호석만_21275 {
	static String A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		A = input[0];
		B = input[1];

		long X = 0;

		//ep jh
		List<Long> aZinbubs = Nto10(A);
		List<Long> bZinbubs = Nto10(B);

		if (aZinbubs == null || bZinbubs == null) {
			System.out.println("Impossible");
			return;
		}
		List<long[]> answer = new LinkedList<>();
		for (int i = 0; i < aZinbubs.size(); i++) {
			for (int j = 0; j < bZinbubs.size(); j++) {
				if (i == j) continue;
				if (aZinbubs.get(i).equals(bZinbubs.get(j))) {
					answer.add(new long[]{aZinbubs.get(i), 36 - i, 36 - j});
				}
			}
		}
		if (answer.size() == 1) System.out.println(answer.get(0)[0] + " " + answer.get(0)[1] + " " + answer.get(0)[2]);
		if (answer.size() > 1) System.out.println("Multiple");
		if (answer.size() == 0) System.out.println("Impossible");

	}

	private static ArrayList<Long> Nto10(String numStr) {
		ArrayList<Long> notations = new ArrayList<>();
		long X;
		int i;
		loop:
		for (int nota = 36; nota >= 2; nota--) {
			X = 0;
			i = 0;
			for (; i < numStr.length(); i++)
			{
				X *= nota;
				int nowX;
				if (numStr.charAt(i) <= 'z' && numStr.charAt(i) >= 'a') nowX = numStr.charAt(i) - 'a' + 10;
				else nowX = numStr.charAt(i) - '0';
				if (nowX >= nota) break loop;
				X += nowX;
				if (X >= Long.MAX_VALUE || X < 0) return null;
			}
			notations.add(X);
		}
		return notations;
	}
}
