package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_B_16953 {
	static long A, B, Answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		// 곱하기
		// 1 붙이기
		Answer = 1000000000;
		DFS(A, B, 0);
		if (Answer != 1000000000) System.out.println(++Answer);
		else System.out.println(-1);
	}

	private static void DFS(long a, long b, long count) {
//		System.out.println(a +", "+ b+", "+ count);
		if (a == b) {
			Answer = Math.min(count, Answer);
			return;
		}
		if (a > b) {
			return;
		}
		DFS (2 * a, b, count+1);
		DFS(a * 10 + 1, b, count+1);
	}
}
