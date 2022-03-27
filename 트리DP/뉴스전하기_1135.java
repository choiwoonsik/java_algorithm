package 트리DP;

import java.util.*;
import java.io.*;

/*
24
-1 0 0 1 1 1 2 2 3 3 4 4 5 5 6 7 7 8 12 13 14 16 16 16
 */
public class 뉴스전하기_1135 {
	static int N;
	static int[] dp;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		adj = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int boss = Integer.parseInt(st.nextToken());
			if (boss != -1) {
				adj[boss].add(i);
			}
		}

		dfs(0);
		System.out.println(dp[0]);
	}

	private static int dfs(int root) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int w = adj[root].size() - 1;
		dp[root] = 0;

		for (Integer child : adj[root]) {
			pq.add(dfs(child));
		}

		while (!pq.isEmpty()) {
			Integer childCost = pq.poll();
			dp[root] = Math.max(dp[root], childCost + w--);
		}

		return dp[root] + 1;
	}
}
