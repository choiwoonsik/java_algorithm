package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 도로포장_1162 {
	static int N, M, K;
	static boolean[][] visited;
	static ArrayList<City>[] adj;
	static long[][] dp; // N개의 노드들에 대해서 K번 포장하는 경우를 위한 dp배열
	static PriorityQueue<City> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.time));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		visited = new boolean[N][K+1];
		dp = new long[N][K+1];
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], Long.MAX_VALUE);
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
			adj[s].add(new City(e, t, 0));
			adj[e].add(new City(s, t, 0));
		}

		dp[0][0] = 0;
		pq.add(new City(0, 0, 0));
		solve();
		long ret = Long.MAX_VALUE;
		for (long time : dp[N-1])
			ret = Math.min(time, ret);
		System.out.println(ret);
	}

	private static void solve() {
		while (!pq.isEmpty())
		{
			City now = pq.poll();
			int wrapCnt = now.wrap;

			visited[now.end][wrapCnt] = true;

			if (dp[now.end][wrapCnt] < now.time) continue;

			dp[now.end][wrapCnt] = now.time; // 주어진 시간 비용
			for (City next : adj[now.end]) {
				// 하나의 다음 노드에 대해서 도로를 포장하는 경우 && 그냥 가는 경우 두개 다 계산 후 넣어준다

				if (!visited[next.end][wrapCnt]) {
					// 도로를 포장하는 경우
					if (wrapCnt < K && dp[now.end][wrapCnt] < dp[next.end][wrapCnt + 1]) {
						dp[next.end][wrapCnt + 1] = dp[now.end][wrapCnt];
						pq.add(new City(next.end, dp[next.end][wrapCnt + 1], wrapCnt + 1));
					}
					// 그냥 계산하는 경우
					if (dp[now.end][wrapCnt] + next.time < dp[next.end][wrapCnt]) {
						dp[next.end][wrapCnt] = dp[now.end][wrapCnt] + next.time;
						pq.add(new City(next.end, dp[next.end][wrapCnt], wrapCnt));
					}
				}
			}
		}
	}

	private static class City
	{
		int end;
		long time;
		int wrap;

		public City(int end, long time, int wrap) {
			this.end = end;
			this.time = time;
			this.wrap = wrap;
		}
	}
}
