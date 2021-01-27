package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 신기한소수_2023 {
	static int[] odd = new int[]{1, 2, 3, 5, 7, 9};
	static int N;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		//N자리수가 주어졌을때, 왼쪽부터 1~N개의 수가 소수인 수를 찾자
		// 1의자리, 2의자리등 끝의 자리가 짝수로 끝나는 경우가 있으면 안된다

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		dfs(0,0);
		System.out.print(str);
	}

	private static void dfs(int num, int len)
	{
		if (len == N) {
			str.append(num).append("\n");
			return;
		}

		for (int i = 0; i <= 5; i++) {
			if (isPrime(num*10+odd[i]))
				dfs(num*10+odd[i], len + 1);
		}
	}

	private static boolean isPrime(int n) {
		if (n < 2) return false;
		int div = 2;

		while (div <= Math.sqrt(n))
		{
			if (n % div == 0)
				return false;
			div++;
		}
		return true;
	}
}
