package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 가장긴증가하는부분수열4_14002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[] D = new int[N];
		int ans = 1;
		D[0] = 1; // 최소 길이 1글자

		for (int i = 1; i < N; i++) {
			D[i] = 1; // 최소 길이 1글자
			for (int j = 0; j < i; j++) {
				// 증가 부분을 찾으면 && 길이가 더 길어진다면 변경
				if (A[j] < A[i] && D[j] + 1 > D[i]) {
					D[i] = D[j] + 1;
				}
			}
			if (ans < D[i]) {
				ans = D[i];
			}
		}
		System.out.println(ans);
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = ans; i > 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (D[j] == i) {
					list.add(A[j]);
					N = j;
					ans--;
					break;
				}
			}
		}
		Collections.sort(list);
		list.forEach(s -> System.out.print(s+" "));
	}
}
