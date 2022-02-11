package 투포인터;

import java.io.*;
import java.util.*;

public class 집배원한상덕_2842 {
	static int N;
	static int count;
	static Dot start;
	static int right;
	static int left;
	static int tired = 987654321;
	static char[][] map;
	static int[][] mapHeight;
	static int[] height;
	static boolean[][] visited;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		mapHeight = new int[N][N];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				char c = line.charAt(j);
				map[i][j] = c;
			}
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mapHeight[i][j] = Integer.parseInt(st.nextToken());
				set.add(mapHeight[i][j]);
				if (map[i][j] == 'P') {
					start = new Dot(i, j, mapHeight[i][j]);
				} else if (map[i][j] == 'K') {
					count++;
				}
			}
		}

		int tmp = 0;
		height = new int[set.size()];
		for (Integer h : set) {
			height[tmp++] = h;
		}
		Arrays.sort(height);

		left = 0;
		right = 0;
		twoPointer();
		System.out.println(tired);
	}

	private static void twoPointer() {
		while (left <= right && right < height.length) {
			if (bfs()) {
				tired = Math.min(height[right] - height[left], tired);
				left++;
			} else {
				right++;
			}
		}
	}

	private static boolean bfs() {
		int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};
		int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};
		boolean success = false;
		int kCount = 0;
		visited = new boolean[N][N];
		visited[start.y][start.x] = true;
		Queue<Dot> queue = new LinkedList<>();

		if (mapHeight[start.y][start.x] >= height[left] && mapHeight[start.y][start.x] <= height[right]) {
			queue.add(start);
		}

		while(!queue.isEmpty()) {
			Dot cur = queue.poll();

			if (kCount == count) {
				success = true;
				break;
			}

			for (int d = 0; d < 8; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if (visited[ny][nx]) continue;
				if (mapHeight[ny][nx] >= height[left] && mapHeight[ny][nx] <= height[right]) {
					if (map[ny][nx] == 'K') kCount++;
					visited[ny][nx] = true;
					queue.add(new Dot(ny, nx, mapHeight[ny][nx]));
				}
			}
		}

		return success;
	}

	private static class Dot {
		int y;
		int x;
		int h;

		public Dot(int y, int x, int h) {
			this.y = y;
			this.x = x;
			this.h = h;
		}
	}
}
