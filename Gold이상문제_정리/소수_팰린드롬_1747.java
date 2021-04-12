package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 소수_팰린드롬_1747 {
	static int N;
	static boolean[] is_not_prime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		is_not_prime = new boolean[1003002];
		N = Integer.parseInt(br.readLine());
		is_not_prime[1] = true;

		for (int i = 2; i < 1003002; i++) {
			if (!is_not_prime[i]) {
				if (i > N && is_pallindrom(String.valueOf(i))) {
					System.out.println(i);
					return;
				}
				not_prime(i);
			}
		}
	}

	private static void not_prime(int n) {
		int plus = n;
		while (n < 1003002) {
			is_not_prime[n] = true;
			n += plus;
		}
	}

	private static boolean is_pallindrom(String s) {
		// is 팰린드롬
		if (s.length() == 0 || s.length() == 1)
			return true;
		else if (s.charAt(0) == s.charAt(s.length()-1))
			return is_pallindrom(s.substring(1, s.length()-1));
		// no 팰린드롬
		return false;
	}
}
