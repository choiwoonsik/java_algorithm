package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 네트워크복구_2211 {
	static int[] origin_dp;
	static boolean[] isUpdated;
	static ArrayList<Dot> using_edge;
	static ArrayList<Dot>[] adj;
	static int N, E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++)
			adj[i] = new ArrayList<>();

		for (int edge = 0; edge < E; edge++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost =  Integer.parseInt(st.nextToken());
			adj[a].add(new Dot(b, cost));
			adj[b].add(new Dot(a, cost));
		}

		// 기존 네트워크
		origin_dijkstra(1);

		// 나중 네트워크
		using_edge = new ArrayList<>();
		after_dijkstra(1);

		StringBuilder str = new StringBuilder();
		str.append(using_edge.size()).append("\n");
		for (Dot usingEdge : using_edge){
			str.append(usingEdge.dest).append(" ").append(usingEdge.cost).append("\n");
		}
		System.out.print(str);
	}

	private static void after_dijkstra(int start) {
		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
		isUpdated = new boolean[N+1];
		pq.add(new Dot(start, 0));

		while(!pq.isEmpty()) {
			Dot now = pq.poll();

			for (Dot next : adj[now.dest]) {
				if (origin_dp[next.dest] >= origin_dp[now.dest] + next.cost) {
					if (origin_dp[next.dest] == origin_dp[now.dest] + next.cost && isUpdated[next.dest])
						continue;
					isUpdated[next.dest] = true;
					origin_dp[next.dest] = origin_dp[now.dest] + next.cost;
					using_edge.add(new Dot(now.dest, next.dest));
					pq.add(next);
				}
			}
		}
	}

	private static void origin_dijkstra(int start) {
		origin_dp = new int[N+1];
		Arrays.fill(origin_dp, Integer.MAX_VALUE);
		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
		pq.add(new Dot(start, 0));
		origin_dp[start] = 0;

		while(!pq.isEmpty())
		{
			Dot now = pq.poll();

			for (Dot next : adj[now.dest])
			{
				if (origin_dp[next.dest] > origin_dp[now.dest] + next.cost) {
					origin_dp[next.dest] = origin_dp[now.dest] + next.cost;
					pq.add(next);
				}
			}
		}
	}

	private static class Dot {
		int dest, cost;

		public Dot(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
