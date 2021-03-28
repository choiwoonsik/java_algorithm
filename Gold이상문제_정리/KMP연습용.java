package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
N
ABCABD
 */
public class KMP연습용 {
	static int N;
	static int[] pi;
	static char[] target;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		pi = new int[N];

		target = br.readLine().toCharArray();
		for (char c : target)
			System.out.print(c + " ");
		System.out.println();

		// pi배열 만들기
		int j = 0;

		for (int i = 1; i < target.length; i++) {

			// 다른데, 같았던 경우가 있었으면 직전에 같았던 부분을 확인
			while (j > 0 && target[j] != target[i]) {
				j = pi[j - 1];
			}

			// 같은 경우
			if (target[j] == target[i]) pi[i] = ++j;
		}

		System.out.println("end make pi");
		for (int i : pi) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}
