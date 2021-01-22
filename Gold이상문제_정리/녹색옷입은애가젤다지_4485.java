package Gold이상문제_정리;

import java.util.*;
import java.io.*;

// 숫가가 크면 안좋고 작으면 좋다 즉 우선순위 큐로 시작부터 넣어서 작은 값으로 dp 업데이트

public class 녹색옷입은애가젤다지_4485 {
	static StringBuilder str = new StringBuilder();
	static int T = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			solve(N, br);
		}
		System.out.println(str);
	}

	private static void solve(int N, BufferedReader br)  throws IOException {
		StringTokenizer st;
		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.pay));
		int[] dy = {0,0,-1,1};
		int[] dx = {-1,1,0,0};

		boolean[][] visited = new boolean[N][N];
		int[][] map = new int[N][N];
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j ++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		pq.add(new Dot(0, 0, map[0][0]));
		dp[0][0] = map[0][0];
		while (!pq.isEmpty())
		{
			Dot now = pq.poll();

			visited[now.y][now.x] = true;

			if (dp[now.y][now.x] < now.pay) continue;

			for(int d = 0; d < 4; d++)
			{
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (!visited[ny][nx])
					{
						if (dp[now.y][now.x] + map[ny][nx] < dp[ny][nx])
						{
							dp[ny][nx] = dp[now.y][now.x] + map[ny][nx];
							pq.add(new Dot(ny, nx, dp[ny][nx]));
						}
					}
				}
			}
		}
		str.append("Problem ").append(T++).append(": ").append(dp[N-1][N-1]).append("\n");
	}

	private static class Dot {
		int y;
		int x;
		int pay;

		public Dot(int y, int x, int pay) {
			this.y = y;
			this.x = x;
			this.pay = pay;
		}
	}
}
