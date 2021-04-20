package Gold이상문제_정리;

import java.io.*;
import java.util.*;
/*
5
B0011
B0000
B0000
11000
EEE00

7
0001000
0001000
B00100E
B00000E
B00100E
0001000
0001000
 */
public class 통나무옮기기_1938 {
	static int[][] map = new int[51][51];
	static boolean[][][] visited = new boolean[51][51][2];
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		Dot[] dots = new Dot[3];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			char[] s = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = s[j];
				if (s[j] == 'B')
					dots[cnt++] = new Dot(i, j);
			}
		}

		int dir;
		// 가로
		if (dots[0].y == dots[1].y) dir = 0;
		// 세로
		else dir = 1;
		BFS(new Tree(dots[0], dots[1], dots[2], 0, dir));
	}

	private static void BFS(Tree tree) {
		Queue<Tree> queue = new LinkedList<>();
		queue.add(tree);
		visited[tree.mid.y][tree.mid.x][tree.dir] = true;

		while (!queue.isEmpty())
		{
			Tree now_tree = queue.poll();
			int move = now_tree.move;

			if (map[now_tree.left.y][now_tree.left.x] == 'E' &&
				map[now_tree.right.y][now_tree.right.x] == 'E' &&
				map[now_tree.mid.y][now_tree.mid.x] == 'E') {
				System.out.println(now_tree.move);
				return;
			}

			// 상하좌우
			for (int d = 0; d < 4; d++) {
				int left_y = now_tree.left.y + dy[d];
				int left_x = now_tree.left.x + dx[d];
				int mid_y = now_tree.mid.y + dy[d];
				int mid_x = now_tree.mid.x + dx[d];
				int right_y = now_tree.right.y + dy[d];
				int right_x = now_tree.right.x + dx[d];
				// 가로인 경우
				if (out_dir(left_y, left_x) || out_dir(mid_y, mid_x) || out_dir(right_y, right_x)) continue;
				if (visited[mid_y][mid_x][now_tree.dir]) continue;
				visited[mid_y][mid_x][now_tree.dir] = true;
				queue.add(new Tree(new Dot(left_y, left_x), new Dot(mid_y, mid_x), new Dot(right_y, right_x), move + 1, now_tree.dir));
			}
			if (turn(now_tree)) {
				// horizon
				if (now_tree.dir == 0 && !visited[now_tree.mid.y][now_tree.mid.x][1]) {
					int ly = now_tree.left.y - 1;
					int lx = now_tree.left.x + 1;
					int ry = now_tree.right.y + 1;
					int rx = now_tree.right.x - 1;
					visited[now_tree.mid.y][now_tree.mid.x][1] = true;
					queue.add(new Tree(new Dot(ly, lx), now_tree.mid, new Dot(ry, rx), move + 1, 1));
				}
				// vertical
				else if (now_tree.dir == 1 && !visited[now_tree.mid.y][now_tree.mid.x][0]) {
					int ly = now_tree.left.y + 1;
					int lx = now_tree.left.x - 1;
					int ry = now_tree.right.y - 1;
					int rx = now_tree.right.x + 1;
					visited[now_tree.mid.y][now_tree.mid.x][0] = true;
					queue.add(new Tree(new Dot(ly, lx), now_tree.mid, new Dot(ry, rx), move + 1, 0));
				}
			}
		}
		System.out.println(0);
	}

	private static boolean turn(Tree tree) {
		int y = tree.mid.y;
		int x = tree.mid.x;
		for (int i = y - 1; i <= y + 1; i++) {
			for (int j = x - 1; j <= x + 1; j++) {
				if (i < 0 || i >= N || j < 0 || j >= N) return false;
				if (map[i][j] == '1') return false;
			}
		}
		return true;
	}

	private static boolean out_dir(int y, int x) {
		return y < 0 || y >= N || x < 0 || x >= N || map[y][x] == '1';
	}

	private static class Tree
	{
		Dot left, mid, right;
		int move, dir;

		public Tree(Dot left, Dot mid, Dot right, int move, int dir) {
			this.left = left;
			this.mid = mid;
			this.right = right;
			this.move = move;
			this.dir = dir;
		}
	}
	private static class Dot {
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
