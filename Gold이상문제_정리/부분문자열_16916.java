package Gold이상문제_정리;
/*
baekjoon
aek
 */

import java.io.*;
import java.util.*;

public class 부분문자열_16916 {
	static int[] pi;
	static char[] dest;
	static char[] src;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isFind = false;

		dest = br.readLine().toCharArray();
		src = br.readLine().toCharArray();
		pi = new int[src.length];

		int j = 0;

		// pi배열 만들기
		for (int i = 1; i < src.length; i++) {

			while (j > 0 && src[j] != src[i])
			{
				j = pi[j - 1];
			}

			if (src[j] == src[i])
			{
				pi[i] = ++j;
			}
		}

		j = 0;
		for (int i = 0; i < dest.length; i++) {

			while (j > 0 && dest[i] != src[j])
			{
				j = pi[j - 1];
			}

			if (dest[i] == src[j]) j++;

			if (j == src.length) {
				isFind = true;
				break;
			}
		}
		System.out.println(isFind ? "1" : "0");
	}
}
