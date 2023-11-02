package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class 쉬운계단수_10844 {
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new long[N + 1][10];

		for (int i = 1; i < 10; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			for (int num = 0; num < 10; num++) {
				if (num == 0) {
					dp[i][num] = dp[i - 1][1] % 1000000000;
				}
				else if (num == 9) {
					dp[i][num] = dp[i - 1][8] % 1000000000;
				}
				else {
					dp[i][num] = (dp[i - 1][num - 1] + dp[i - 1][num + 1]) % 1000000000;
				}
			}
		}
		Dot[] dots = new Dot[100];
		for (int i = 0; i < 100; i++) {
			dots[i] = new Dot(i, i + 1);
		}
		Map<Integer, List<Dot>> collect = Arrays.stream(dots).collect(Collectors.groupingBy(it -> it.y));
		int[] array = Arrays.stream(dots).mapToInt(it -> it.y + it.x).toArray();
		List<Dot> list = Arrays.asList(dots);
		Arrays.fill(array, 1);


		System.out.print(Arrays.stream(dp[N]).sum() % 1000000000);
	}

	private static class Dot {
		int y;
		int x;
		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
