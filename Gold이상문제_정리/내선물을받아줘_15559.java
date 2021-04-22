package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
3 4
SWWW
SEWN
EEEN
 */

public class 내선물을받아줘_15559 {
	static int[][] map;
	static int[][] visited;
	static int CYCLE, N, M, RESULT;
	static int[] dy = {-1, 1, 0, 0}; // N S E W
	static int[] dx = {0, 0, 1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == 'N')
					map[i][j] = 0;
				if (s.charAt(j) == 'S')
					map[i][j] = 1;
				if (s.charAt(j) == 'E')
					map[i][j] = 2;
				if (s.charAt(j) == 'W')
					map[i][j] = 3;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == 0)
				{
					visited[i][j] = ++CYCLE;
					BFS(i, j);
				}
			}
		}

		System.out.println(RESULT);
	}

	private static void BFS(int i, int j) {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(i, j));

		while (!queue.isEmpty()) {
			Dot now = queue.poll();
			int dir = map[now.y][now.x];
			int ny = now.y + dy[dir];
			int nx = now.x + dx[dir];

			if (visited[ny][nx] == 0) {
				visited[ny][nx] = CYCLE;
				queue.add(new Dot(ny, nx));
			} else if (visited[ny][nx] == CYCLE) {
				RESULT++;
			}
		}
	}

	private static class Dot
	{
		int y, x;

		public Dot (int y, int x)
		{
			this.y = y;
			this.x = x;
		}
	}
}
