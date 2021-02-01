package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자_1194 {
	static String[] map;
	static boolean[][][] visited;
	static int N, M;
	static Queue<Dot> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new String[N];
		visited = new boolean[N][M][1<<7]; // 열쇠 체크

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine();
			for (int j = 0; j < M; j++) {
				if (map[i].charAt(j) == '0') queue.add(new Dot(i, j, 0, 0));
			}
		}

		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};
		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			// 탈출구멍을 찾은 경우
			if (map[now.y].charAt(now.x) == '1') {
				System.out.println(now.move);
				return;
			}

			// 키를 주운 경우
			if (map[now.y].charAt(now.x) >= 'a' && map[now.y].charAt(now.x) <= 'f') {
				now.key |= (1 << map[now.y].charAt(now.x) - 'a');
			}

			// 탐색
			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (map[ny].charAt(nx) == '#') continue;
				if (visited[ny][nx][now.key]) continue;

				// 문이있는경우
				if (map[ny].charAt(nx) >= 'A' && map[ny].charAt(nx) <= 'F') {
					// 해당 키가 있다면
					if (((1 << map[ny].charAt(nx) - 'A') & now.key) == (1 << map[ny].charAt(nx) - 'A')) {
						visited[ny][nx][now.key] = true;
						queue.add(new Dot(ny, nx, now.move+1, now.key));
					}
				}else {
					// 문이없는 경우
					visited[ny][nx][now.key] = true;
					queue.add(new Dot(ny, nx, now.move+1, now.key));
				}
			}
		}
		System.out.println(-1);
	}
	private static class Dot
	{
		int y, x, move, key;

		public Dot(int y, int x, int move, int key) {
			this.y = y;
			this.x = x;
			this.move = move;
			this.key = key;
		}
	}
}
