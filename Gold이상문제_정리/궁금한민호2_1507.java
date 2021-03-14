package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 궁금한민호2_1507 {
	static int N;
	static int[][] table;
	static int[][] sub;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		table = new int[N][N];
		sub = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		boolean isFalse = false;
		loop: for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (k == i || k == j) continue ;
					if (table[i][j] == table[i][k] + table[k][j])
						sub[i][j] = 1;
					else if (table[i][j] > table[i][k] + table[k][j]) {
						isFalse = true;
						break loop;
					}
				}
			}
		}

		int sum = 0;
		if (isFalse) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (sub[i][j] == 0) {
					sum += table[i][j];
				}
			}
		}
		System.out.println(sum/2);
	}
}
