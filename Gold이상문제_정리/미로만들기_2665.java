package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 미로만들기_2665 {
	static int[][] miro;
	static boolean[][] visited;
	static int[][] dp;
	static int N;
	static int[] dy = {0, 0, -1, 1};
	static int[] dx = {1, -1, 0, 0};
	static PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.pay));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		miro = new int[N][N];
		visited = new boolean[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				miro[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);

		dp[0][0] = 0;
		pq.add(new Dot(0, 0, 0));
		while (!pq.isEmpty())
		{
			Dot now = pq.poll();

			visited[now.y][now.x] = true;

			if (dp[now.y][now.x] < now.pay) continue;

			for (int d = 0; d < 4; d++)
			{
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
					if (!visited[ny][nx])
					{
						// 뚫려있음
						if (miro[ny][nx] == 1) {
							dp[ny][nx] = dp[now.y][now.x];
							pq.add(new Dot(dp[ny][nx], ny, nx));
						}
						// 막혀있음
						else {
							if (dp[now.y][now.x] + 1 < dp[ny][nx]) {
								dp[ny][nx] = dp[now.y][now.x] + 1;
								pq.add(new Dot(dp[ny][nx], ny, nx));
							}
						}
					}
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}

	private static class Dot
	{
		int pay;
		int y;
		int x;

		public Dot(int pay, int y, int x) {
			this.pay = pay;
			this.y = y;
			this.x = x;
		}
	}
}
