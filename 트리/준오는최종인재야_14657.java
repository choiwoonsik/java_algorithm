package 트리;

import java.util.*;
import java.io.*;
/*
6 4
1 2 4
2 3 2
3 4 7
3 5 3
6 5 4

7 4
1 2 4
2 3 2
3 4 7
3 5 3
6 5 4
7 1 8
 */
public class 준오는최종인재야_14657 {
	static int N;
	static int PER_TIME;
	static ArrayList<Node>[] tree;
	static boolean[] visited;
	static int[][] dp;
	static int COUNT = 0;
	static int HOUR = 1;
	static int MAX_COUNT;
	static int MIN_HOUR = Integer.MAX_VALUE;
	static int ROOT = 1;
	static int LAST;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		PER_TIME = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			tree[u].add(new Node(v, h));
			tree[v].add(new Node(u, h));
		}

		dfs(ROOT);
		for (int i = 0; i < N + 1; i++) {
			dp[i][COUNT] = 0;
			dp[i][HOUR] = 0;
		}
		Arrays.fill(visited, false);
		MIN_HOUR = Integer.MAX_VALUE;
		MAX_COUNT = 0;
		ROOT = LAST;
		dfs(ROOT);

		int day = MIN_HOUR / PER_TIME;
		if (MIN_HOUR % PER_TIME > 0) day++;
		System.out.print(day);
	}

	private static void dfs(int node) {
		visited[node] = true;

		if (node != ROOT && tree[node].size() == 1) {
			if (dp[node][COUNT] == MAX_COUNT) {
				if (dp[node][HOUR] < MIN_HOUR) {
					MIN_HOUR = dp[node][HOUR];
					LAST = node;
				}
			} else if (dp[node][COUNT] > MAX_COUNT){
				MAX_COUNT = dp[node][COUNT];
				MIN_HOUR = dp[node][HOUR];
				LAST = node;
			}
		}

		for (Node child : tree[node]) {
			if (visited[child.here]) continue;

			dp[child.here][HOUR] += dp[node][HOUR] + child.hour;
			dp[child.here][COUNT] += dp[node][COUNT] + 1;

			dfs(child.here);
		}
	}

	private static class Node {
		int here;
		int hour;

		public Node(int here, int hour) {
			this.here = here;
			this.hour = hour;
		}
	}
}
