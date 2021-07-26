package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 백도어_17396 {
	static int N, M;
	static boolean[] detected;
	static long[] dp;
	static ArrayList<Node>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		detected = new boolean[N];
		adj = new ArrayList[N];
		dp = new long[N];

		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();

		Arrays.fill(dp, Long.MAX_VALUE);
		dp[0] = 0;

		input = br.readLine().split(" ");
		for (int i = 0; i < input.length; i++)
			detected[i] = input[i].equals("1");
		detected[N-1] = false;

		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int u = Integer.parseInt(input[0]);
			int v = Integer.parseInt(input[1]);
			int t = Integer.parseInt(input[2]);
			adj[u].add(new Node(v, t));
			adj[v].add(new Node(u, t));
		}

		dijkstra();

		if (dp[N-1] == Long.MAX_VALUE) System.out.println(-1);
		else System.out.println(dp[N-1]);
	}

	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.t));
		pq.add(new Node(0, 0));

		while (!pq.isEmpty())
		{
			Node now = pq.poll();

			if (dp[now.n] < now.t) continue;

			for (Node next : adj[now.n]) {
				if (detected[next.n]) continue;
				if (dp[next.n] > dp[now.n] + next.t) {
					dp[next.n] = dp[now.n] + next.t;
					pq.add(new Node(next.n, dp[next.n]));
				}
			}
		}
	}

	private static class Node
	{
		int n;
		long t;

		public Node(int n, long t) {
			this.n = n;
			this.t = t;
		}
	}
}
