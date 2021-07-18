package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 움직이는미로탈출_16954 {
	static final int N = 8;
	static char[][] miro = new char[N][N];
	static Queue<Dot> queue;
	static boolean isOver;
	static int time = 0;
	static int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1, 0};
	static int[] dx = {0, 0, -1, 1, -1, 1, -1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < N; i++) {
			miro[i] = br.readLine().toCharArray();
		}

		Dot player = new Dot(N-1, 0);
		queue = new LinkedList<>();
		queue.add(player);
		solution();

	}

	private static void solution() {
		boolean fail = false;

		while (!queue.isEmpty() && !isOver) {
			time++;
			fail = true;
			if (play()) {
				moveWall();
				if (isOver) {
					fail = false;
					break;
				}
			}
			else break;
		}
		System.out.println(fail ? 0 : 1);
	}

	private static void moveWall() {
		Queue<Dot> tmpQ = new LinkedList<>();
		for (Dot dot : queue) {
			dot.y -= 1;
			if (dot.y >= N - time) continue;
			if (miro[dot.y][dot.x] != '#') {
				if (dot.y == 0) isOver = true;
				tmpQ.add(dot);
			}
		}
		queue.clear();
		queue.addAll(tmpQ);
	}

	private static boolean play() {

		boolean[][] visited = new boolean[N][N];
		int size = queue.size();

		while (size-- > 0) {
			Dot now = queue.poll();

			for (int d = 0; d < dy.length; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (miro[ny][nx] == '#') continue;
				if (visited[ny][nx]) continue;
				visited[ny][nx] = true;
				if (ny == 0) {
					isOver = true;
					return true;
				}
				queue.add(new Dot(ny, nx));
			}
		}

		return !queue.isEmpty();
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
