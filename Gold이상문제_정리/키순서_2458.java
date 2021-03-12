package Gold이상문제_정리;
/*
6 6
1 5
3 4
5 4
4 2
4 6
5 2
 */

import java.io.*;
import java.util.*;

public class 키순서_2458 {
	static int INF = 987654321;
	static int N, M;
	static int[][] table;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		table = new int[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			Arrays.fill(table[i], INF);
			table[i][i] = 0;
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			table[small][big] = 1;
		}

		for (int k = 1; k < N+1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N+1; j++) {
					if (table[i][j] > table[i][k] + table[k][j])
						table[i][j] = table[i][k] + table[k][j];
				}
			}
		}

		int count = 0;
		boolean notKnow;
		for (int i = 1; i < N+1; i++) {
			notKnow = false;
			for (int j = 1; j < N+1; j++) {
				if (Math.min(table[i][j], table[j][i]) == INF) {
					notKnow = true;
					break;
				}
			}
			if (!notKnow) {
				count++;
			}
		}
		System.out.println(count);
	}
}
