package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 보물섬_2589 {
	static int H, W;
	static char[][] map;
	static int[][] visited;
	static int MAX;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,1,-1};
	public static void main(String[] args) throws IOException {
		// 섬하나마다 체크 -> 섬번호 부여 & 거리 측정?
		// bfs로 가장 늦게 체크된 지역 두개가 끝과 끝 -> x
		// 끝에 도달하면 그 좌표를 가지고 같은 육지만 bfs로 돌고 끝을 찾으면 거리 갱신 -> x
		// 육지를 만날 때 마다 육지에 대해서 bfs를 해서 가장 먼 dist를 갖는 지점을 찾는다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new char[H][W];
		for (int i = 0; i < H; i++) {
			String s = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		find();
		System.out.println(MAX);
	}

	private static void find() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 'L') {
					int dist = BFS(i, j);
					MAX = Math.max(dist, MAX);
				}
			}
		}
	}

	private static int BFS(int i, int j) {
		Queue<Dot> queue = new LinkedList<>();
		Dot dot = new Dot(i, j);
		queue.add(dot);
		int dist = 0;
		visited = new int[H][W];
		visited[i][j] = 1;

		while (!queue.isEmpty())
		{
			Dot now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int ny = now.i + dy[d];
				int nx = now.j + dx[d];
				if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
				if (map[ny][nx] != 'L') continue;
				if (visited[ny][nx] != 0) continue;

				visited[ny][nx] = visited[now.i][now.j] + 1;
				dist = Math.max(dist, visited[ny][nx]);
				queue.add(new Dot(ny, nx));
			}
		}

		return dist-1;
	}

	private static class Dot
	{
		int i, j;

		public Dot(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
