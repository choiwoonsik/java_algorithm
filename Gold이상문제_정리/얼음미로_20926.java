package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
5 5
2E115
32411
11313
R42TH
124R6
 */

public class 얼음미로_20926 {
	static final int Hol = -1, Rock = 100, Exit = 200;
	static int W, H, Min = Integer.MAX_VALUE;
	static int[][] board;
	static Dot Me;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		W = Integer.parseInt(input[0]);
		H = Integer.parseInt(input[1]);

		board = new int[H][W];
		visited = new boolean[H][W];
		for (int i = 0; i < H; i++) {
			String l = br.readLine();
			for (int j = 0; j < W; j++) {
				char c = l.charAt(j);
				if (c >= '1' && c <= '9') board[i][j] = c - '0';
				else if (c == 'T') Me = new Dot(i, j, 0);
				else if (c == 'E') board[i][j] = Exit;
				else if (c == 'H') board[i][j] = Hol;
				else if (c == 'R') board[i][j] = Rock;
			}
		}

		BFS();
		if (Min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(Min);
	}

	private static void BFS() {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(Me);
		visited[Me.y][Me.x] = true;

		while (!queue.isEmpty()) {
			Dot now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = now.y;
				int nx = now.x;
				int time = 0;
				boolean flag = false;

				while (true) {
					ny += dy[d];
					nx += dx[d];

					if (isOut(ny, nx)) break;
					if (visited[ny][nx]) break;
					if (board[ny][nx] == Hol) break;
					if (board[ny][nx] == Rock) {
						flag = true;
						break;
					}
					if (board[ny][nx] == Exit) {
						Min = Math.min(Min, now.t + time);
						break;
					}
					time += board[ny][nx];
				}

				ny -= dy[d];
				nx -= dx[d];
				visited[ny][nx] = true;
				if (flag) {
					if (ny == now.y && nx == now.x) continue;
					queue.add(new Dot(ny, nx, now.t + time));
				}
			}
		}
	}

	private static boolean isOut(int ny, int nx) {
		return ny < 0 || nx < 0 || ny >= H || nx >= W;
	}

	private static class Dot
	{
		int y, x, t;

		public Dot(int y, int x, int t) {
			this.y = y;
			this.x = x;
			this.t = t;
		}
	}
}
