package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 줄어들지않아_2688 {
	static long[][] DP = new long[65][10];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] answer = new long[65];

		for (int j = 0; j < 10; j++)
			DP[1][j] = 1;
		answer[1] = 10;

		for (int j = 2; j <= 64; j++) {
			for (int k = 0; k < 10; k++) {
				for (int l = 0; l <= k; l++) {
					DP[j][k] += DP[j - 1][l];
				}
			}
			answer[j] = 0;
			for (int k = 0; k < 10; k++)
				answer[j] += DP[j][k];
		}

		StringBuilder str = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int len = Integer.parseInt(br.readLine());
			str.append(answer[len]).append("\n");
		}
		System.out.print(str);
	}
}
