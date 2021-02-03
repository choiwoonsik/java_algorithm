package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 말이되고싶은원숭이_1600 {
	static int K, W, H, Min;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<Dot> que = new LinkedList<>();
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] horsey = {2, 2, -2, -2, 1, -1, 1, -1};
	static int[] horsex = {1, -1, 1, -1, 2, 2, -2, -2};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		Min = 987654321;

		map = new int[H][W];
		visited = new boolean[H][W][K+1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		que.add(new Dot(0, 0, 0, 0));
		visited[0][0][0] = true;
		while (!que.isEmpty())
		{
			Dot now = que.poll();

			//System.out.println(now.y +", " + now.x+ "/ move: " + now.move + "/ use: " + now.use);
			if (now.y == H - 1 && now.x == W - 1) {
				Min = Math.min(Min, now.move);
			}

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
				if (visited[ny][nx][now.use]) continue;
				if (map[ny][nx] == 1) continue;

				visited[ny][nx][now.use] = true;
				que.add(new Dot(ny, nx, now.move + 1, now.use));
			}

			for (int hd = 0; hd < 8; hd++) {
				int hny = horsey[hd] + now.y;
				int hnx = horsex[hd] + now.x;
				if (hny < 0 || hny >= H || hnx < 0 || hnx >= W) continue;
				if (visited[hny][hnx][now.use]) continue;
				if (map[hny][hnx] == 1) continue;

				if (now.use < K && !visited[hny][hnx][now.use + 1]) {
					visited[hny][hnx][now.use + 1] = true;
					que.add(new Dot(hny, hnx, now.move + 1, now.use + 1));
				}
			}
		}
		if (Min == 987654321)
			System.out.println(-1);
		else
			System.out.println(Min);
	}
	private static class Dot
	{
		int y, x, move, use;

		public Dot(int y, int x, int move, int use) {
			this.y = y;
			this.x = x;
			this.move = move;
			this.use = use;
		}
	}
}
