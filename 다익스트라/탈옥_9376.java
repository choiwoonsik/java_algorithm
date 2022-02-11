package 다익스트라;

import java.io.*;
import java.util.*;

public class 탈옥_9376 {
	static int T;
	static int H, W;
	static int INF = 987654321;
	static char[][] map;
	static int[][][] dist;
	static boolean[][][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Dot[] thiefs = new Dot[3];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H + 2][W + 2];
			for (int i = 0; i < H + 2; i++) {
				Arrays.fill(map[i], '.');
			}

			visited = new boolean[3][H + 2][W + 2];
			dist = new int[3][H + 2][W + 2];

			for (int t = 0; t < 3; t++) {
				for (int i = 0; i < H + 2; i++) {
					Arrays.fill(dist[t][i], -1);
				}
			}

			int order = 1;
			thiefs[0] = new Dot(0, 0);
			for (int i = 1; i <= H; i++) {
				String line = br.readLine();

				for (int j = 0; j < W; j++) {
					int jdx = j + 1;
					map[i][jdx] = line.charAt(j);
					if (map[i][jdx] == '$') {
						thiefs[order++] = new Dot(i, jdx);
					}
				}
			}

			bw.write(testCase() + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int testCase() {
		bfs_01(thiefs[0], 0);
		bfs_01(thiefs[1], 1);
		bfs_01(thiefs[2], 2);

		int minSum = INF;
		for (int i = 0; i < H + 2; i++) {
			for (int j = 0; j < W + 2; j++) {
				if (map[i][j] == '*') continue;
				if (dist[0][i][j] == -1 && dist[1][i][j] ==-1 && dist[2][i][j] == -1) continue;

				int sum = dist[0][i][j] + dist[1][i][j] + dist[2][i][j];

				if (map[i][j] == '#') {
					sum -= 2;
				}
				minSum = Math.min(minSum, sum);
			}
		}
		return minSum;
	}

	private static void bfs_01(Dot thief, int type) {
		Deque<Dot> deque = new LinkedList<>();
		deque.addFirst(thief);
		visited[type][thief.y][thief.x] = true;
		dist[type][thief.y][thief.x] = 0;

		while (!deque.isEmpty()) {
			Dot cur = deque.pollFirst();

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + cur.y;
				int nx = dx[d] + cur.x;

				if (ny < 0 || nx < 0 || ny > H + 1 || nx > W + 1) continue;
				if (map[ny][nx] == '*') continue;
				if (visited[type][ny][nx]) continue;

				visited[type][ny][nx] = true;

				// 비용 0, 선순위 탐색
				if ((map[ny][nx] == '.' || map[ny][nx] == '$') && dist[type][ny][nx] == -1) {
					dist[type][ny][nx] = dist[type][cur.y][cur.x];
					deque.addFirst(new Dot(ny, nx));
				} else if (map[ny][nx] == '#') {
					// 비용 1, 후순위 탐색
					dist[type][ny][nx] = dist[type][cur.y][cur.x] + 1;
					deque.addLast(new Dot(ny, nx));
				}
			}
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
