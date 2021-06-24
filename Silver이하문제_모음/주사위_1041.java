package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class 주사위_1041 {
	static int N, Min1, Min2, Min3;
	static int[] dice = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for (int d = 0; d < 6; d++) {
			dice[d] = Integer.parseInt(st.nextToken());
		}

		Min1 = find_1();
		Min2 = find_2();
		Min3 = find_3();

		long answer = 0;
		if (N != 1) {
			long sum_min1 = Min1 * ((long) (N - 2) * (N - 2) * 5 + (long) (N - 2) * 4);
			long sum_min2 = Min2 * ((long) (N - 1) * 4 + (long) (N - 2) * 4);
			long sum_min3 = Min3 * ((long) (4));

			answer = sum_min3 + sum_min2 + sum_min1;
		} else {
			int s = 0;
			int max = 0;
			for (int n : dice) {
				s += n;
				max = Math.max(max, n);
			}
			s -= max;
			answer = s;
		}
		System.out.println(answer);
	}

	private static int find_3() {
		int Min = 10000000;

		int[] up = new int[]{0, 5};
		int[] mid = new int[]{1, 4};
		int[] down = new int[]{2, 3};

		for (int i = 0; i < 2; i++) {
			int d1 = dice[up[i]];
			for (int j = 0; j < 2; j++) {
				int d2 = dice[mid[j]];
				for (int k = 0; k < 2; k++) {
					int d3 = dice[down[k]];
					Min = Math.min(Min, d1 + d2 + d3);
				}
			}
		}

		return Min;
	}

	private static int find_2() {
		int Min = 10000000;

		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				if (i + j != 5) {
					Min = Math.min(Min, dice[i] + dice[j]);
				}
			}
		}

		return Min;
	}

	private static int find_1() {
		int Min = 10000000;
		for (int n : dice)
			Min = Math.min(Min, n);
		return Min;
	}
}
