package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 소수인팰린드롬_1990 {
	static int A, B;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		String a = st.nextToken();
		A = Integer.parseInt(a);
		String b = st.nextToken();
		B = Integer.parseInt(b);

		findPrimePallindrom(A);
	}

	private static void findPrimePallindrom(int nA) {
		StringBuilder answer = new StringBuilder();

		while (nA <= B)
		{
			if (isPallindrom(nA) && isPrime(nA))
				answer.append(nA).append("\n");
			nA++;
		}
		answer.append("-1");
		System.out.println(answer);
	}

	private static boolean isPallindrom(int myS) {
		int reverse = 0;
		int origin = myS;

		while (myS > 0)
		{
			reverse *= 10;
			reverse += myS % 10;
			myS /= 10;
		}

		return reverse == origin;
	}

	private static boolean isPrime(int nA) {
		if (nA == 1) return false;
		if (nA == 2) return true;

		int div = 2;
		while (div <= Math.sqrt(nA)) {
			if (nA % div == 0) {
				return false;
			}
			div++;
		}
		return true;
	}
}
