package 다익스트라;

import java.util.*;
import java.io.*;

public class 주유소_13308 {
	static int N;
	static int M;
	static long INF = Long.MAX_VALUE;
	static int[] oilCost;
	static long[][] dp; // [i번 노드][현재 노드까지의 기름 최소값] = 비용
	static ArrayList<Vertex>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		oilCost = new int[N + 1];
		adj = new ArrayList[N + 1];
		dp = new long[N + 1][2501];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			oilCost[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i], -1);
			adj[i] = new ArrayList<>();
		}

		// edge
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[from].add(new Vertex(to, dist));
			adj[to].add(new Vertex(from, dist));
		}

		dijkstra();
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(N -> N.cost));
		pq.add(new Node(0, 1, oilCost[1]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dp[cur.node][cur.oilCost] != -1) continue;

			dp[cur.node][cur.oilCost] = cur.cost;

			if (cur.node == N) {
				System.out.println(cur.cost);
				break;
			}

			for (Vertex next : adj[cur.node]) {
				int nextCity = next.node;
				long nextCost = cur.cost + (long) next.dist * cur.oilCost;
				int minOil = Math.min(cur.oilCost, oilCost[nextCity]);

				if (dp[nextCity][minOil] == -1) {
					pq.add(new Node(nextCost, nextCity, minOil));
				}
			}
		}
	}

	private static class Vertex {
		int node;
		int dist;

		public Vertex(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}

	private static class Node {
		long cost;
		int node;
		int oilCost;

		public Node(long cost, int node, int oilCost) {
			this.cost = cost;
			this.node = node;
			this.oilCost = oilCost;
		}
	}
}
