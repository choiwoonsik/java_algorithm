package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
7 3
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 2 0 1 1
0 1 0 0 0 0 0
2 1 0 0 0 0 2
 */

public class 연구소3_2_17142 {
	static int N, M, DAY = 1000000;
	static boolean zero = false;
	static Queue<Dot> queue = new LinkedList<>();
	static List<Dot> list = new ArrayList<>();
	static int[][] map = new int[51][51];
	static int[][] count = new int[51][51];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) list.add(new Dot(i, j));
				if (map[i][j] == 0) zero = true;
			}
		}

		if (zero) virus_comb();
		else DAY = 0;
		if (DAY == 1000000) DAY = -1;
		System.out.println(DAY);
	}

	private static void virus_comb() {
		boolean[] checked = new boolean[list.size()];
		Dot[] virus_arr = new Dot[list.size()];
		for (int i = 0; i < list.size(); i++)
			virus_arr[i] = list.get(i);

		comb(0, 0, virus_arr, checked);
	}

	private static void comb(int start, int select, Dot[] virus_arr, boolean[] checked) {
		if (select == M) {
			count = new int[51][51];
			for (int i = 0; i < virus_arr.length; i++) {
				if (checked[i]) {
					queue.add(virus_arr[i]);
					count[virus_arr[i].y][virus_arr[i].x] = 1;
				}
			}
			int day = virus_BFS();
			if (no_zero())
				DAY = Math.min(day, DAY);
			queue.clear();
			return;
		}

		for (int i = start; i < virus_arr.length; i++) {
			if (!checked[i]) {
				checked[i] = true;
				comb(i + 1, select + 1, virus_arr, checked);
				checked[i] = false;
			}
		}
	}

	private static boolean no_zero() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && count[i][j] == 0) return false;
			}
		}
		return true;
	}

	private static int virus_BFS() {
		int day = 0;
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		while (!queue.isEmpty()) {
			Dot now = queue.poll();

			if (map[now.y][now.x] == 0)
				day = count[now.y][now.x] - 1;

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (map[ny][nx] == 1) continue;
				if (count[ny][nx] > 0) continue;
				count[ny][nx] = count[now.y][now.x] + 1;
				queue.add(new Dot(ny, nx));
			}
		}
		return day;
	}

	private static class Dot
	{
		int y, x;
		Dot (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
