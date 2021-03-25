package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
3 3
1 1 0
1 1 1
1 0 1
1 1 1
 */
public class 로봇청소기_14503 {
	static int H, W;
	static int r, c, d, clean_cnt;
	static int[][] map;
	static boolean[][] visited;
	// 북, 서, 남, 동
	static int[] dy = {-1, 0, 1, 0};
	static int[] dx = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if (d == 1) d = 3;
		else if (d == 3) d = 1;
		clean(r, c, d);
		System.out.println(clean_cnt);
	}

	private static void clean(int r, int c, int d) {
		if (!visited[r][c])
		{
			visited[r][c] = true;
			clean_cnt++;
			clean(r, c, d);
		} else {
			boolean flag = false;
			for (int nd = d + 1; nd <= d + 4; nd++) {
				int ny = r + dy[nd % 4];
				int nx = c + dx[nd % 4];
				if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
				if (!visited[ny][nx] && map[ny][nx] != 1) {
					flag = true;
					clean(ny, nx, nd % 4);
					break;
				}
			}
			// back
			if (!flag) {
				int by = r - dy[d];
				int bx = c - dx[d];
				if (map[by][bx] != 1)
					clean(by, bx, d);
			}
		}
	}
}
