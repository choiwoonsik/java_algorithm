package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 고냥이_16472 {
	static int[] alpha = new int[26];
	static int N;
	static char[] cat_speak;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		cat_speak = br.readLine().toCharArray();

		int L = 0, R = 0, len = 0, can_learn = 0;
		int maxLen = 0;
		while (R < cat_speak.length) {
			int word = cat_speak[R] - 'a';
			// 안배운거
			if (alpha[word] == 0)
				can_learn++;
			alpha[word]++;
			len++;

			while (L <= R && can_learn > N) {
				int before_word = cat_speak[L] - 'a';
				alpha[before_word]--;
				if (alpha[before_word] == 0)
					can_learn--;
				len--;
				L++;
			}
			maxLen = Math.max(maxLen, len);
			R++;
		}
		System.out.println(maxLen);
	}
}
