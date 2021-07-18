package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 다리만들기_2146 {
	static int N, MIN_DIST = Integer.MAX_VALUE;
	static int[][] board;
	static boolean[][] visited;
	static boolean[] island;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++)
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int indexing = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && board[i][j] == 1)
					DFS(new Dot(i, j), ++indexing);
			}
		}

		island = new boolean[indexing+1];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!island[board[i][j]] && board[i][j] >= 1) {
					island[board[i][j]] = true;
					findMinDistIsland(new Dot(i, j), board[i][j]);
					visited = new boolean[N][N];
				}
			}
		}
		System.out.println(MIN_DIST);
	}

	private static void findMinDistIsland(Dot dot, int nowIsland) {
		PriorityQueue<Dot> queue = new PriorityQueue<>(Comparator.comparingInt(d -> d.d));
		visited[dot.i][dot.j] = true;
		queue.add(dot);

		while (!queue.isEmpty()) {
			Dot now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = now.i + dy[d];
				int nx = now.j + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if (visited[ny][nx]) continue;
				if (now.d >= MIN_DIST) continue;
				visited[ny][nx] = true;

				if (board[ny][nx] == nowIsland) queue.add(new Dot(ny, nx, 0));
				else if (board[ny][nx] == 0) queue.add(new Dot(ny, nx, now.d + 1));
				else MIN_DIST = now.d;
			}
		}
	}

	private static void DFS(Dot now, int idx) {
		if (visited[now.i][now.j]) return;
		if (board[now.i][now.j] == 0) return;

		visited[now.i][now.j] = true;
		board[now.i][now.j] = idx;

		for (int d = 0; d < 4; d++) {
			int ny = now.i + dy[d];
			int nx = now.j + dx[d];
			if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
			DFS(new Dot(ny, nx), idx);
		}
	}

	private static class Dot {
		int i, j, d = 0;
		public Dot(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public Dot(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
}
