package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 아맞다우산_17244 {
	static char[][] map = new char[50][50];
	static int M, N, Min, countX;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		countX = 0;
		int sy = 0, sx = 0;
		char k = '0';
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'X') {
					countX++;
					map[i][j] = k;
					k++;
				}
				else if (map[i][j] == 'S') {
					sy = i;
					sx = j;
				}
			}
		}
		// bit 로 방문 체크를 하기위해서 countX개의 모든 물품의 조합을 위해 (1 << countX)개의 크기를 만든다
		visited = new boolean[N][M][1 << 5];

		bfs(sy, sx, 0, 0);
		System.out.println(Min);
	}
	private static void bfs(int i, int j, int time, int key)
	{
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j, time, key));
		visited[i][j][key] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (map[now.i][now.j] == 'E' && ((now.key & ((1 << countX) - 1)) == (1 << countX) - 1)) {
				Min = now.time;
				break;
			}

			// 키를 찾은 경우 체크
			if (map[now.i][now.j] >= '0' && map[now.i][now.j] <= '4') {
				now.key |= (1 << (map[now.i][now.j] - '0'));
			}

			for (int d = 0; d < 4; d++) {
				int ny = now.i + dy[d];
				int nx = now.j + dx[d];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (!visited[ny][nx][now.key] && map[ny][nx] != '#') {
						visited[ny][nx][now.key] = true;
						queue.add(new Node(ny, nx, now.time + 1, now.key));
					}
				}
			}
		}
	}

	private static class Node
	{
		int i, j, time, key;

		public Node(int i, int j, int time, int key)
		{
			this.i = i;
			this.j = j;
			this.time = time;
			this.key = key;
		}
	}
}
