package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 단어맞추기_9081 {
	static int N;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		while (N-- > 0) nextPermutation(br.readLine());

		System.out.print(answer);
	}

	private static void nextPermutation(String readLine) {
		char[] alphbet = readLine.toCharArray();

		int l;
		int r = 0;

		loop:
		for (l = alphbet.length - 2; l >= 0; l--) {
			char left = alphbet[l];
			for (r = alphbet.length - 1; r > l; r--) {
				if (left < alphbet[r]) {
					break loop;
				}
			}
		}

		if (l == -1) {
			answer.append(readLine).append("\n");
			return;
		}

		char tmp = alphbet[l];
		alphbet[l] = alphbet[r];
		alphbet[r] = tmp;

		Arrays.sort(alphbet, l + 1, alphbet.length);

		StringBuilder word = new StringBuilder();
		for (char c : alphbet) word.append(c);
		answer.append(word).append("\n");
	}
}
