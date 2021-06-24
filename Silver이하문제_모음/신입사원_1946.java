package Silver이하문제_모음;

import java.util.*;
import java.io.*;

public class 신입사원_1946 {
	static int T, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0)
		{
			N = Integer.parseInt(br.readLine());
			int[] pass = new int[N+1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				pass[a] = b;
			}

			int count = 0;
			int last = 1;
			for (int i = 1; i <= N; i++) {
				if (pass[last] >= pass[i]) {
					last = i;
					count++;
				}
			}
			answer.append(count).append("\n");
		}
		System.out.println(answer);
	}
}
