package Gold이상문제_정리;
/*
- 승객을 고를 때는 현재 위치에서 최단거리가 가장 짧은 승객
- 그중 행 번호가 가장 작은 승객을, 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객
- 한 승객을 목적지로 성공적으로 이동시키면, 그 승객을 태워 이동하면서 소모한 연료 양의 두 배가 충전

6 3 15
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
 */

import java.io.*;
import java.util.*;

public class 스타트택시_19238 {
	static int N, M, Fuel;
	static int[][] map = new int[20][20];
	static int[][] dist_map = new int[20][20];
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int minDist = 1000000;
	static HashMap<Integer, Dot> client_dest_map = new HashMap<>();
	static Queue<Dot> queue = new LinkedList<>();
	static Dot Taxi;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Fuel = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		Taxi = new Dot(y - 1, x - 1);
		int id = 2;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			map[sy - 1][sx - 1] = id;
			client_dest_map.put(id, new Dot(ey - 1, ex - 1));
			id++;
		}
		int answer = play();
		System.out.println(answer);
	}

	private static int play() {
		while (true)
		{
			Dot start = find_client_BFS();
			if (start == null) return -1;
			Taxi.y = start.y;
			Taxi.x = start.x;
			int client_id = start.id;
			int toClient = start.dist;
			Fuel -= toClient;
			if (Fuel < 0) return -1;

			int toDest;
			Dot dest = client_dest_map.get(client_id);
			toDest = go_Dest_BFS(start, dest);
			Taxi.y = dest.y;
			Taxi.x = dest.x;
			Fuel -= toDest;
			if (Fuel < 0) return -1;

			Fuel += toDest * 2;
			map[start.y][start.x] = 0;
			client_dest_map.remove(client_id);
			if (client_dest_map.size() == 0) return Fuel;
		}
	}

	private static int go_Dest_BFS(Dot start, Dot end) {
		queue.clear();
		queue.add(start);
		dist_map = new int[N][N];
		dist_map[start.y][start.x] = 1;
		minDist = 1000000;

		while (!queue.isEmpty())
		{
			Dot now = queue.poll();
			if (now.y == end.y && now.x == end.x) {
				minDist = dist_map[now.y][now.x] - 1;
				break;
			}
			for (int d = 0; d < 4; d++) {
				int ny = dy[d] + now.y;
				int nx = dx[d] + now.x;
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if (dist_map[ny][nx] > 0) continue;
				if (map[ny][nx] == 1) continue;
				dist_map[ny][nx] = dist_map[now.y][now.x] + 1;
				queue.add(new Dot(ny, nx));
			}
		}
		return minDist;
	}

	private static Dot find_client_BFS() {
		PriorityQueue<Dot> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.dist != o2.dist) return Integer.compare(o1.dist, o2.dist);
			else {
				if (o1.y != o2.y) return Integer.compare(o1.y, o2.y);
				else return Integer.compare(o1.x, o2.x);
			}
		});
		dist_map = new int[N][N];
		dist_map[Taxi.y][Taxi.x] = 1;
		queue.clear();
		queue.add(Taxi);
		int now_dist;
		while (!queue.isEmpty())
		{
			Dot now = queue.poll();
			if (map[now.y][now.x] >= 2) {
				now_dist = dist_map[now.y][now.x] - 1;
				pq.add(new Dot(now.y, now.x, now_dist, map[now.y][now.x]));
			}
			for (int d = 0; d < 4; d++) {
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
				if (dist_map[ny][nx] > 0) continue;
				if (map[ny][nx] == 1) continue;
				dist_map[ny][nx] = dist_map[now.y][now.x] + 1;
				queue.add(new Dot(ny, nx));
			}
		}

		return pq.poll();
	}

	private static class Dot {
		int y, x, dist, id;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public Dot(int y, int x, int dist, int id) {
			this.y = y;
			this.x = x;
			this.dist = dist;
			this.id = id;
		}
	}
}