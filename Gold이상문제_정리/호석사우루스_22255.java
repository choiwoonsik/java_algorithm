package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 호석사우루스_22255 {
	static int N, M, ans = 0;
	static boolean[][][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[][] board;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[4][3][N][M];

		String[] input = br.readLine().split(" ");
		int sy = Integer.parseInt(input[0])-1;
		int sx = Integer.parseInt(input[1])-1;
		int ey = Integer.parseInt(input[2])-1;
		int ex = Integer.parseInt(input[3])-1;
		Dot start = new Dot(1, sy, sx, 0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		board[ey][ex] = 400;
		BFS(start);
		if (ans == 0) System.out.println(-1);
		else System.out.println(ans);
	}

	private static void BFS(Dot start) {
		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(d -> d.d));
		pq.add(start);

		while (!pq.isEmpty())
		{
			Dot now = pq.poll();
			int move = now.m;

			int d = 0, ed = 4;
			if (move % 3 == 1) ed = 2;
			else if (move % 3 == 2) d = 2;

			for (; d < ed; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];

				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (board[ny][nx] == -1) continue;
				if (visited[d][move][ny][nx]) continue;
				if (board[ny][nx] == 400) {
					ans = now.d;
					return;
				}

				visited[d][move][ny][nx] = true;
				pq.add(new Dot((move+1) % 3, ny, nx, now.d + board[ny][nx]));
			}
		}
	}

	private static class Dot
	{
		int m, y, x, d;

		public Dot(int m, int y, int x, int d) {
			this.m = m;
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}
}
