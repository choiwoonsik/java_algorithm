package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 역사_1613 {
	static final int INF = 10000000;
	static int n, k, q;
	static int[][] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		A = new int[n+1][n+1];

		for (int i = 0; i < n + 1; i++) {
			Arrays.fill(A[i], INF);
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			A[front][back] = 1;
		}

		for (int k = 1; k < n + 1; k++) {
			for (int i = 0; i < n + 1; i++) {
				for (int j = 0; j < n + 1; j++) {
					if (A[i][j] > A[i][k] + A[k][j])
						A[i][j] = A[i][k] + A[k][j];
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		q = Integer.parseInt(st.nextToken());
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (A[a][b] == INF && A[b][a] == INF) {
				sb.append("0\n");
			} else if (A[a][b] != INF)
				sb.append("-1\n");
			else if (A[b][a] != INF)
				sb.append("1\n");
		}

		System.out.print(sb);
	}

}
