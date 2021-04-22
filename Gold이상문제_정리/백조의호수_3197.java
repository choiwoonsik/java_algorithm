package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
8 17
...XXXXXX..XX.XXX
....XXXXXXXXX.XXX
...XXXXXXXXXXXX..
..XXXXX.LXXXXXX..
.XXXXXX..XXXXXX..
XXXXXXX...XXXX...
..XXXXX...XXX....
....XXXXX.XXXL...
 */
public class 백조의호수_3197 {
	static int N, M, DAYS;
	static Queue<Dot> move_queue = new LinkedList<>();
	static Queue<Dot> water_queue = new LinkedList<>();
	static char[][] map = new char[1500][1500];
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0};
	static Dot[] swans = new Dot[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int idx = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'L') {
					map[i][j] = '.';
					swans[idx++] = new Dot(i, j);
				}
				if (map[i][j] == '.')
					water_queue.add(new Dot(i, j));
			}
		}

		solve();
	}

	private static void solve() {
		move_queue.add(swans[0]);

		while (true) {
			// 백조 탐색
			if (swan_BFS()) break;
			else DAYS++;

			// melt
			melt_BFS();
		}
		System.out.println(DAYS);
	}

	private static void print(char[][] map) {
		System.out.println("\n**CHECK**");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void melt_BFS() {
		int size = water_queue.size();

		while (size-- > 0)
		{
			Dot now = water_queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (map[ny][nx] != 'X') continue;
				map[ny][nx] = '.';
				water_queue.add(new Dot(ny, nx));
			}
		}
	}

	private static boolean swan_BFS() {
		Queue<Dot> next_queue = new LinkedList<>();

		while (!move_queue.isEmpty())
		{
			Dot now = move_queue.poll();

			boolean flag = false;
			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (ny == swans[1].y && nx == swans[1].x) return true;
				if (map[ny][nx] == 'O') continue;
				if (map[ny][nx] == 'X') {
					if (!flag) {
						next_queue.add(now);
						flag = true;
					}
					continue;
				}
				map[ny][nx] = 'O';
				move_queue.add(new Dot(ny, nx));
			}
		}
		move_queue = next_queue;
		return false;
	}

	private static class Dot {
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
