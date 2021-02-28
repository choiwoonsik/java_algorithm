package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 특정한최단경로_1504 {
	static int N, E, A, B;
	static long[] distance;
	static ArrayList<Dot>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int edge = 0; edge < E; edge++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adj[from].add(new Dot(to, cost));
			adj[to].add(new Dot(from, cost));
		}

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		// 1 -> A, 1 -> B, A <-> B, B -> N, A -> N
		long cost_1_to_A = dijkstra(1, A);
		long cost_1_to_B = dijkstra(1, B);
		long cost_A_to_B = dijkstra(A, B);
		long cost_A_to_N = dijkstra(A, N);
		long cost_B_to_N = dijkstra(B, N);

		boolean cost_1_A_B_N = true;
		boolean cost_1_B_A_N = true;

		if (cost_1_to_A == Long.MAX_VALUE || cost_A_to_B == Long.MAX_VALUE || cost_B_to_N == Long.MAX_VALUE)
			cost_1_A_B_N = false;
		if (cost_1_to_B == Long.MAX_VALUE || cost_A_to_B == Long.MAX_VALUE || cost_A_to_N == Long.MAX_VALUE)
			cost_1_B_A_N = false;

		if (!cost_1_A_B_N && !cost_1_B_A_N) {
			System.out.println(-1);
		} else {
			System.out.println(Math.min(cost_1_to_A + cost_A_to_B + cost_B_to_N, cost_1_to_B + cost_A_to_B + cost_A_to_N));
		}
	}

	private static long dijkstra(int start, int destination)
	{
		distance = new long[N+1];
		Arrays.fill(distance, Long.MAX_VALUE);
		PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s.cost));
		pq.add(new Dot(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty())
		{
			Dot now = pq.poll();
			for (Dot next : adj[now.dest]) {
				if (distance[next.dest] > distance[now.dest] + next.cost) {
					distance[next.dest] = distance[now.dest] + next.cost;
					pq.add(next);
				}
			}
		}
		return distance[destination];
	}

	private static class Dot
	{
		int dest;
		long cost;
		public Dot(int dest, long cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
