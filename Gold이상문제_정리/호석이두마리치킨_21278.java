package Gold이상문제_정리;

/*
5 4
1 3
4 2
2 5
3 2
 */

import java.util.*;
import java.io.*;

public class 호석이두마리치킨_21278 {
	static final int INF = 10000001;
	static int N, M, Min = INF, I, J;
	static int[][] time;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		time = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(time[i], INF);
			time[i][i] = 0;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			time[u][v] = 1;
			time[v][u] = 1;
		}

		floyd();

		// nC2
		boolean[] visited = new boolean[N + 1];
		int[] selected = new int[2];
		Arrays.fill(visited, false);
		comb(1, 0, 2, visited, selected);

		System.out.println(I + " " + J + " " + Min);
	}

	private static void comb(int start, int select, int max, boolean[] visited, int[] selected) {
		if (select == max) {

			int sum = 0;
			for (int i = 1; i < N + 1; i++) {
//				if (visited[i]) continue;
				int min = INF;
				for (int k : selected)
					min = Math.min(time[i][k], min);
				sum += min * 2;
			}

			if (Min > sum) {
				Min = sum;
				I = selected[0];
				J = selected[1];
			}
			return;
		}

		for (int i = start; i < N + 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[select] = i;
				comb(i + 1, select + 1, max, visited, selected);
				visited[i] = false;
			}
		}
	}

	private static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (time[i][j] > time[i][k] + time[k][j]) {
						time[i][j] = time[i][k] + time[k][j];
					}
				}
			}
		}
	}
}
