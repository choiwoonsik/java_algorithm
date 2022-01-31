package SDS_알고리즘.Day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열4_14002 {
	static int N;
	static int[] nums;
	static int[] dp;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		dp = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int len = 1;
		dp[0] = 1;
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			len = Math.max(len, dp[i]);
		}

		if (N == 1) len = 1;
		answer.append(len).append("\n");

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = len; i > 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (dp[j] == i) {
					list.add(nums[j]);
					N = j;
					break;
				}
			}
		}

		list.sort(null);
		for (int n : list) {
			answer.append(n).append(" ");
		}
		System.out.println(answer);
	}
}
