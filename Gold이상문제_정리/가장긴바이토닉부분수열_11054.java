package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 가장긴바이토닉부분수열_11054 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] dp = new int[N];
		int[] dp2 = new int[N];

		dp[0] = 1;
		// 왼쪽에서 증가
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j] && dp[j] + 1 > dp[i])
					dp[i] = dp[j] + 1;
			}
		}

		// 오른쪽에서 증가
		dp2[N - 1] = 1;
		for (int i = N - 2; i >= 0; i--) {
			dp2[i] = 1;
			for (int j = N -1; j > i; j--) {
				if (A[i] > A[j] && dp2[j] + 1 > dp2[i])
					dp2[i] = dp2[j] + 1;
			}
		}

		// 둘의 합의 최대값
		int len = 1;
		for (int i = 0; i < N; i++) {
			len = Math.max(len, dp[i] + dp2[i]);
		}
		System.out.println(len - 1);
	}
}
