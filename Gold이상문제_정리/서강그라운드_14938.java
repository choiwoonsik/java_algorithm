package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
5 5 4
5 7 8 2 3
1 4 5
5 2 4
3 2 3
1 2 3
 */

public class 서강그라운드_14938 {
	static final int INF = Integer.MAX_VALUE;
	static int N, M, R;
	static int[] dist;
	static int[] items;
	static ArrayList<Edge>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		items = new int[N+1];
		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			adj[s].add(new Edge(e, d));
			adj[e].add(new Edge(s, d));
		}

		int Max = 0;
		for (int i = 1; i <= N; i++) {
			Max = Math.max(Max, dijkstra(i));
		}

		System.out.println(Max);
	}

	private static int dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.dist));

		dist = new int[N+1];
		Arrays.fill(dist, INF);

		dist[start] = 0;
		pq.add(new Edge(start, 0));

		while (!pq.isEmpty())
		{
			Edge now = pq.poll();

			if (dist[now.to] < now.dist) continue;

			for (Edge next : adj[now.to])
			{
				if (dist[next.to] > dist[now.to] + next.dist)
				{
					dist[next.to] = dist[now.to] + next.dist;
					pq.add(new Edge(next.to, dist[next.to]));
				}
			}
		}

		int getCnt = 0;
		for (int i = 1; i < N + 1; i++) {
			if (dist[i] <= M) {
				getCnt += items[i];
			}
		}

		return getCnt;
	}

	private static class Edge {
		int to;
		int dist;
		public Edge (int to, int dist)
		{
			this.to = to;
			this.dist = dist;
		}
	}
}
