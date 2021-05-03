package Gold이상문제_정리;

/*
7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1

1 ≤ N, M ≤ 10
3 ≤ N×M ≤ 100
2 ≤ 섬의 개수 ≤ 6
 */

import java.util.*;
import java.io.*;

public class 다리만들기2_2_17472 {
	static int[] land_parent = new int[7];
	static int N, M, Land_number;
	static int[][] map = new int[10][10];
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static PriorityQueue<Bridge> bridge_pq = new PriorityQueue<>(Comparator.comparingInt(s->s.len));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		island_numbering();
		make_bridge();
		int answer = calc_bridge();

		int parent = find(land_parent[1]);
		for (int i = 2; i <= Land_number; i++) {
			if (find(land_parent[i]) != parent) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(answer);
	}

	private static int calc_bridge() {
		int all_len = 0;

		while (!bridge_pq.isEmpty())
		{
			Bridge bridge = bridge_pq.poll();

			int start = find(bridge.start);
			int end = find(bridge.end);

			if (start != end) {
				union(start, end);
				all_len += bridge.len;
			}
		}
		return all_len;
	}

	private static void union(int p1, int p2) {
		land_parent[p2] = land_parent[p1];
	}

	private static int find(int i) {
		if (land_parent[i] != i)
			return land_parent[i] = find(land_parent[i]);
		return land_parent[i];
	}

	private static void make_bridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				check_bridge(i, j);
			}
		}
	}

	private static void check_bridge(int i, int j) {
		int START = map[i][j];
		for (int d = 0; d < 4; d++) {
			int LEN = 0;
			boolean flag = true;
			int ny = i + dy[d];
			int nx = j + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == START) continue;

			while (map[ny][nx] == 0) {
				ny += dy[d];
				nx += dx[d];
				LEN++;
				if (ny < 0 || nx < 0 || ny >= N || nx >= M) {
					flag = false;
					break;
				}
				if (map[ny][nx] == START) {
					flag = false;
					break;
				}
			}
			if (LEN <= 1) continue;
			if (!flag) continue;
			bridge_pq.add(new Bridge(START, map[ny][nx], LEN));
		}
	}

	private static void island_numbering() {
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1){
					Land_number++;
					visited[i][j] = true;
					numbering_BFS(i, j, visited);
				}
			}
		}
		for (int i = 1; i <= Land_number; i++)
			land_parent[i] = i;
	}

	private static void numbering_BFS(int i, int j, boolean[][] visited) {
		Queue<Dot> queue = new LinkedList<>();
		map[i][j] = Land_number;
		queue.add(new Dot(i, j));

		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;

				if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
				if (visited[ny][nx]) continue;
				if (map[ny][nx] == 0) continue;
				map[ny][nx] = Land_number;
				visited[ny][nx] = true;
				queue.add(new Dot(ny, nx));
			}
		}
	}

	private static class Dot
	{
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	private static class Bridge
	{
		int start, end, len;

		public Bridge(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}
	}
}
