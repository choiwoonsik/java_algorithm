package 다익스트라;

import java.io.*;
import java.util.*;

public class 개코전쟁_2325 {
	static int N;
	static int M;
	static int INF = (int) 2e9;
	static int[] dp;
	static int[] roots;
	static int fromU, toV;
	static ArrayList<Node>[] adj;
	static ArrayList<Integer>[] reverseAdj;
	static StringBuilder trace = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		reverseAdj = new ArrayList[N + 1];
		dp = new int[N + 1];
		adj = new ArrayList[N + 1];

		Arrays.fill(dp, INF);
		for (int i = 0; i < N + 1; i++) {
			reverseAdj[i] = new ArrayList<>();
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			adj[from].add(new Node(to, dist));
			adj[to].add(new Node(from, dist));
		}

		dijkstra();
		traceRevert(N);

		String t = trace.toString();
		st = new StringTokenizer(t, " ");

		int counts = st.countTokens();
		roots = new int[counts];
		for (int i = 0; i < counts; i++) {
			roots[i] = Integer.parseInt(st.nextToken());
		}

		int max = 0;
		for (int i = 0; i < roots.length - 1; i++) {
			int nodeU = roots[i];
			int nodeV = roots[i + 1];

			fromU = nodeU;
			toV = nodeV;
			dijkstra();
			max = Math.max(dp[N], max);
		}

		System.out.println(max);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(N -> N.dist));
		Arrays.fill(dp, INF);
		dp[1] = 0;

		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dp[cur.node] < cur.dist) continue;

			if (cur.node == N) {
				break;
			}

			for (Node next : adj[cur.node]) {
				if (cur.node == fromU && next.node == toV) continue;
				if (next.node == fromU && cur.node == toV) continue;
				int nextNode = next.node;
				int nextDist = next.dist;

				if (dp[nextNode] > dp[cur.node] + nextDist) {
					dp[nextNode] = dp[cur.node] + nextDist;
					pq.add(new Node(nextNode, dp[nextNode]));
					reverseAdj[nextNode].clear();
					reverseAdj[nextNode].add(cur.node);
				}
			}
		}
	}

	private static void traceRevert(int start) {
		trace.append(start).append(" ");
		if (start == 1) {
			return;
		}

		for (int next : reverseAdj[start]) {
			traceRevert(next);
		}
	}

	private static class Node {
		int node;
		int dist;

		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
}
