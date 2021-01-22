package Gold이상문제_정리;

import java.io.*;
import java.util.*;

// 학생의 시작점 - > 도착점 -> 시작점의 총 소요시간을 계산하고 그중 가장 오래 걸리는 학생의 시간을 출력

public class 파티_1238 {
	static PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.time));
	static int[] dp;
	static ArrayList<Road>[] adj;
	static int N, M, X;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			adj[s].add(new Road(e, t));
		}

		int MAX_TIME = 0;
		for (int i = 1; i <= N; i++) {
			// 가는데 비용
			pq.clear();
			pq.add(new Road(i, 0));
			start(i);
			int go = dp[X];

			// 오는데 비용
			pq.clear();
			pq.add(new Road(X, 0));
			start(X);
			int back = dp[i];
			MAX_TIME = Math.max(MAX_TIME, go + back);
		}
		System.out.println(MAX_TIME);
	}

	private static void start(int start) {
		boolean[] visited = new boolean[N+1];
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;

		while (!pq.isEmpty())
		{
			Road now = pq.poll();

			visited[now.end] = true;

			if (dp[now.end] < now.time) continue;

			for (Road next : adj[now.end])
			{
				if (!visited[next.end] && dp[now.end] + next.time < dp[next.end])
				{
					dp[next.end] = dp[now.end] + next.time;
					pq.add(new Road(next.end, dp[next.end]));
				}
			}
		}
	}

	private static class Road
	{
		int end;
		int time;

		public Road(int end, int time) {
			this.end = end;
			this.time = time;
		}
	}
}
