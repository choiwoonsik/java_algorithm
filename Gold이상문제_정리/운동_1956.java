package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 운동_1956 {
	static int[][] table;
	static int N, M;
	static int INF = 4000001;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		table = new int[N+1][N+1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(table[i], INF);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			table[s][e] = c;
		}

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (table[i][j] > table[i][k] + table[k][j]){
						table[i][j] = table[i][k] + table[k][j];
					}
				}
			}
		}

		int min = INF;
		for (int i = 1; i < N + 1; i++) {
			min = Math.min(min, table[i][i]);
		}
		if (min == INF)
			System.out.println(-1);
		else
			System.out.println(min);
	}
}

/*
4 3
2 3 1
3 4 1
4 2 1
 */