package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 집구하기_13911 {
	static final int INF = Integer.MAX_VALUE;
	static final int MACDONALDS = 0;
	static final int STARBUCKS = 1;
	static int V, E, M, S;
	static int[] limit = new int[2];
	static int[][] dist = new int[2][10100];
	static HashSet<Integer> noHouseSet = new HashSet<>();
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		for (int i = 0; i < 2; i++) {
			Arrays.fill(dist[i], INF);
		}

		for (int i = 0; i < V + 3; i++) {
			adj.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj.get(u).add(new Edge(v, w));
			adj.get(v).add(new Edge(u, w));
		}

		// MACDONALDS adjcent list
		st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		limit[MACDONALDS] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int src = Integer.parseInt(st.nextToken());
			noHouseSet.add(src);

			adj.get(V+1).add(new Edge(src, 0));
		}

		// STARBUCKS adjcent list
		st = new StringTokenizer(br.readLine());

		S = Integer.parseInt(st.nextToken());
		limit[STARBUCKS] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < S; i++) {
			int src = Integer.parseInt(st.nextToken());
			noHouseSet.add(src);

			adj.get(V+2).add(new Edge(src, 0));
		}

		int Min = INF;
		dijkstra(V+1, MACDONALDS);
		dijkstra(V+2, STARBUCKS);

		for (int i = 1; i < V + 1; i++) {
			if (noHouseSet.contains(i)) continue;
			if (dist[MACDONALDS][i] <= limit[MACDONALDS] && dist[STARBUCKS][i] <= limit[STARBUCKS]) {
				Min = Math.min(Min, dist[MACDONALDS][i] + dist[STARBUCKS][i]);
			}
		}
		System.out.println(Min >= INF ? -1 : Min);
	}

	private static void dijkstra(int root, int index) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

		pq.add(new Node(root, 0));
		dist[index][root] = 0;

		while (!pq.isEmpty())
		{
			Node n = pq.poll();
			int now_node = n.node;
			int now_dist = n.dist;

			if (dist[index][now_node] < now_dist) continue;
			for (Edge next : adj.get(now_node))
			{
				int next_node = next.next;
				int next_dist = next.w;
				if (dist[index][next_node] > dist[index][now_node] + next_dist)
				{
					dist[index][next_node] = dist[index][now_node] + next_dist;
					pq.add(new Node(next_node, dist[index][next_node]));
				}
			}
		}
	}

	private static class Edge
	{
		int next, w;

		public Edge(int next, int w) {
			this.next = next;
			this.w = w;
		}
	}

	private static class Node
	{
		int node;
		int dist;

		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
}
