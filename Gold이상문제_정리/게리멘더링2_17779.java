package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 게리멘더링2_17779 {
	static int[][] map;
	static boolean[][] lined;
	static int[][] visited;
	static int N, Min = 40001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solve();
		System.out.println(Min);
	}

	private static void solve() {
		int D1, D2;
		for (D1 = 1; D1 < N; D1++) {
			for (D2 = 1; D2 < N; D2++) {

				for (int r = 1; r <= N; r++)
				{
					for (int c = 1; c <= N; c++)
					{
						if (r + D1 + D2 > N || c - D1 < 1 || c + D2 > N || D1 < 0 || D2 < 0) continue;
						visited = new int[N+1][N+1];
						lined = new boolean[N+1][N+1];
						find_part(r, c, D1, D2);
					}
				}
			}
		}
	}

	private static void find_part(int x, int y, int d1, int d2) {
		int part1, part2, part3, part4, part5;
		make_line(x, y, d1, 0);
		make_line(x, y, d2, 1);
		make_line(x+d1, y-d1, d2, 1);
		make_line(x+d2, y+d2, d1, 0);

		part1 = sum_to_right(1, x + d1 - 1, 1, y, 1);
		part2 = sum_to_left(1, x + d2, y + 1, N, 2);
		part3 = sum_to_right(x + d1, N, 1, y - d1 + d2 - 1, 3);
		part4 = sum_to_left(x + d2 + 1, N, y - d1 + d2, N, 4);
		part5 = make_part5(x, y);

		int min12 = Math.min(part1, part2);
		int min34 = Math.min(part3, part4);
		int max12 = Math.max(part1, part2);
		int max34 = Math.max(part3, part4);
		int min = Math.min(Math.min(min12, min34), part5);
		int max = Math.max(Math.max(max12, max34), part5);
		Min = Math.min(Min, max - min);
	}

	private static int make_part5(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{r, c});

		int sum = 0;
		boolean[][] vst = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++)
			Arrays.fill(vst[i], false);
		int[] dy = {1, -1, 0, 0};
		int[] dx = {0, 0, 1, -1};

		while (!queue.isEmpty())
		{
			int[] now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = now[0] + dy[d];
				int nx = now[1] + dx[d];

				if (ny <= 0 || ny > N || nx <= 0 || nx > N) continue;
				if (visited[ny][nx] > 0) continue;
				if (vst[ny][nx]) continue;
				sum += map[ny][nx];
				lined[ny][nx] = true;
				vst[ny][nx] = true;
				queue.add(new int[]{ny, nx});
			}
		}
		return sum;
	}

	private static void make_line(int x, int y, int d, int plus) {

		int r = x;
		int c = y;
		while(r <= x + d) {
			lined[r][c] = true;
			r++;
			if (plus == 1)
				c++;
			else
				c--;
		}
	}

	private static int sum_to_right(int s_r, int e_r, int s_c, int e_c, int part) {
		int s = 0;
		for (int i = s_r; i <= e_r; i++) {
			for (int j = s_c; j <= e_c; j++) {
				if (lined[i][j]) break;
				visited[i][j] = part;
				s += map[i][j];
			}
		}
		return s;
	}

	private static int sum_to_left(int s_r, int e_r, int s_c, int e_c, int part){
		int s = 0;
		for (int i = e_r; i >= s_r; i--) {
			for (int j = e_c; j >= s_c; j--) {
				if (lined[i][j]) break;
				visited[i][j] = part;
				s += map[i][j];
			}
		}
		return s;
	}
}
