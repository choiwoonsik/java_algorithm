package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
11245
91321150448
1226406
0
 */

public class 소수부분문자열_5636 {
	static int MAX = 0;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			MAX = 0;
			String s = br.readLine();
			if (s.length() == 1 && Integer.parseInt(s) == 0) break;

			for (int start = 0; start < s.length(); start++)
			{
				StringBuilder nowNum = new StringBuilder();
				for (int i = start; i < Math.min(s.length(), start+5); i++)
				{
					nowNum.append(s.charAt(i));
					int number = Integer.parseInt(nowNum.toString());
					if (isPrime(number))
						MAX = Math.max(MAX, number);
				}
			}
			answer.append(MAX).append("\n");
		}
		System.out.print(answer);
	}

	private static boolean isPrime(int n) {
		if (n == 1) return false;
		if (n == 2) return true;

		int div = 2;

		while (div <= Math.sqrt(n))
			if (n % div++ == 0)
				return false;
		return true;
	}
}
