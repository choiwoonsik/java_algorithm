package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 다리만들기2_17472 {
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Bridge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.len));
	static int N, M;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {-1, 1, 0, 0};
	static int[] p;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 육지 번호 매기기
		int n = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					visited[i][j] = true;
					dfs(i, j, n);
					n++;
				}
			}
		}

		// 다리 만들기
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < M; x++) {
				if (map[y][x] > 0) {
					// 육지라면 다리 만듬 - 오른쪽으로
					for (int dari = x + 1; dari < M; dari++) {
						if (map[y][dari] == map[y][x])
							break;
						else if (map[y][dari] > 0) {
							if (dari - x - 1 > 1)
								pq.add(new Bridge(map[y][x], map[y][dari], dari - x - 1));
							break;
						}
					}
					// 아래쪽으로
					for (int dari = y + 1; dari < N; dari++) {
						if (map[dari][x] == map[y][x])
							break;
						else if (map[dari][x] > 0) {
							if (dari - y - 1 > 1)
								pq.add(new Bridge(map[y][x], map[dari][x], dari - y - 1));
							break;
						}
					}
				}
			}
		}

		p = new int[n];
		for (int i = 1; i < n; i++)
			p[i] = i;

		int span = 0;
		//다리 놓기
		while (!pq.isEmpty())
		{
			Bridge now = pq.poll();

			if (find(now.start) != find(now.end)) {
				union(now.start, now.end);
				span += now.len;
			}
		}

		// 모두 연결 되있나
		int count = 0;
		for (int i = 1; i < n; i++) {
			if (p[i] == i) {
				count++;
				if (count > 1) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(span);
	}

	private static int find(int land) {
		if (p[land] == land) return land;
		return p[land] = find(p[land]);
	}

	private static void union(int s, int e)
	{
		s = find(s);
		e = find(e);
		if (s < e)
			p[p[e]] = p[s];
		else
			p[p[s]] = p[e];
	}

	private static void dfs(int i, int j, int n) {
		map[i][j] = n;

		for (int d = 0; d < 4; d++) {
			int ny = dy[d] + i;
			int nx = dx[d] + j;
			if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
				if (!visited[ny][nx] && map[ny][nx] == 1) {
					visited[ny][nx] = true;
					map[ny][nx] = n;
					dfs(ny, nx, n);
				}
			}
		}
	}

	private static class Bridge {
		int start;
		int end;
		int len;

		public Bridge(int start, int end, int len) {
			this.start = start;
			this.end = end;
			this.len = len;
		}
	}
}