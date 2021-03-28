package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 이름정하기_16900 {
	static StringBuilder plus_str = new StringBuilder();
	static StringBuilder str = new StringBuilder();
	static char[] S;
	static int K, cnt;
	static int[] pi;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		str.append(st.nextToken());
		plus_str.append(str);
		S = str.toString().toCharArray();
		K = Integer.parseInt(st.nextToken());
		pi = new int[S.length];

		make_pi();
//		find_KMP();
		find_KMP2();
	}

	private static void find_KMP2() {
		long ans = 0;
		ans = pi[S.length - 1] + (long) (S.length - pi[S.length - 1]) * K;
		System.out.println(ans);
	}

	private static void find_KMP() {
		while(true)
		{
			// 체크
			cnt = 0;
			if (check()) {
				System.out.println(plus_str.length());
				break;
			} else {
				// 부족하면 추가
				int j = S.length - 1;
				plus_str.append(str.substring(pi[j]));
			}
		}
	}

	private static boolean check() {
		// plus_str 에서 str 찾기 , K개?
		int j = 0;
		for (int i = 0; i < plus_str.length(); i++) {

			while (j > 0 && plus_str.charAt(i) != S[j]) {
				j = pi[j - 1];
			}

			if (plus_str.charAt(i) == S[j]) j++;

			if (j == S.length) {
				cnt++;
				j = pi[j - 1];
			}
		}
		return K == cnt;
	}

	private static void make_pi() {
		int j = 0;
		for (int i = 1; i < S.length; i++) {

			while (S[i] != S[j] && j > 0) {
				j = pi[j - 1];
			}

			if (S[i] == S[j]) pi[i] = ++j;
		}
	}
}
