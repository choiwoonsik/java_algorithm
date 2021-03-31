package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 낚씨왕_17143 {
	static fish[][] sea;
	static fish[][] sub_sea;
	static int R, C, N, all_catch;
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		sea = new fish[R+1][C+1];
		sub_sea = new fish[R+1][C+1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			sea[r][c] = new fish(s, d, size);
		}

		for (int pos = 1; pos <= C; pos++) {
			fish_catch(pos);
			move_fish();
			for (int i = 0; i <= R; i++) {
				sea[i] = sub_sea[i].clone();
				Arrays.fill(sub_sea[i], null);
			}
		}
		System.out.println(all_catch);
	}

	private static void move_fish() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (sea[i][j] != null) {
					int ny = i;
					int nx = j;

					for (int move = 0; move < sea[i][j].s; move++) {
						// 아래, 오른쪽 - 방향 변경
						if ((sea[i][j].d == 2 && ny == R) || (sea[i][j].d == 3 && nx == C)) {
							if (sea[i][j].d == 2) sea[i][j].d = 1;
							else sea[i][j].d = 4;
						}
						// 위, 왼쪽 - 방향 변경
						else if ((sea[i][j].d == 1 && ny == 1) || (sea[i][j].d == 4 && nx == 1)) {
							if (sea[i][j].d == 1) sea[i][j].d = 2;
							else sea[i][j].d = 3;
						}
						ny += dy[sea[i][j].d];
						nx += dx[sea[i][j].d];
					}

					if (sub_sea[ny][nx] != null) {
						sub_sea[ny][nx] = sub_sea[ny][nx].size > sea[i][j].size ? sub_sea[ny][nx] : sea[i][j];
					} else sub_sea[ny][nx] = sea[i][j];
					sea[i][j] = null;
				}
			}
		}
	}

	private static void fish_catch(int pos) {
		for (int r = 1; r <= R; r++) {
			if (sea[r][pos] != null) {
				all_catch += sea[r][pos].size;
				sea[r][pos] = null;
				break;
			}
		}
	}

	private static class fish {
		int s, d, size;

		public fish(int s, int d, int size) {
			this.s = s;
			this.d = d;
			this.size = size;
		}
	}
}
/*
4 4 4
1 1 1 2 10
2 1 0 1 1
3 1 1 2 10
4 1 0 1 1

100 7 7
3 2 2 3 9
3 3 1 3 3
3 5 1 4 7
3 6 2 4 6
2 4 1 2 8
1 4 2 2 4
4 4 1 1 5
 */