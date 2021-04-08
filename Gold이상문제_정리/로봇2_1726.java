package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
5 6
0 0 0 0 0 0
0 1 1 0 1 0
0 1 0 0 0 0
0 0 1 1 1 0
1 0 0 0 0 0
4 2 3
2 4 1
 */

public class 로봇2_1726 {
	static int[] dy = {0, 0, 0, 1, -1};
	static int[] dx = {0, 1, -1, 0, 0};
	static int[][] map = new int[101][101];
	static boolean[][][] visited = new boolean[101][101][5];
	static int row, col;
	static Dot[] dot = new Dot[2];
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			dot[i] = new Dot(y, x, d, 0);
		}

		BFS();
	}

	private static void BFS() {

		Queue<Dot> queue = new LinkedList<>();
		visited[dot[0].y][dot[0].x][dot[0].dir] = true;
		queue.add(dot[0]);

		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			if (now.y == dot[1].y && now.x == dot[1].x && now.dir == dot[1].dir) {
				System.out.println(now.cnt);
				return;
			}

			// turn
			for (int d = 1; d <= 4; d++) {
				if (d == now.dir) continue;
				if (d == reverse(now.dir)) continue;
				if (visited[now.y][now.x][d]) continue;
				visited[now.y][now.x][d] = true;
				queue.add(new Dot(now.y, now.x, d, now.cnt+1));
			}

			// move
			for (int m = 1; m <= 3; m++) {
				int ny = now.y + dy[now.dir] * m;
				int nx = now.x + dx[now.dir] * m;
				if (ny < 1 || ny > row || nx < 1 || nx > col) continue;
				if (map[ny][nx] == 1) break;
				if (visited[ny][nx][now.dir]) continue;
				visited[now.y][now.x][now.dir] = true;
				queue.add(new Dot(ny, nx, now.dir, now.cnt + 1));
			}
		}
	}

	private static int reverse(int dir) {
		if (dir == 1) return 2;
		if (dir == 2) return 1;
		if (dir == 3) return 4;
		if (dir == 4) return 3;
		return 0;
	}

	private static class Dot
	{
		int y, x, dir, cnt;

		public Dot(int y, int x, int dir, int cnt) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}
