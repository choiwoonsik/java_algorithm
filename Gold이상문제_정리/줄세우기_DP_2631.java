package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 줄세우기_DP_2631 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] DP = new int[N];
		int[] child = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			child[i] = num;
		}

		DP[0] = 1;
		for (int i = 1; i < N; i++) {
			DP[i] = 1; // 최소 한명
			for (int j = 0; j < i; j++) {
				if (child[i] > child[j] && DP[i] < DP[j] + 1) { // 다음학생 추가 가능
					DP[i] = DP[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++)
			max = Math.max(max, DP[i]);
		System.out.println(N - max);
	}
}
