package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 오르막수_11057 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[][] DP = new int[1001][10];
		for (int i = 0; i < 10; i++) {
			DP[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int k = 0; k < 10; k++) {
				for (int l = 0; l <= k; l++) {
					DP[i][k] += DP[i-1][l] % 10007;
				}
			}
		}

		long ret = 0;
		for (int s = 0; s < 10; s++) {
			ret += DP[N][s] % 10007;
		}
		System.out.println(ret % 10007);
	}
}
