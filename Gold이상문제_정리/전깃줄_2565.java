package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 전깃줄_2565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] A = new int[501];
		int[] dp = new int[501];
		Arrays.fill(A, 0);
		Arrays.fill(dp, 0);
		int max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			A[a] = b;
			max = Math.max(max, Math.max(a, b));
		}

		int last = 0;
		if (A[1] != 0)
			dp[1] = 1;
		else
			dp[1] = 0;
		for (int i = 2; i <= max; i++)
		{
			if (A[i] != 0)
				dp[i] = 1;
			else
				dp[i] = 0;
			for (int j = 1; j < i; j++) {
				if (A[i] > A[j] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;
			}
			last = Math.max(last, dp[i]);
		}
		System.out.println(N - last);
	}
}
