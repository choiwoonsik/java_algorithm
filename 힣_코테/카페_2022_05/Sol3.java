package 힣_코테.카페_2022_05;

import java.util.*;

public class Sol3 {
	public static void main(String[] args) {
		Solution sol = new Solution();

		int solution = sol.solution(
				2, 2,
				new int[][]{
						{1, 1, 1, 2},
						{1, 2, 2, 2},
						{2, 2, 2, 1}
				},
				1, 1, 4, 3
		);
		System.out.println(solution);
	}

	private static class Solution {
		static boolean[][][] noWall;
		static boolean[][] visited;
		static int R;
		static int C;
		static int eR;
		static int eC;
		static int up = 0, down = 1, left = 2, right = 3;

		public int solution(int rows, int columns, int[][] maze, int r1, int c1, int r2, int c2) {

			R = rows;
			C = columns;
			eR = (rows == 1) ? 2 : R * R;
			eC = (columns == 1) ? 2 : C * C;

			noWall = new boolean[4][R + 2][C + 2];

			if (R == 1 && C == 1) {
				noWall[right][1][1] = true;
				noWall[left][1][2] = true;

				noWall[down][1][1] = true;
				noWall[up][2][1] = true;

				noWall[up][2][2] = true;
				noWall[down][1][2] = true;

				noWall[left][2][2] = true;
				noWall[right][2][1] = true;
			}

			for (int[] mazeInfo : maze) {
				if (mazeInfo.length == 0) continue;
				int row1 = mazeInfo[0];
				int col1 = mazeInfo[1];
				int row2 = mazeInfo[2];
				int col2 = mazeInfo[3];

				int dir = translateDir(row1, col1, row2, col2);
				noWall[dir][row1][col1] = true;
				if (dir == up) noWall[down][row2][col2] = true;
				if (dir == down) noWall[up][row2][col2] = true;
				if (dir == left) noWall[right][row2][col2] = true;
				if (dir == right) noWall[left][row2][col2] = true;
			}

			return go(r1, c1, r2, c2);
		}

		private int go(int r1, int c1, int r2, int c2) {
			int[] dy = {-1, 1, 0, 0};
			int[] dx = {0, 0, 1, -1};
			PriorityQueue<Dot> queue = new PriorityQueue<>(Comparator.comparingInt(D -> D.d));
			visited = new boolean[eR + 1][eC + 1];

			visited[r1][c1] = true;
			queue.add(new Dot(r1, c1, 0));

			while (!queue.isEmpty()) {
				Dot cur = queue.poll();

				if (cur.y == r2 && cur.x == c2) {
					return cur.d;
				}

				int curRegionY = ((cur.y - 1) / R) + 1;
				int curRegionX = ((cur.x - 1) / C) + 1;

				for (int dir = 0; dir < 4; dir++) {

					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					int nd = cur.d + 1;

					if (ny < 1 || nx < 1 || ny > eR || nx > eC) continue;


					int nextRegionY = ((ny - 1) / R) + 1;
					int nextRegionX = ((nx - 1) / C) + 1;

					if (curRegionY == nextRegionY && curRegionX == nextRegionX) {
						// inner region check
						int curInY = cur.y <= R ? cur.y : cur.y - ((cur.y - 1) / R) * R;
						int curInX = cur.x <= C ? cur.x : cur.x - ((cur.x - 1) / C) * C;

						int innerY = ny <= R ? ny : ny - ((ny - 1) / R) * R;
						int innerX = nx <= C ? nx : nx - ((nx - 1) / C) * C;

						if (innerY < 1 || innerX < 1 || innerY > R || innerX > C) continue;

						int regionDir = translateDir(curInY, curInX, innerY, innerX);
						if (!noWall[regionDir][curInY][curInX]) continue;
					} else {
						// outer region check
						int regionDir = translateDir(curRegionY, curRegionX, nextRegionY, nextRegionX);
						if (!noWall[regionDir][curRegionY][curRegionX]) continue;
					}

					if (visited[ny][nx]) continue;
					visited[ny][nx] = true;
					queue.add(new Dot(ny, nx, nd));
				}
			}

			return 1;
		}

		private int translateDir(int row1, int col1, int row2, int col2) {
			if (row1 == row2) {
				if (col1 < col2) return right;
				if (col2 < col1) return left;
			}
			if (col1 == col2) {
				if (row1 < row2) return down;
				if (row2 < row1) return up;
			}

			return -1;
		}

		private static class Dot {
			int y;
			int x;
			int d;

			Dot(int y, int x, int d) {
				this.y = y;
				this.x = x;
				this.d = d;
			}
		}
	}
}

