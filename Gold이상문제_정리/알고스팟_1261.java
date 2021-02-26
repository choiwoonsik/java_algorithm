package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 알고스팟_1261 {
	static int N, M;
	static int[][] map;
	static int[][] count_map;
	static int[] dy = {0,0,-1,1};
	static int[] dx = {-1,1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		count_map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			Arrays.fill(count_map[i], 987654321);
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		BFS();
		System.out.println(count_map[N-1][M-1]);
	}

	private static void BFS() {
		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(0, 0));
		count_map[0][0] = 0;

		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (count_map[ny][nx] <= count_map[now.y][now.x]) continue;
				if (map[ny][nx] == 0) {
					count_map[ny][nx] = count_map[now.y][now.x];
					queue.add(new Dot(ny, nx));
				} else {
					if (count_map[ny][nx] > count_map[now.y][now.x] + 1) {
						count_map[ny][nx] = count_map[now.y][now.x] + 1;
						queue.add(new Dot(ny, nx));
					}
				}
			}
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
