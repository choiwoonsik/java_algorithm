package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
1q2w3e4r5t6y
qwerty

prsent : 1
no present : 0
 */

public class 나는친구가적다_16172 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String note = br.readLine();
		char[] pattern = br.readLine().toCharArray();
		StringBuilder sub_note = new StringBuilder();
		for (int i = 0; i < note.length(); i++)
		{
			if (note.charAt(i) <= '9' && note.charAt(i) >= '0')
				continue;
			sub_note.append(note.charAt(i));
		}
		char[] real_note = sub_note.toString().toCharArray();

		int[] pi = getPi(pattern);

		// i : 전체 문자열
		int j = 0;
		for (int i = 0; i < real_note.length; i++) {
			// while 돌면서 반복된 부분 땡겨줌
			while (j > 0 && real_note[i] != pattern[j])
				j = pi[j - 1];

			if (real_note[i] == pattern[j]) {
				if (j == pattern.length - 1) {
					System.out.println(1);
					return;
				}
				j++;
			}
		}
		System.out.println(0);
	}

	private static int[] getPi(char[] target) {
		int[] pi = new int[target.length];
		Arrays.fill(pi, 0);

		int j = 0;
		for (int i = 1; i < target.length; i++) {

			while (j > 0 && target[i] != target[j])
				j = pi[j - 1];

			if (target[i] == target[j])
				pi[i] = ++j;
		}

		return pi;
	}
}
