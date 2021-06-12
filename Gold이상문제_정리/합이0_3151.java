package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
입력은 표준 입력으로 주어진다.

첫 번째 줄에 학생의 수 N이 입력된다. 두 번째 줄에 N개의 그녀가 가르칠 학생들의 코딩 실력인 Ai가 주어진다.

(1 ≤ N ≤ 10000, -10000 ≤ Ai ≤ 10000)
10
2 -5 2 3 -4 7 -4 0 1 -6

 */

public class 합이0_3151 {
	static int N, Cnt;
	static int[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(A);

		for (int i = 0; i < N - 2; i++)
			find_zero(i);

		System.out.println(Cnt);
	}
	// -6 -5 -4 -4 0 1 2 2 3 7
	private static void find_zero(int index) {
		int left = index + 1;
		int right = N - 1;

		while (left < right) {
			if (A[left] + A[right] + A[index] < 0) left++;
			if (A[left] + A[right] + A[index] > 0) right--;
			if (left >= right) break;
			if (A[left] + A[right] + A[index] == 0) {
				if (A[left] == A[right]) {
					int diff = right - left + 1;
					Cnt += (diff * (diff - 1)) / 2;
					break;
				}
				else {
					int tmp_l = left;
					int cnt_l = 0;
					while (A[tmp_l] == A[left]) {
						tmp_l++;
						cnt_l++;
					}
					left = tmp_l;
					int tmp_r = right;
					int cnt_r = 0;
					while (A[tmp_r] == A[right]) {
						tmp_r--;
						cnt_r++;
					}
					right = tmp_r;
					Cnt += cnt_l * cnt_r;
				}
			}
		}
	}
}
