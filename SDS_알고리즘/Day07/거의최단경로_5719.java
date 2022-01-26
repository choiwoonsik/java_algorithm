package SDS_알고리즘.Day07;

import java.util.*;
import java.io.*;
/*
7 9
0 6
0 1 1
0 2 1
0 3 2
0 4 3
1 5 2
2 6 4
3 6 2
4 6 4
5 6 1
4 6
0 2
0 1 1
1 2 1
1 3 1
3 2 1
2 0 3
3 0 2
6 8
0 1
0 1 1
0 2 2
0 3 3
2 5 3
3 4 2
4 1 1
5 1 1
3 0 1
0 0
 */
public class 거의최단경로_5719 {
	static int V;
	static int E;
	static int START;
	static int END;
	static int[] dp;
	static int INF = 987654321;
	static boolean[][] isBlocked;
	static StringBuilder answer = new StringBuilder();
	static ArrayList<Edge>[] adj;
	static ArrayList<Integer>[] reverseTrace;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			dp = new int[V];
			isBlocked = new boolean[V][V];

			if (V == 0 && E == 0) break;

			st = new StringTokenizer(br.readLine());
			START = Integer.parseInt(st.nextToken());
			END = Integer.parseInt(st.nextToken());

			reverseTrace = new ArrayList[V];
			adj = new ArrayList[V];
			for (int i = 0; i < V; i++) {
				adj[i] = new ArrayList<>();
				reverseTrace[i] = new ArrayList<>();
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adj[u].add(new Edge(v, c));
			}

			// 최초 다익스트라
			dijkstra();

			// 지우기
			eraseEdge(END);

			// 거의 최단거리 다익스트라
			dijkstra();

			if (dp[END] == INF) answer.append("-1");
			else answer.append(dp[END]);
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void eraseEdge(int node) {
		if (node == START)
			return;

		for (Integer next : reverseTrace[node]) {
			if (!isBlocked[next][node]) {
				isBlocked[next][node] = true;
				eraseEdge(next);
			}
		}
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(E -> E.cost));
		Arrays.fill(dp, INF);
		dp[START] = 0;
		pq.add(new Edge(START, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.cost > dp[cur.node]) continue;

			for (Edge next : adj[cur.node]) {

				if (isBlocked[cur.node][next.node]) continue;

				if (dp[next.node] > dp[cur.node] + next.cost) {
					dp[next.node] = dp[cur.node] + next.cost;
					reverseTrace[next.node].clear();
					reverseTrace[next.node].add(cur.node);
					pq.add(new Edge(next.node, dp[next.node]));
				} else if (dp[next.node] == dp[cur.node] + next.cost) {
					reverseTrace[next.node].add(cur.node);
				}
			}
		}
	}

	private static class Edge {
		int node;
		int cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
