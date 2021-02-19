package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 궁금한민호_1507 {
	static int N;
	static int[][] road;
	static boolean[][] use;
	static int time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		road = new int[N+1][N+1];
		use = new boolean[N+1][N+1];
		for (int i = 1; i < N+1; i++) {
			Arrays.fill(use[i], true);
			use[i][i] = false;
		}
		boolean isWrong = false;

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		loop: for (int r = 1; r <= N; r++) {
			for (int s = 1; s <= N; s++) {
				for (int e = 1; e <= N; e ++) {
					if (r == s || r == e || s == e) continue;
					if (road[s][e] == road[s][r] + road[r][e])
						use[s][e] = false;
					else if (road[s][e] > road[s][r] + road[r][e]){
						isWrong = true;
						break loop;
					}
				}
			}
		}

		if (isWrong) {
			System.out.println(-1);
			return;
		}
		for (int s = 1; s <= N; s++) {
			for (int e = 1; e <= N; e++) {
				if (use[s][e]) {
					time += road[s][e];
				}
			}
		}
		System.out.println(time/2);
	}
}
