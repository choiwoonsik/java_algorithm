package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 플로이드_11404 {
	static long INF = 1000000001;
	static int N, M;
	static long[][] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder str = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new long[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			Arrays.fill(adj[i], INF);
			adj[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (adj[s][e] > c)
				adj[s][e] = c;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (adj[i][j] > adj[i][k] + adj[k][j])
						adj[i][j] = adj[i][k] + adj[k][j];
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (adj[i][j] == INF)
					str.append("0 ");
				else
					str.append(adj[i][j]).append(" ");
			}
			str.append("\n");
		}
		System.out.println(str);
	}
}
