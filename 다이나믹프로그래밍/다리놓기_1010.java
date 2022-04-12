package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
3
2 2
1 5
13 29
 */
public class 다리놓기_1010 {
	static int T;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			calc(u, v);
		}
		System.out.print(answer);
	}

	private static void calc(int u, int v) {
		u = Math.min(v - u, u);

		long t1 = 1;
		for (int i = u; i >= 1; i--) {
			t1 *= v;
			v--;
		}

		long t2 = 1;
		for (int i = u; i >= 1; i--) {
			t2 *= u;
			u--;
		}

		long t = t1 / t2;
		answer.append(t).append("\n");
	}
}
