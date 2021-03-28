package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class Cubeditor_1701 {
	static int[] pi;
	static char[] dest;
	static char[] src;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		dest = br.readLine().toCharArray();

		find_KMP();
		System.out.println(max);
	}

	private static void find_KMP() {
		for (int i = 0; i < dest.length; i++) {
			src = new char[dest.length - i];
			src = Arrays.copyOfRange(dest, i, dest.length);
			make_pi(src);

			for (int k : pi)
				max = Math.max(max, k);
		}
	}

	private static void make_pi(char[] src) {
		pi = new int[src.length];

		int j = 0;
		for (int i = 1; i < src.length; i++) {

			while (j > 0 && src[i] != src[j]) {
				j = pi[j - 1];
			}

			if (src[i] == src[j]) {
				pi[i] = ++j;
			}
		}
	}
}
