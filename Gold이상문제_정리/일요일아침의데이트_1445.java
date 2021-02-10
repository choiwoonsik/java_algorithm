package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 일요일아침의데이트_1445 {
	static int N, M;
	static Dot start;
	static boolean[][] visited;
	static char[][] map;
	static pair[][] count_map;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		// N, M, 시작 S, 도착 F, 쓰레기 g, ㄱㅊ은곳 .
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S')
					start = new Dot(i, j, 0, 0);
			}
		}
		find_road();
	}

	private static void find_road() {
		PriorityQueue<Dot> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.isG == o2.isG)
				return o1.isAdjG - o2.isAdjG;
			return o1.isG - o2.isG;
		});

		pq.add(start);
		visited[start.i][start.j] = true;
		while (!pq.isEmpty())
		{
			Dot now = pq.poll();

			for (int d = 0; d < 4; d++)
			{
				int ny = now.i + dy[d];
				int nx = now.j + dx[d];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
				if (visited[ny][nx]) continue;

				visited[ny][nx] = true;
				//다음자리가 꽃
				if (map[ny][nx] == 'F') {
					System.out.println(now.isG +" "+now.isAdjG);
					break;
				}
				// 다음자리가 쓰레기
				else if (map[ny][nx] == 'g') {
					pq.add(new Dot(ny, nx, now.isG + 1, now.isAdjG));
				}
				else {
					// 다음자리가 쓰레기 옆
					boolean isAdj = false;
					for (int gd = 0; gd < 4; gd++) {
						int nny = ny + dy[gd];
						int nnx = nx + dx[gd];
						if (nny < 0 || nny >= N || nnx < 0 || nnx >= M) continue;
						if (map[nny][nnx] == 'g') {
							isAdj = true;
							pq.add(new Dot(ny, nx, now.isG, now.isAdjG + 1));
							break;
						}
					}
					// 쓰레기없는 자리
					if (!isAdj)
						pq.add(new Dot(ny, nx, now.isG, now.isAdjG));
				}
//				System.out.println();
//				for (int yy = 0; yy < N; yy++) {
//					for (int xx = 0; xx < M; xx++) {
//						System.out.print(count_map[yy][xx].garbage+" " +count_map[yy][xx].adj_garbage+" | ");
//					}
//					System.out.println();
//				}
			}
		}
	}

	private static class Dot
	{
		int i, j;
		int isG;
		int isAdjG;

		public Dot(int i, int j, int isG, int isAdjG) {
			this.i = i;
			this.j = j;
			this.isG = isG;
			this.isAdjG = isAdjG;
		}
	}
	private static class pair
	{
		int garbage, adj_garbage;

		public pair(int garbage, int adj_garbage) {
			this.garbage = garbage;
			this.adj_garbage = adj_garbage;
		}
	}
}
