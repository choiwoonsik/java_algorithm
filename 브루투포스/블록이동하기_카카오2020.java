package 브루투포스;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 블록이동하기_카카오2020 {

	/*
	[[0, 0, 0, 1, 1],[0, 0, 0, 1, 0],[0, 1, 0, 1, 1],[1, 1, 0, 0, 1],[0, 0, 0, 0, 0]]

	{0, 0, 0, 0, 1, 0},
	{0, 0, 1, 1, 1, 0},
	{0, 1, 1, 1, 1, 0},
	{0, 1, 0, 0, 1, 0},
	{0, 0, 1, 0, 0, 0},
	{0, 0, 0, 0, 0, 0},

	 */
	public static void main(String[] args) {
		Solution sol = new Solution();
		int ret = sol.solution(new int[][]
				{
						{0, 0, 0, 0, 1, 0},
						{0, 0, 1, 1, 1, 0},
						{0, 1, 1, 1, 1, 0},
						{0, 1, 0, 0, 1, 0},
						{0, 0, 1, 0, 0, 0},
						{0, 0, 0, 0, 0, 0}
				});
		System.out.println(ret == 10);
	}

	private static class Solution {
		static int N;
		static int[][] MAP;
		static boolean[][][] visited;
		static int EMPTY = 0;
		static int BLOCK = 1;
		static int GARO = 0;
		static int SERO = 1;
		static int[] dy = new int[]{0, 0, -1, 1};
		static int[] dx = new int[]{1, -1, 0, 0};
		static PriorityQueue<Bot> pq = new PriorityQueue<>(Comparator.comparingInt(B -> B.count));

		private int solution(int[][] board) {
			N = board.length;
			MAP = new int[N + 1][N + 1];
			visited = new boolean[2][N + 1][N + 1];

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					MAP[i][j] = board[i - 1][j - 1];
				}
			}

			return bfs(new Bot(GARO, new Dot(1, 1), new Dot(1, 2), 0));
		}

		private int bfs(Bot bot) {
			pq.add(bot);

			while (!pq.isEmpty()) {
				Bot cur = pq.poll();

				if (cur.left.y < 1 || cur.left.y > N) continue;
				if (cur.left.x < 1 || cur.left.x > N) continue;
				if (cur.right.y < 1 || cur.right.y > N) continue;
				if (cur.right.x < 1 || cur.right.x > N) continue;


				if (MAP[cur.left.y][cur.left.x] == BLOCK || MAP[cur.right.y][cur.right.x] == BLOCK) {
					continue;
				}

				if (visited[cur.dir][cur.left.y][cur.left.x] && visited[cur.dir][cur.right.y][cur.right.x]) {
					continue;
				}

				if ((cur.left.y == N && cur.left.x == N) || (cur.right.y == N && cur.right.x == N)) {
					System.out.println(cur.count);
					return cur.count;
				}

				visited[cur.dir][cur.left.y][cur.left.x] = true;
				visited[cur.dir][cur.right.y][cur.right.x] = true;

				if (cur.dir == GARO) {
					garoUp(cur);
					garoDown(cur);
				}
				if (cur.dir == SERO) {
					seroLeft(cur);
					seroRight(cur);
				}

				for (int d = 0; d < 4; d++) {
					int ny1 = cur.left.y + dy[d];
					int nx1 = cur.left.x + dx[d];
					int ny2 = cur.right.y + dy[d];
					int nx2 = cur.right.x + dx[d];
					pq.add(new Bot(cur.dir, new Dot(ny1, nx1), new Dot(ny2, nx2), cur.count + 1));
				}
			}

			return 0;
		}

		private void seroLeft(Bot cur) {
			if (cur.left.x - 1 < 1) {
				return;
			}

			if (MAP[cur.left.y][cur.left.x - 1] == EMPTY && MAP[cur.right.y][cur.right.x - 1] == EMPTY) {
				// 왼쪽위
				pq.add(new Bot(GARO, new Dot(cur.left.y, cur.left.x - 1), cur.left, cur.count + 1));
				// 왼쪽아래
				pq.add(new Bot(GARO, new Dot(cur.right.y, cur.right.x - 1), cur.right, cur.count + 1));
			}
		}

		private void seroRight(Bot cur) {
			if (cur.left.x + 1 > N) {
				return;
			}

			if (MAP[cur.left.y][cur.left.x + 1] == EMPTY && MAP[cur.right.y][cur.right.x + 1] == EMPTY) {
				// 오른쪽위
				pq.add(new Bot(GARO, cur.left, new Dot(cur.left.y, cur.left.x + 1), cur.count + 1));
				// 오른쪽아래
				pq.add(new Bot(GARO, cur.right, new Dot(cur.right.y, cur.right.x + 1), cur.count + 1));
			}
		}

		private void garoUp(Bot cur) {

			if (cur.right.y - 1 < 1) {
				return;
			}

			if (MAP[cur.left.y - 1][cur.left.x] == EMPTY && MAP[cur.right.y - 1][cur.right.x] == EMPTY) {
				// 왼쪽 위
				pq.add(new Bot(SERO, new Dot(cur.left.y - 1, cur.left.x), cur.left, cur.count + 1));
				// 오른쪽 위
				pq.add(new Bot(SERO, new Dot(cur.right.y - 1, cur.right.x), cur.right, cur.count + 1));
			}
		}

		private void garoDown(Bot cur) {
			if (cur.left.y + 1 > N) {
				return;
			}

			if (MAP[cur.left.y + 1][cur.left.x] == EMPTY && MAP[cur.right.y + 1][cur.right.x] == EMPTY) {
				// 왼쪽 아래
				pq.add(new Bot(SERO, cur.left, new Dot(cur.left.y + 1, cur.left.x), cur.count + 1));
				// 오른쪽 아래
				pq.add(new Bot(SERO, cur.right, new Dot(cur.right.y + 1, cur.right.x), cur.count + 1));
			}
		}
	}

	private static class Bot {
		int dir;
		Dot left;
		Dot right;
		int count;

		public Bot(int dir, Dot left, Dot right, int count) {
			this.dir = dir;
			this.left = left;
			this.right = right;
			this.count = count;
		}
	}

	private static class Dot {
		int y;
		int x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
