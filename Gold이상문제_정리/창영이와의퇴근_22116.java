package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 창영이와의퇴근_22116 {
	static int N;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[][] board;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(d -> d.diff));
		pq.add(new Dot(0, 0, 0));
		dp[0][0] = 0;
		Dijkstra(pq);
	}

	private static void Dijkstra(PriorityQueue<Dot> pq) {
		while (!pq.isEmpty())
		{
			Dot now = pq.poll();

			if (now.y == N - 1 && now.x == N - 1) {
				System.out.println(now.diff);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;

				int diff = Math.abs(board[now.y][now.x] - board[ny][nx]);
				diff = Math.max(diff, now.diff);

				if (dp[ny][nx] > diff) {
					dp[ny][nx] = diff;
					pq.add(new Dot(ny, nx, diff));
				}
			}
		}
	}

	private static class Dot {
		int y, x, diff;

		public Dot(int y, int x, int diff) {
			this.y = y;
			this.x = x;
			this.diff = diff;
		}
	}
}
