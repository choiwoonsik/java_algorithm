package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 연구소3_17142 {
	static int N, M, Min, virus_cnt;
	static int[][] map;
	static int[][] count_map;
	static Dot[] virus_arr;
	static boolean[] activated_virus;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		Min = 987654321;
		map = new int[N][N];
		virus_arr = new Dot[10];
		activated_virus = new boolean[10];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					virus_arr[virus_cnt++] = new Dot(i, j);
			}
		}

		DFS(0, 0);
		if (Min == 987654321)
			System.out.println(-1);
		else
			System.out.println(Min);
	}

	private static void DFS(int active_cnt, int active) {
		if (active_cnt == M) {
			int time = spread_virus();
			if (check_no_blank())
				Min = Math.min(Min, time);
			return;
		}

		for (int i = active; i < virus_cnt; i++) {
			if (!activated_virus[i]) {
				activated_virus[i] = true;
				DFS(active_cnt + 1, i);
				activated_virus[i] = false;
			}
		}
	}

	private static boolean check_no_blank() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (count_map[i][j] == 0 && map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	private static int spread_virus()
	{
		int last_count = 0;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		count_map = new int[N][N];
		Queue<Dot> queue = new LinkedList<>();
		for (int i = 0; i < virus_cnt; i++) {
			if (activated_virus[i]) {
				queue.add(virus_arr[i]);
				count_map[virus_arr[i].y][virus_arr[i].x] = 1;
			}
		}

		while (!queue.isEmpty()) {
			Dot now = queue.poll();

			if (map[now.y][now.x] == 0)
				last_count = count_map[now.y][now.x] - 1;

			for (int d = 0; d < 4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (map[ny][nx] == 1) continue;
				if (count_map[ny][nx] > 0) continue;

				count_map[ny][nx] = count_map[now.y][now.x] + 1;
				queue.add(new Dot(ny, nx));
			}
		}

		return last_count;
	}

	private static class Dot
	{
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
