package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 빌런호석_22251 {
	static int N, K, P, X, ans;
	static boolean[][] nums = {
			{true, true, true, false, true, true, true},
			{false, false, true, false, false, true, false},
			{true, false, true, true, true, false, true},
			{true, false, true, true, false, true, true},
			{false, true, true, true, false, true, false},
			{true, true, false, true, false, true, true},
			{true, true, false, true, true, true, true},
			{true, false, true, false, false, true, false},
			{true, true, true, true, true, true, true},
			{true, true, true, true, false, true, true}
	};
	static int[][] diffs = new int[10][10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		P = Integer.parseInt(input[2]);
		X = Integer.parseInt(input[3]);
		// 1 ~ N
		// K 자리수
		// P개 변경 가능
		// 현재 X

		makeDiffs();

		for (int i = 1; i <= N; i++) {
			int count = 0;
			int tmpN = i;
			int tmpX = X;
			if (i == X) continue;
			for (int k = 0; k < K; k++) {
				count += diffs[tmpN % 10][tmpX % 10];
				tmpN /= 10;
				tmpX /= 10;
				if (count > P) break;
			}
			if (count <= P) ans++;
		}
		System.out.println(ans);
	}

	private static void makeDiffs() {
		int count;
		for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 10; j++) {
				count = 0;
				for (int k = 0; k < 7; k++) {
					if (nums[i][k] != nums[j][k]) count++;
				}
				diffs[i][j] = count;
				diffs[j][i] = count;
			}
		}
	}
}
