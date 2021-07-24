package Gold이상문제_정리;
/*
9 2
3 2 5 5 6 4 4 5 7
 */

import java.util.*;
import java.io.*;

public class 겹치는건싫어_20922 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] nums = new int[N];
		int[] degree = new int[100001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nums[i] = Integer.parseInt(st.nextToken());

		int left = 0;
		int right = 0;
		int maxLen = 0;

		while (right < N)
		{
			int now = nums[right];

			degree[now]++;

			if (degree[now] <= K)
				maxLen = Math.max(maxLen, (right - left) + 1);

			while (left <= right && degree[now] > K)
				degree[nums[left++]]--;
			right++;
		}

		System.out.println(maxLen);
	}
}
