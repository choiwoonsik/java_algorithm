package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 로봇청소기_4991 {
	static int dustCnt;
	static char[][] map;
	static boolean[][][] visited;
	static int W, H;
	static Queue<Dot> queue = new LinkedList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder str = new StringBuilder();
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		//먼지마다 개수를 따로 저장 -> 더 작은 길을 찾으면 바꿈
		while(true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			if (W == 0 && H == 0)
				break;
			make_clean();
		}
		System.out.print(str);
	}

	private static void make_clean() throws IOException {
		map = new char[H][W];
		queue.clear();
		dustCnt = 0;
		char numbering = '0';
		int[] dy = {-1, 1, 0, 0};
		int[] dx = {0, 0, -1, 1};

		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '*') {
					dustCnt++;
					map[i][j] = numbering++;
				}
				if (map[i][j] == 'o') queue.add(new Dot(i, j, 0, 0));
			}
		}

		visited = new boolean[H][W][1 << dustCnt];
		int maxMove = 987654321;
		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			if (map[now.y][now.x] >= '0' && map[now.y][now.x] <= '9') {
				now.dust |= (1 << (map[now.y][now.x] - '0'));
			}

			if (now.dust == ((1 << dustCnt) - 1)) {
				maxMove = now.move;
				break;
			}

			for (int d = 0; d < 4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
				if (map[ny][nx] == 'x') continue;
				if (visited[ny][nx][now.dust]) continue;

				visited[ny][nx][now.dust] = true;
				queue.add(new Dot(ny, nx, now.dust, now.move+1));
			}
		}
		if (maxMove == 987654321)
			str.append(-1).append("\n");
		else
			str.append(maxMove).append("\n");
	}

	private static class Dot
	{
		int y, x, dust, move;

		public Dot(int y, int x, int dust, int move) {
			this.y = y;
			this.x = x;
			this.dust = dust;
			this.move = move;
		}
	}
}
