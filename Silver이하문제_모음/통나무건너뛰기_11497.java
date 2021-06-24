package Silver이하문제_모음;

import java.io.*;
import java.util.*;

public class 통나무건너뛰기_11497 {
	static int T, N;
	static int[] nums;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0)
		{
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			nums = new int[N];
			for (int i = 0; i < N; i++) {
				int n = Integer.parseInt(st.nextToken());
				nums[i] = n;
			}
			solve();
		}
		System.out.println(answer);
	}

	private static void solve() {
		Arrays.sort(nums);
		int[] newNums = new int[nums.length];
		int time = 0;
		int idx = 0;

		for (int n : nums) {
			if (time % 2 == 0) {
				newNums[idx] = n;
			}
			else {
				int pos = newNums.length - idx - 1;
				newNums[pos] = n;
				idx++;
			}
			time++;
		}

		int maxDiff = 0;
		for (int i = 0; i < newNums.length; i++) {
			maxDiff = Math.max(maxDiff, Math.abs(newNums[(i + 1) % newNums.length] - newNums[i]));
		}
		answer.append(maxDiff).append("\n");
	}
}
